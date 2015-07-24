package com.ymssdeng.basis.helper.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.http.util.Args;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * JSON Utilities
 * 
 * @author Administrator
 *
 */
public class Jsons {

  /**
   * Shared ObjectMapper
   */
  public static final ObjectMapper MAPPER = new ObjectMapper();
  static {
    MAPPER.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
  }

  /**
   * Convert an object to JSON string
   * 
   * @param obj
   * @return
   * @throws Exception
   */
  public static String obj2Json(Object obj) throws Exception {
    Args.notNull(obj, "obj");
    return MAPPER.writeValueAsString(obj);
  }

  /**
   * Convert an object to JSON and write to stream
   * 
   * @param out
   * @param obj
   * @throws Exception
   */
  public static void writeAsJson(OutputStream out, Object obj) throws Exception {
    Args.notNull(out, "out");
    Args.notNull(obj, "obj");

    MAPPER.writeValue(out, obj);
  }

  /**
   * Convert JSON to object
   * 
   * @param json
   * @param clazz
   * @return
   */
  public static <T> T json2Obj(String json, Class<T> clazz) throws Exception {
    Args.notNull(json, "json");
    Args.notNull(clazz, "clazz");

    return MAPPER.readValue(json, clazz);
  }

  /**
   * Convert JSON to object
   * 
   * @param json
   * @param clazz
   * @return
   */
  public static <T> T json2Obj(InputStream in, Class<T> clazz) throws Exception {
    Args.notNull(in, "in");
    Args.notNull(clazz, "clazz");

    return MAPPER.readValue(in, clazz);
  }

  /**
   * Convert JSON to List
   * 
   * @param json
   * @param clazz
   * @return
   */
  public static <T> List<T> json2List(String json, Class<T[]> clazz) throws Exception {
    Args.notNull(json, "json");
    Args.notNull(clazz, "clazz");

    T[] ts = MAPPER.readValue(json, clazz);
    return Arrays.asList(ts);
  }

  /**
   * Convert JSON to Set
   * 
   * @param json
   * @param clazz
   * @return
   */
  @SuppressWarnings("unchecked")
  public static <T> Set<T> json2Set(String json, Class<T> clazz) throws Exception {
    Args.notNull(json, "json");
    Args.notNull(clazz, "clazz");

    return MAPPER.readValue(json, Set.class);
  }
}
