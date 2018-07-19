package com.chouchongkeji.goexplore.utils;

import okhttp3.Response;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * <b>项目名称</b>：background<br>
 * <b>类名称</b>：HttpClientUtils<br>
 * <b>类描述</b>：HTTP接口请求工具类<br>
 * <b>创建人</b>：SAM QZL<br>
 * <b>创建时间</b>：2016-3-31 下午4:08:50<br>
 * <b>修改人</b>：SAM QZL<br>
 * <b>修改时间</b>：2016-3-31 下午4:08:50<br>
 * <b>修改备注</b>：<br>
 * <b>依赖包</b>：httpclient-4.5.1.jar httpclient-cache-4.5.1.jar
 * httpclient-win-4.5.1.jar httpcore-4.4.3.jar httpmime-4.5.1.jar
 * @author SAM QZL<br>
 * @version
 * 
 */
public class HttpClientUtils {

    /** HTTPS **/
    private static CloseableHttpClient httpsClient;
    /** HTTP **/
    private static CloseableHttpClient httpClient;

    public static void setHttpsClient(CloseableHttpClient httpsClient) {

        HttpClientUtils.httpsClient = httpsClient;
    }

    public static void setHttpClient(CloseableHttpClient httpClient) {

        HttpClientUtils.httpClient = httpClient;
    }

    /**
     * @功能说明:获取https连接对象(单例模式)
     * @return httpsClient
     * @throws Exception
     * @返回类型:CloseableHttpClient
     * @方法名称:getHttpsClient
     * @类名称:HttpClientUtils
     * @文件名称:HttpClientUtils.java
     * @所属包名:com.meonline.util
     * @项目名称:meonline
     * @创建时间:2016-10-25 下午3:16:49
     * @作者:SAM QZL
     * @版本:1.0
     */
    public static synchronized CloseableHttpClient getHttpsClient() throws Exception {

        if (httpsClient != null) {
            return httpsClient;
        }
        else {
            httpsClient = new SSLClient();
            return httpsClient;
        }
    }

    /**
     * @功能说明:获取http连接对象(单例模式)
     * @return httpClient
     * @throws Exception
     * @返回类型:CloseableHttpClient
     * @方法名称:getHttpClient
     * @类名称:HttpClientUtils
     * @文件名称:HttpClientUtils.java
     * @所属包名:com.meonline.util
     * @项目名称:meonline
     * @创建时间:2016-10-25 下午3:17:58
     * @作者:SAM QZL
     * @版本:1.0
     */
    public static synchronized CloseableHttpClient getHttpClient() throws Exception {

        if (httpClient != null) {
            return httpClient;
        }
        else {
            httpClient = HttpClients.createDefault();
            return httpClient;
        }
    }

    /**
     * @功能说明:https post 请求并带有headers参数
     * @param url
     *            请求地址<String>
     * @param headers
     *            请求头参数<Map>
     * @param params
     *            请求参数<String>
     * @param charset
     *            字符编码<String>
     * @return 服务器返回内容
     * @返回类型:String
     * @方法名称:doPostWithHeader
     * @类名称:HttpClientUtils
     * @文件名称:HttpClientUtils.java
     * @所属包名:com.meonline.util
     * @项目名称:meonline
     * @创建时间:2016-10-25 下午3:22:19
     * @作者:SAM QZL
     * @版本:1.0
     */
    public static String doPostWithHeader(String url, Map<String, String> headers, String params, String charset) {

        /** 获取连接对象 **/
        CloseableHttpClient httpsClient = null;
        /** 设置URL **/
        HttpPost post = null;
        /** 结果 **/
        String result = "";
        try {
            httpsClient = getHttpsClient();
            post = new HttpPost(url);
            /** 设置请求头 **/
            if (headers != null && headers.size() > 0) {
                Iterator<Entry<String, String>> iterators = headers.entrySet().iterator();
                while (iterators.hasNext()) {
                    Entry<String, String> entry = iterators.next();
                    post.addHeader(entry.getKey(), entry.getValue());
                }
            }
            /** 设置参数 **/
            StringEntity paramEntry = new StringEntity(params, charset);
            post.setEntity(paramEntry);
            /** 获取返回内容 **/
            CloseableHttpResponse response = null;
            response = httpsClient.execute(post);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            /** 关闭连接 **/
            post.releaseConnection();
        }
        return result;
    }

