package com.ymssdeng.basis.helper.http;

import java.io.IOException;

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
  private int maxRetries = 1;

  HttpRequestBuilder() {
    httpclient = HttpClients.createDefault();
  }

  HttpRequestBuilder(CloseableHttpClient client) {
    httpclient = client;
  }
  
  public static HttpRequestBuilder create() {
    return new HttpRequestBuilder();
  }
  
  public static HttpRequestBuilder create(CloseableHttpClient client) {
    return new HttpRequestBuilder(client);
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
  
  public HttpRequestBuilder maxRetries(int maxRetries) {
    this.maxRetries = maxRetries;
    return this;
  }

  public void execute() throws Exception {
    execute(null);
  }

  public <T> T execute(ResponseHandler<T> handler) {
    Preconditions.checkNotNull(method, "you have not set http method yet");

    T t = null;
    for (int i = 0; i < maxRetries; i++) {
      try {
        if (config != null) method.setConfig(config);
        CloseableHttpResponse response = httpclient.execute(method);
        if (handler != null) {
          t = handler.handleResponse(response);
        } else {
          EntityUtils.consume(response.getEntity());
        }
        return t;
      } catch (Exception e) {} finally {
        method.releaseConnection();
        try {
          httpclient.close();
        } catch (IOException e) {}
      }
    }

    return null;
  }
}
