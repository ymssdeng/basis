package com.bj58.oceanus.client.orm;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.ymssdeng.oceanusex.dal.Pagination;
import com.ymssdeng.oceanusex.dal.RowKey;
import com.ymssdeng.oceanusex.dal.Table;

/**
 * Simple SQL command builder
 * 
 * @author hui.deng
 *
 */
public class OceanusSqlBuilder {

  private StringBuilder builder = new StringBuilder();
  private String tableName;
  private String rowKeyFieldName;
  private List<String> names = Lists.newArrayList();
  private static Logger log = LoggerFactory.getLogger(OceanusSqlBuilder.class);

  private <T> OceanusSqlBuilder(Class<T> clazz) {
    Table table = clazz.getAnnotation(Table.class);
    if (table == null) {
      tableName = clazz.getName();
    } else {
      tableName = table.name();
    }

    for (Field field : MappingAnnotationUtil.getAllFields(clazz)) {
      // 约定不更新或插入AutoIncrementId
      if (field.isAnnotationPresent(RowKey.class)) {
        RowKey rk = field.getAnnotation(RowKey.class);
        if (rk.autoIncrement()) continue;
      }
      String name = MappingAnnotationUtil.getDBCloumnName(clazz, field);
      names.add(name);
    }

    for (Field field : MappingAnnotationUtil.getAllFields(clazz)) {
      if (field.isAnnotationPresent(RowKey.class)) {
        rowKeyFieldName = field.getName();
      }
    }
  }

  public static <T> OceanusSqlBuilder instance(Class<T> clazz) {
    return new OceanusSqlBuilder(clazz);
  }

  public OceanusSqlBuilder select() {
    builder = new StringBuilder();
    builder.append("select * from ").append(tableName).append(' ');
    return this;
  }

  public OceanusSqlBuilder select(String... fields) {
    builder = new StringBuilder();
    builder.append("select ");
    int i = 0;
    for (String f : fields) {
      builder.append(f);
      if (++i < fields.length) {
        builder.append(", ");
      }
    }
    builder.append(" from ").append(tableName).append(' ');
    return this;
  }

  @SuppressWarnings("unused")
  public OceanusSqlBuilder insert() {
    builder = new StringBuilder();
    builder.append("insert into ").append(tableName).append(" (");
    int i = 0;
    for (String name : names) {
      builder.append(name);
      if (++i < names.size()) {
        builder.append(", ");
      }
    }
    builder.append(") values (");
    i = 0;
    for (String name : names) {
      builder.append('?');
      if (++i < names.size()) {
        builder.append(", ");
      }
    }
    builder.append(')');
    return this;
  }

  public <T> OceanusSqlBuilder update(T t, String... fields) {
    builder = new StringBuilder();
    builder.append("update ").append(tableName).append(" set ");
    int i = 0;

    List<String> tmp;
    if (fields == null)
      tmp = names;
    else
      tmp = Arrays.asList(fields);

    for (String name : tmp) {
      if (tmp != names && !names.contains(name)) continue;
      builder.append(name).append("=?");
      if (++i < fields.length) {
        builder.append(", ");
      }
    }
    // where
    builder.append(" where ").append(rowKeyFieldName).append("=?");
    builder.append(' ');
    return this;
  }

  public OceanusSqlBuilder delete() {
    builder = new StringBuilder();
    builder.append("delete from ").append(tableName).append(' ');
    return this;
  }

  public OceanusSqlBuilder where(String where) {
    if (!Strings.isNullOrEmpty(where)) {
      builder.append("where ").append(where).append(' ');
    }
    return this;
  }

  public OceanusSqlBuilder pagination(Pagination pgn) {
    if (pgn == null) return this;

    if (pgn.getOrderBy() != null) {
      builder.append("order by ").append(pgn.getOrderBy()).append(' ').append(pgn.getOrder());
    }
    builder.append(" limit ?,? ");
    return this;
  }

  public String build() {
    String sql = builder.toString();
    log.debug("Build sql: " + sql);
    return sql;
  }
}
