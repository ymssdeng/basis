package com.mdeng.common.http;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import com.mdeng.common.utils.Jsons;

/**
 * Handlers to handle http response
 * 
 * @author hui.deng
 *
 */
public final class HttpResponseHandlers {

  private HttpResponseHandlers() {}

  // enum singleton pattern
  public static ResponseHandler<StatusLine> statusLineHandler() {
    return StatusLineHandler.INSTANCE;
  }

  // enum singleton pattern
  public static ResponseHandler<String> stringHandler() {
    return StringEntityHandler.INSTANCE;
  }

  public static <T> ResponseHandler<T> jsonHandler(Class<T> clazz) {
    return new JsonEntityHandler<T>(clazz);
  }

  private enum StatusLineHandler implements ResponseHandler<StatusLine> {
    INSTANCE;
    @Override
    public StatusLine handleResponse(HttpResponse response) throws ClientProtocolException,
        IOException {
      return response.getStatusLine();
    }
  }

  private enum StringEntityHandler implements ResponseHandler<String> {
    INSTANCE;

    @Override
    public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
      return EntityUtils.toString(response.getEntity());
    }
  }

  private static class JsonEntityHandler<T> implements ResponseHandler<T> {

    private Class<T> clazz;

    public JsonEntityHandler(Class<T> clazz) {
      this.clazz = clazz;
    }

    @Override
    public T handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
      try {
        InputStream in = response.getEntity().getContent();
        return Jsons.json2Obj(in, clazz);
      } catch (Exception e) {
        e.printStackTrace();
      }

      return null;
    }

  }
}