    /**
     * @功能说明:https post 请求方法
     * @param url
     *            请求地址<String>
     * @param map
     *            请求参数<Map>
     * @param charset
     *            字符编码<String>
     * @return 服务器返回内容
     * @返回类型:String
     * @方法名称:doPost
     * @类名称:HttpClientUtils
     * @文件名称:HttpClientUtils.java
     * @所属包名:com.meonline.util
     * @项目名称:meonline
     * @创建时间:2016-10-25 下午3:25:08
     * @作者:SAM QZL
     * @版本:1.0
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static String doPost(String url, Map<String, String> map, String charset) {

        try {
            Response post = OkHttpUtil.post(OkHttpManager.create(null, null), url, RequestParams.valueof(map));
            if (post.isSuccessful()) {
                return post.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
//        HttpClient httpClient = null;
//        HttpPost httpPost = null;
//        String result = null;
//        try {
//            httpClient = getHttpsClient();
//            httpPost = new HttpPost(url);
//            // 设置参数
//            List<NameValuePair> list = new ArrayList<NameValuePair>();
//            Iterator iterator = map.entrySet().iterator();
//            while (iterator.hasNext()) {
//                Entry<String, String> elem = (Entry<String, String>) iterator.next();
//                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
//            }
//            if (list.size() > 0) {
//                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
//                httpPost.setEntity(entity);
//            }
//            HttpResponse response = httpClient.execute(httpPost);
//            if (response != null) {
//                HttpEntity resEntity = response.getEntity();
//                if (resEntity != null) {
//                    result = EntityUtils.toString(resEntity, charset);
//                }
//            }
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        finally {
//            httpPost.releaseConnection();
//        }
//        return result;
    }

    /**
     * @功能说明:https post 请求方法
     * @param url
     *            请求地址<String>
     * @param params
     *            请求参数<String>
     * @param charset
     *            字符编码<String>
     * @return 服务器返回内容
     * @返回类型:String
     * @方法名称:doPost
     * @类名称:HttpClientUtils
     * @文件名称:HttpClientUtils.java
     * @所属包名:com.meonline.util
     * @项目名称:meonline
     * @创建时间:2016-10-25 下午3:25:08
     * @作者:SAM QZL
     * @版本:1.0
     */
    public static String doPost(String url, String params, String charset) {

        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = getHttpsClient();
            httpPost = new HttpPost(url);
            StringEntity paramsEntity = new StringEntity(params, charset);
            httpPost.setEntity(paramsEntity);
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            httpPost.releaseConnection();
        }
        return result;
    }

    /**
     * @功能说明:https get 请求方法
     * @param url
     *            请求地址<String>
     * @param params
     *            请求参数<Map>
     * @param charset
     *            字符编码<String>
     * @return 服务器返回内容
     * @返回类型:String
     * @方法名称:doGet
     * @类名称:HttpClientUtils
     * @文件名称:HttpClientUtils.java
     * @所属包名:com.meonline.util
     * @项目名称:meonline
     * @创建时间:2016-10-25 下午3:30:33
     * @作者:SAM QZL
     * @版本:1.0
     */
    public static String doGet(String url, Map<String, Object> params, String charset) {

        HttpClient httpClient = null;
        HttpGet httpGet = null;
        String result = null;
        try {
            httpClient = getHttpsClient();
            String getParams = "?";
            if (params != null && params.size() > 0) {
                /** 设置参数 **/
                Iterator<Entry<String, Object>> iterator = params.entrySet().iterator();
                while (iterator.hasNext()) {
                    Entry<String, Object> entry = iterator.next();
                    getParams += entry.getKey() + "=";
                    getParams += entry.getValue() + "&";
                }
                getParams = getParams.substring(0, (getParams.lastIndexOf("&")));
            }
            httpGet = new HttpGet(url + getParams);
            httpGet.addHeader("Content-Type", "text/json");
            HttpResponse response = httpClient.execute(httpGet);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            httpGet.releaseConnection();
        }
        return result;
    }

    /**
     * @功能说明:http get 请求方法
     * @param url
     *            请求地址<String>
     * @param params
     *            请求参数<Map>
     * @param charset
     *            字符编码<String>
     * @return 服务器返回内容
     * @返回类型:String
     * @方法名称:doGetHttp
     * @类名称:HttpClientUtils
     * @文件名称:HttpClientUtils.java
     * @所属包名:com.meonline.util
     * @项目名称:meonline
     * @创建时间:2016-10-25 下午3:34:39
     * @作者:SAM QZL
     * @版本:1.0
     */
    public static String doGetHttp(String url, Map<String, Object> params, String charset) {

        HttpClient httpClient = null;
        HttpGet httpGet = null;
        String result = null;
        try {
            httpClient = getHttpClient();
            String getParams = "?";
            if (params != null && params.size() > 0) {
                /** 设置参数 **/
                Iterator<Entry<String, Object>> iterator = params.entrySet().iterator();
                while (iterator.hasNext()) {
                    Entry<String, Object> entry = iterator.next();
                    getParams += entry.getKey() + "=";
                    getParams += entry.getValue() + "&";
                }
                getParams = getParams.substring(0, (getParams.lastIndexOf("&")));
            }
            httpGet = new HttpGet(url + getParams);
            httpGet.addHeader("Content-Type", "text/json");
            HttpResponse response = httpClient.execute(httpGet);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            httpGet.releaseConnection();
        }
        return result;
    }

    /**
     * @功能说明:http post 请求方法
     * @param url
     *            请求地址<String>
     * @param params
     *            请求参数<Map>
     * @param charset
     *            字符编码<String>
     * @return 服务器返回内容
     * @返回类型:String
     * @方法名称:doPostHttp
     * @类名称:HttpClientUtils
     * @文件名称:HttpClientUtils.java
     * @所属包名:com.meonline.util
     * @项目名称:meonline
     * @创建时间:2016-10-25 下午3:35:44
     * @作者:SAM QZL
     * @版本:1.0
     */
    public static String doPostHttp(String url, Map<String, Object> params, String charset) {

        /** 连接对象 **/
        HttpClient httpClient = null;
        /** post **/
        HttpPost httpPost = null;
        /** 结果 **/
        String result = null;
        try {
            httpClient = getHttpClient();
            httpPost = new HttpPost(url);
            if (params != null && params.size() > 0) {
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                /** 设置参数 **/
                Iterator<Entry<String, Object>> iterator = params.entrySet().iterator();
                while (iterator.hasNext()) {
                    Entry<String, Object> entry = iterator.next();
                    list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                }
                if (list.size() > 0) {
                    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                    httpPost.setEntity(entity);
                }
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            httpPost.releaseConnection();
        }
        return result;
    }

    /**
     * @功能说明:http post 请求并带有headers参数
     * @param url
     *            请求地址<String>
     * @param headers
     *            请求头参数<Map>
     * @param params
     *            请求参数<String>
     * @param charset
     *            字符编码<String>
     * @return 服务器返回内容
     * @返回类型:String
     * @方法名称:doPostWithHeader
     * @类名称:HttpClientUtils
     * @文件名称:HttpClientUtils.java
     * @所属包名:com.meonline.util
     * @项目名称:meonline
     * @创建时间:2016-10-25 下午3:22:19
     * @作者:SAM QZL
     * @版本:1.0
     */
    public static String doPostHttpWithHeader(String url, Map<String, String> headers, String params, String charset) {

        /** 获取连接对象 **/
        HttpClient httpsClient = null;
        /** 设置URL **/
        HttpPost post = null;
        /** 结果 **/
        String result = "";
        try {
            httpsClient = getHttpClient();
            post = new HttpPost(url);
            /** 设置请求头 **/
            if (headers != null && headers.size() > 0) {
                Iterator<Entry<String, String>> iterators = headers.entrySet().iterator();
                while (iterators.hasNext()) {
                    Entry<String, String> entry = iterators.next();
                    post.addHeader(entry.getKey(), entry.getValue());
                }
            }
            /** 设置参数 **/
            StringEntity paramEntry = new StringEntity(params, charset);
            post.setEntity(paramEntry);
            /** 获取返回内容 **/
            HttpResponse response = httpsClient.execute(post);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            /** 关闭连接 **/
            post.releaseConnection();
        }
        return result;
    }
}
