package com.bj58.oceanus.client.orm;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import com.bj58.seo.oceanusex.dal.AutoIncrementId;
import com.bj58.seo.oceanusex.dal.DBField;
import com.bj58.seo.oceanusex.dal.OceanusEntity;
import com.bj58.seo.oceanusex.dal.OceanusResult;
import com.bj58.seo.oceanusex.dal.Pagination;
import com.bj58.seo.oceanusex.exceptions.OceanusDuplicateException;
import com.bj58.seo.oceanusex.exceptions.OceanusNotFoundException;
import com.bj58.seo.oceanusex.exceptions.OceanusSqlException;
import com.bj58.seo.oceanusex.orm.OceanusSupport;
import com.google.common.collect.Lists;

/**
 * Base database access service
 * 
 * @author hui.deng
 *
 * @param <T>
 */
public class OceanusSupportImpl<T extends OceanusEntity> extends BaseDaoEx
    implements
      OceanusSupport<T> {

  protected Class<T> clazz;

  public OceanusSupportImpl(Class<T> clazz) {
    this.clazz = clazz;
  }

  /**
   * Get by primary key id, exception if not found
   * 
   * @param id
   * @return
   * @throws Exception
   */
  public T getById(Object id) throws Exception {
    return single(String.format("%s=?", getAutoIncrementFieldName()), id);
  }

  /**
   * Get by primary key id
   * 
   * @param id
   * @return
   * @throws Exception
   */
  @SuppressWarnings("unused")
  public List<T> getById(Object[] id) throws Exception {
    checkNotNull(id, "id can not be null");
    checkArgument(id.length > 0, "id length is 0");
    StringBuilder where = new StringBuilder(getAutoIncrementFieldName() + " in (");
    for (Object object : id) {
      where.append("?,");
    }
    where.deleteCharAt(where.length() - 1).append(")");
    Pagination pgn = new Pagination(1, id.length);
    return pagination(where.toString(), pgn, id).getOceanusList();
  }

  /**
   * Count by fields
   * 
   * @param fields
   * @param values
   * @return
   * @throws Exception
   */
  public int count(DBField... fields) throws Exception {
    String where = "1=1 ";
    if (fields != null) {
      for (DBField field : fields) {
        where += "and " + field.getName() + "=? ";
      }
      return count(where, DBField.values(Arrays.asList(fields)));
    }

    return count(where);
  }

  /**
   * Get by fields, return list value
   * 
   * @param fields
   * @param values
   * @param pgn
   * @return
   * @throws Exception
   */
  public OceanusResult<T> getByFields(Pagination pgn, DBField... fields) throws Exception {
    checkNotNull(fields, "fields can not be null");
    String where = "1=1 ";
    for (DBField field : fields) {
      where += "and " + field.getName() + "=? ";
    }
    return pagination(where, pgn, DBField.values(Arrays.asList(fields)));
  }

  public T getByFields(DBField... fields) throws Exception {
    checkNotNull(fields, "fields can not be null");
    String where = "1=1 ";
    for (DBField field : fields) {
      where += "and " + field.getName() + "=? ";
    }
    return single(where, DBField.values(Arrays.asList(fields)));
  }

  /**
   * Insert into database
   * 
   * @param t
   * @return
   * @throws Exception
   */
  public void insert(T t) throws Exception {
    checkNotNull(t, "t can not be null");
    String sql = OceanusSqlBuilder.instance(clazz).insert().build();

    Object[] params = getFieldsValues(t);
    insertAndGetKey(sql, params);
  }

  /**
   * Batch insert in one transaction
   * 
   * @param lst
   * @throws Exception
   */
  public void insert(List<T> lst) throws Exception {
    checkNotNull(lst, "lst can not be null");
    checkArgument(lst.size() > 0, "lst has no element");
    String sql = OceanusSqlBuilder.instance(clazz).insert().build();

    List<Object[]> params = Lists.newArrayList();
    for (T t : lst) {
      Object[] p = getFieldsValues(t);
      params.add(p);
    }

    batch(sql, params);
  }

  /**
   * Update an object on fields
   * 
   * @param t
   * @param fields
   * @throws Exception
   */
  public void update(T t, String... fields) throws Exception {
    checkNotNull(t, "t can not be null");
    String sql = OceanusSqlBuilder.instance(clazz).update(t, fields).build();

    List<Object> params = Lists.newArrayList((getFieldsValues(t, fields)));
    params.add(getAutoIncrementFieldValue(t));
    excuteUpdate(sql, params.toArray());
  }

  /**
   * Delete by key
   * 
   * @param key
   * @throws Exception
   */
  public void delete(Object key) throws Exception {
    checkNotNull(key, "key can not be null");
    delete(String.format("%s=?", getAutoIncrementFieldName()), key);
  }

  /**
   * Delete by condition
   * 
   * @param where
   * @param values
   * @throws Exception
   */
  public void delete(String where, Object... values) throws Exception {
    checkNotNull(where, "where can not be null");
    String sql = OceanusSqlBuilder.instance(clazz).delete().where(where).build();
    excuteUpdate(sql, values);
  }

  /**
   * Custom pagination
   * 
   * @param where
   * @param pgn
   * @param values
   * @return
   * @throws Exception
   */
  public OceanusResult<T> pagination(String where, Pagination pgn, Object... values)
      throws Exception {
    checkNotNull(where, "where can not be null");
    pgn = checkPgn(pgn);

    // list
    String sql = OceanusSqlBuilder.instance(clazz).select().where(where).pagination(pgn).build();
    int start = (pgn.getPageNo() - 1) * pgn.getPageSize();
    List<Object> params = values == null ? Lists.newArrayList() : Lists.newArrayList(values);
    params.add(start);
    params.add(pgn.getPageSize());

    List<T> lst = excuteQuery(clazz, sql, params.toArray());

    // count
    int count = count(where, values);

    // set result to pagination
    pgn.setTotal(count);

    return new OceanusResult<T>(count, lst);
  }

  // /////////////////////////////////////////////////////////////////////////

  protected T single(String where, Object... values) throws Exception {
    String sql = OceanusSqlBuilder.instance(clazz).select().where(where).build();
    List<T> lst = excuteQuery(clazz, sql, values);
    if (lst.size() == 0) {
      throw new OceanusNotFoundException("Expected single but 0 found");
    } else if (lst.size() >= 2) {
      throw new OceanusDuplicateException("Expected single but " + lst.size() + " found");
    }

    return lst.get(0);
  }

  protected int count(String where, Object... values) throws Exception {
    String sql = OceanusSqlBuilder.instance(clazz).select("count(1)").where(where).build();
    return excuteCount(sql, values);
  }

  protected Pagination checkPgn(Pagination pgn) {
    return pgn != null ? pgn : new Pagination();
  }

  protected <E> boolean exists(List<E> lst) {
    return lst.size() > 0;
  }

  protected Object[] getFieldsValues(T t, String... fieldNames) throws IllegalAccessException,
      InvocationTargetException {
    List<String> tmp = null;
    if (fieldNames != null) tmp = Arrays.asList(fieldNames);

    List<Object> params = Lists.newArrayList();
    for (Field field : MappingAnnotationUtil.getAllFields(clazz)) {
      String name = MappingAnnotationUtil.getDBCloumnName(clazz, field);
      if (name != null
          && !(name.equalsIgnoreCase("id") || field.isAnnotationPresent(AutoIncrementId.class))) {
        if (tmp != null && !tmp.contains(name)) continue;
        // 约定不更新或插入Id
        Method method = MappingAnnotationUtil.getGetterMethod(clazz, field);
        params.add(method.invoke(t));
      }
    }
    return params.toArray();
  }

  private String getAutoIncrementFieldName() throws Exception {
    for (Field field : MappingAnnotationUtil.getAllFields(clazz)) {
      if (field.isAnnotationPresent(AutoIncrementId.class)) {
        return field.getName();
      }
    }

    for (Field field : MappingAnnotationUtil.getAllFields(clazz)) {
      if (field.getName().equals("id")) {
        return "id";
      }
    }

    throw new OceanusSqlException("can not find identity column");
  }

  private Object getAutoIncrementFieldValue(T t) throws Exception {
    for (Field field : MappingAnnotationUtil.getAllFields(clazz)) {
      if (field.isAnnotationPresent(AutoIncrementId.class)) {
        Method method = MappingAnnotationUtil.getGetterMethod(clazz, field);
        return method.invoke(t);
      }
    }

    for (Field field : MappingAnnotationUtil.getAllFields(clazz)) {
      if (field.getName().equals("id")) {
        Method method = MappingAnnotationUtil.getGetterMethod(clazz, field);
        return method.invoke(t);
      }
    }

    throw new OceanusSqlException("can not find identity column");
  }
}
