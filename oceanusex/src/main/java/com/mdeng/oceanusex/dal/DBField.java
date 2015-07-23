package com.mdeng.oceanusex.dal;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * Database filed
 * 
 * @author hui.deng
 *
 */
public class DBField {
  private String name;
  private Object value;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public DBField(String name, Object value) {
    super();
    this.name = name;
    this.value = value;
  }

  public static Object[] values(List<DBField> fields) {
    return Lists.transform(fields, new Function<DBField, Object>() {

      @Override
      public Object apply(DBField input) {
        return input.getValue();
      }

    }).toArray();
  }
}
