package com.daoran.newfactory.onefactory.util.Http;

import android.content.Context;
import android.os.Message;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;

/**
 * @version V1.0
 * 线程封装
 * @Description: TODO
 * @date 2013-4-26 下午2:47:55
 */
public abstract class SyncHttpClient extends AsyncHttpClient {
    private int responseCode;
    protected String result;
    protected AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {

        @Override
        void sendResponseMessage(org.apache.http.HttpResponse response) {
            responseCode = response.getStatusLine().getStatusCode();
            super.sendResponseMessage(response);
        }

        ;

        @Override
        protected void sendMessage(Message msg) {
            /*
             * Dont use the handler and send it directly to the analysis
			 * (because its all the same thread)
			 */
            handleMessage(msg);
        }

        @Override
        public void onSuccess(String content) {
            result = content;
        }

        @Override
        public void onFailure(Throwable error, String content) {
            result = onRequestFailed(error, content);
        }
    };

    /**
     * @return the response code for the last request, might be usefull
     * sometimes
     */
    public int getResponseCode() {
        return responseCode;
    }

    // Private stuff
    @Override
    protected void sendRequest(DefaultHttpClient client, HttpContext httpContext, HttpUriRequest uriRequest, String contentType,
                               AsyncHttpResponseHandler responseHandler, Context context) {
        if (contentType != null) {
            uriRequest.addHeader("Content-Type", contentType);
        }

		/*
         * will execute the request directly
		 */
        new AsyncHttpRequest(client, httpContext, uriRequest, responseHandler).run();
    }

    public abstract String onRequestFailed(Throwable error, String content);

    public String get(String url, RequestParams params) {
        this.get(url, params, responseHandler);
		/*
		 * the response handler will have set the result when this line is
		 * reached
		 */
        return result;
    }

    public String get(String url) {
        this.get(url, null, responseHandler);
        return result;
    }

    public String post(String url, RequestParams params) {
        this.post(url, params, responseHandler);
        return result;
    }

    public String post(String url) {
        this.post(url, null, responseHandler);
        return result;
    }
}
