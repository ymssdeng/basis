package com.ymssdeng.basis.helper.http;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Preconditions;

/**
 * To build a HTTP request.
 * 
 * @author hui.deng
 *
 */
public class HttpRequestBuilder {
  private CloseableHttpClient httpclient;
  private HttpRequestBase method;
  private RequestConfig config;

  HttpRequestBuilder() {
    httpclient = HttpClients.createDefault();
  }

  public static HttpRequestBuilder create() {
    return new HttpRequestBuilder();
  }

  public HttpRequestBuilder get(String url) {
    method = new HttpGet(url);
    return this;
  }

  public HttpRequestBuilder post(String url) {
    method = new HttpPost(url);
    return this;
  }

  public HttpRequestBuilder method(HttpRequestBase method) {
    this.method = method;
    return this;
  }

  public HttpRequestBuilder config(RequestConfig config) {
    this.config = config;
    return this;
  }

  public void execute() throws Exception {
    execute(null);
  }

  public <T> T execute(ResponseHandler<T> handler) throws Exception {
    Preconditions.checkNotNull(method, "you have not set http method yet");

    try {
      if (config != null) method.setConfig(config);
      CloseableHttpResponse response = httpclient.execute(method);
      if (handler != null) {
        return handler.handleResponse(response);
      } else {
        EntityUtils.consume(response.getEntity());
      }
    } finally {
      method.releaseConnection();
      httpclient.close();
    }

    return null;
  }

}
