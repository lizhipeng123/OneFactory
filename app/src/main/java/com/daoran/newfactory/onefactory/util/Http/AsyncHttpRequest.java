package com.daoran.newfactory.onefactory.util.Http;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * @version V1.0
 * 继承自Runnabler，被submit至线程池执行网络请求并发送start，success等消息
 * @Description: TODO
 * @date 2013-4-26 下午7:46:18
 */
class AsyncHttpRequest implements Runnable {
    private final AbstractHttpClient client;
    private final HttpContext context;
    private final HttpUriRequest request;
    private final AsyncHttpResponseHandler responseHandler;
    private int executionCount;

    public AsyncHttpRequest(AbstractHttpClient client, HttpContext context, HttpUriRequest request, AsyncHttpResponseHandler responseHandler) {
        this.client = client;
        this.context = context;
        this.request = request;
        this.responseHandler = responseHandler;
    }

    @Override
    public void run() {
        try {
            if (responseHandler != null) {
                responseHandler.sendStartMessage();
            }

            makeRequestWithRetries();

            if (responseHandler != null) {
                responseHandler.sendFinishMessage();
            }
        } catch (IOException e) {
            if (responseHandler != null) {
                responseHandler.sendFinishMessage();
                responseHandler.sendFailureMessage(e, (String) null);
            }
        }
    }

    private void makeRequest() throws IOException {
        if (!Thread.currentThread().isInterrupted()) {
            try {
                HttpResponse response = client.execute(request, context);
                if (!Thread.currentThread().isInterrupted()) {
                    if (responseHandler != null) {
                        responseHandler.sendResponseMessage(response);
                    }
                } else {
                    Log.e("AsyncHttpRequest", "this block is reached whenever the request is cancelled before its response is received");
                }
            } catch (IOException e) {
                if (!Thread.currentThread().isInterrupted()) {
                    throw e;
                }
            }
        }
    }

    private void makeRequestWithRetries() throws ConnectException {
        // This is an additional layer of retry logic lifted from droid-fu
        // See: https://github.com/kaeppler/droid-fu/blob/master/src/main/java/com/github/droidfu/http/BetterHttpRequestBase.java
        boolean retry = true;
        IOException cause = null;
        HttpRequestRetryHandler retryHandler = client.getHttpRequestRetryHandler();
        while (retry) {
            try {
                makeRequest();
                return;
            } catch (UnknownHostException e) {
                if (responseHandler != null) {
                    responseHandler.sendFailureMessage(e, "can't resolve host");
                }
                return;
            } catch (SocketException e) {
                // Added to detect host unreachable
                if (responseHandler != null) {
                    responseHandler.sendFailureMessage(e, "can't resolve host");
                }
                return;
            } catch (SocketTimeoutException e) {
                if (responseHandler != null) {
                    responseHandler.sendFailureMessage(e, "socket time out");
                }
                return;
            } catch (IOException e) {
                cause = e;
                retry = retryHandler.retryRequest(cause, ++executionCount, context);
            } catch (NullPointerException e) {
                // there's a bug in HttpClient 4.0.x that on some occasions causes
                // DefaultRequestExecutor to throw an NPE, see
                // http://code.google.com/p/android/issues/detail?id=5255
                cause = new IOException("NPE in HttpClient" + e.getMessage());
                retry = retryHandler.retryRequest(cause, ++executionCount, context);
            } catch (Exception e) {
                cause = new IOException("Exception" + e.getMessage());
                retry = retryHandler.retryRequest(cause, ++executionCount, context);
            }
        }

        // no retries left, crap out with exception
        ConnectException ex = new ConnectException();
        ex.initCause(cause);
        throw ex;
    }
}
