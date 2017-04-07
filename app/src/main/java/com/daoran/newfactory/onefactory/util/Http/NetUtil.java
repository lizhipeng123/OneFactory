package com.daoran.newfactory.onefactory.util.Http;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.util.Log;

import com.daoran.newfactory.onefactory.util.Comfig;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetUtil {
    private static final String LogTag = NetUtil.class.getSimpleName();

    private static final HttpClient HTTP_CLIENT;

    private static AsyncHttpClient asyncHttpClient;
    private static SyncHttpClient syncHttpClient;

    static {
        HttpParams params = new BasicHttpParams();
        ConnManagerParams.setMaxTotalConnections(params, 100);
        ConnManagerParams.setTimeout(params, 20 * 1000);
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);

        // Create and initialize scheme registry
        SchemeRegistry schreg = new SchemeRegistry();
        schreg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schreg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));

        ThreadSafeClientConnManager connectionManager = new ThreadSafeClientConnManager(params, schreg);

        HTTP_CLIENT = new DefaultHttpClient(connectionManager, params);
    }

    /**
     * 创建{@link BasicNameValuePair} 参数.
     *
     * @param name
     * @param value
     */
    public static NameValuePair createParam(String name, Object value) {
        return new BasicNameValuePair(name, String.valueOf(value));
    }

    /**
     * 封装一个带参数的URL组合
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */

    public static String executeGet(String url, List<NameValuePair> params) throws Exception {

        StringBuffer urlWithParams = new StringBuffer(url);

        boolean isFirst = true;
        for (NameValuePair param : params) {
            if (isFirst) {
                urlWithParams.append("?");
                isFirst = false;
            } else {
                urlWithParams.append("&");
            }
            urlWithParams.append(param.getName() + "=" + param.getValue());
        }

        return executeGet(urlWithParams.toString());
    }

    /**
     * HTTP REQUESTS GET方式访问服务器
     *
     * @param url
     * @return JSON字符串
     * @throws IOException
     */

    public static String executeGet(String url) throws Exception {
        if (Comfig.isDebug) {
            System.out.println(url);
        }
        HttpGet request = new HttpGet(url);
        HttpResponse response = HTTP_CLIENT.execute(request);

        int status = response.getStatusLine().getStatusCode();

        if (status != HttpStatus.SC_OK) {
            ByteArrayOutputStream ostream = new ByteArrayOutputStream();
            response.getEntity().writeTo(ostream);
            return null;
        } else {
            ByteArrayOutputStream ostream = new ByteArrayOutputStream();
            String result = null;
            try {
                response.getEntity().writeTo(ostream);
                result = ostream.toString();
            } catch (IOException e) {
                throw e;
            } finally {
                ostream.close();
                ostream = null;
            }

            if (Comfig.isDebug) {
                Log.v(LogTag, "get response: " + result);
            }
            return result;
        }

    }

    /**
     * HTTP REQUESTS 通过URL获取bitmap
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static Bitmap getImage(String url) throws Exception {

        if (Comfig.isDebug) {
            Log.v(LogTag, url);
        }
        HttpGet request = new HttpGet(url);
        HttpResponse response = HTTP_CLIENT.execute(request);

        int status = response.getStatusLine().getStatusCode();

        if (status != HttpStatus.SC_OK) {
            if (Comfig.isDebug) {
                Log.v(LogTag, "get HttpStatus: " + status);
            }
            return null;
        } else {

            InputStream stream = null;
            Bitmap bitMap = null;
            try {
                stream = response.getEntity().getContent();
                bitMap = BitmapFactory.decodeStream(stream);
            } catch (IOException e) {
                throw e;
            } finally {
                if (null != stream) {
                    stream.close();
                    stream = null;
                }
            }

            if (Comfig.isDebug) {
                Log.v(LogTag, "get response: <stream>");
            }

            return bitMap;
        }

    }

    /**
     * @param context
     * @return
     */
    public static boolean isNetAvailable(Context context) {

        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connMgr == null) {
            return false;
        }

        return (connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected())
                || (connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .isConnected())
                || (connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIMAX) != null && connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIMAX)
                .isConnected());
    }

    /**
     * POST请求方法
     *
     * @param url    目标URL
     * @param params 访问参数
     * @return JSON字符串
     * @throws IOException
     * @throws NetException
     */
    public static String executePost(String url, List<NameValuePair> params) throws Exception {

        String result = "";

        if (Comfig.isDebug) {
            System.out.println(url + ":" + params);
        }
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("charset", HTTP.UTF_8);

        if (params != null) {
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                throw new UnsupportedEncodingException(e.getLocalizedMessage());
            }
        }

        HttpResponse response;
        try {
            response = HTTP_CLIENT.execute(httpPost);
        } catch (ClientProtocolException e1) {

            throw new ClientProtocolException(e1.getLocalizedMessage());
        } catch (IOException e1) {

            throw new IOException(e1.getLocalizedMessage());
        }

        int status = response.getStatusLine().getStatusCode();

        if (status != HttpStatus.SC_OK) {

            throw new NetException(response.getStatusLine());
        }

        ByteArrayOutputStream ostream = new ByteArrayOutputStream();

        try {
            response.getEntity().writeTo(ostream);
            result = ostream.toString();
        } catch (IOException e) {
            throw e;
        } finally {
            ostream.close();
            ostream = null;
        }

        if (Comfig.isDebug) {
            Log.v(LogTag, "post response : " + result);
        }
        return result;
    }

    /**
     * 网络连接(图片读取的时候用的)
     *
     * @param url 目标URL
     * @return URLConnection对象
     * @throws IOException
     */
    private static URLConnection getConnection(String url) throws IOException {
        URL aryURI = new URL(url);
        URLConnection conn = aryURI.openConnection();
        return conn;
    }

    /**
     * 下载文件（这里主要是图片文件）
     *
     * @param context 上下文 变量
     * @param url     目标URL
     * @return 返回的是一个下载到本地的文件的名字
     * @throws IOException
     */
    public static String downloadFile(Context context, String url) throws IOException {

        if (!isNetAvailable(context)) {

            throw new IOException("");
        }
        String fileName = getFileName(url);
        InputStream is = getFileIS(url);
        OutputStream os = (FileOutputStream) LocalFileOperating.getLocalOS(context, fileName);
        LocalFileOperating.writeLocalFile(context, is, os);
        return fileName;
    }

    /**
     * 根据URL获取文件的名字
     *
     * @param url 目标URL
     * @return
     */
    public static String getFileName(String url) {

        String fileNameRegexp = "[^/]*$";
        Pattern pattern = Pattern.compile(fileNameRegexp);
        Matcher matcher = pattern.matcher(url);
        String result = "";
        if (matcher.find()) {
            result = matcher.group();
        }
        return result;
    }

    /**
     * 根据URL获取数据流对象
     *
     * @param url 目标URL
     * @return 返回的是一个数据流对象
     * @throws IOException
     */
    private static InputStream getFileIS(String url) throws IOException {
        URLConnection conn = getConnection(url);
        conn.connect();
        InputStream inputStream = conn.getInputStream();
        return inputStream;
    }

    /**
     * 单例(异步)
     */
    public static AsyncHttpClient getAsyncHttpClient() {
        if (asyncHttpClient == null) {
            asyncHttpClient = new AsyncHttpClient();
        }
        return asyncHttpClient;
    }

    /**
     * 单例(同步)
     */
    public static SyncHttpClient getSyncHttpClient() {
        if (syncHttpClient == null) {
            syncHttpClient = new SyncHttpClient() {
                @Override
                public String onRequestFailed(Throwable error, String content) {
                    return null;
                }
            };
        }
        return syncHttpClient;
    }
}