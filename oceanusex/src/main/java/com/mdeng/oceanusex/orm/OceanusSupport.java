package com.mdeng.oceanusex.orm;

import java.util.List;

import com.mdeng.oceanusex.dal.DBField;
import com.mdeng.oceanusex.dal.OceanusEntity;
import com.mdeng.oceanusex.dal.OceanusResult;
import com.mdeng.oceanusex.dal.Pagination;

/**
 * Database access methods set
 * 
 * @author hui.deng
 *
 * @param <T>
 */
public interface OceanusSupport<T extends OceanusEntity> {
  /**
   * Get by primary key id, exception if not found
   * 
   * @param id
   * @return
   * @throws Exception
   */
  T getById(Object id) throws Exception;

  /**
   * Get by primary key id
   * 
   * @param id
   * @return
   * @throws Exception
   */
  List<T> getById(Object[] id) throws Exception;

  /**
   * Get by fields with pagination
   * 
   * @param field
   * @param value
   * @return
   * @throws Exception
   */
  OceanusResult<T> getByFields(Pagination pgn, DBField... fields) throws Exception;

  /**
   * Get by fields, exception if not only
   * @param fields
   * @return
   * @throws Exception
   */
  T getByFields(DBField... fields) throws Exception;

  /**
   * Count by fields
   * 
   * @param field
   * @return
   * @throws Exception
   */
  int count(DBField... fields) throws Exception;

  /**
   * Insert into database
   * 
   * @param t
   * @return
   * @throws Exception
   */
  void insert(T t) throws Exception;

  /**
   * Batch insert in one transaction
   * 
   * @param lst
   * @throws Exception
   */
  void insert(List<T> lst) throws Exception;

  /**
   * Update an object on fields
   * 
   * @param t
   * @param fields
   * @throws Exception
   */
  void update(T t, String... fields) throws Exception;

  /**
   * Delete by key
   * 
   * @param key
   * @throws Exception
   */
  void delete(Object key) throws Exception;

  /**
   * Delete by condition
   * 
   * @param where
   * @param values
   * @throws Exception
   */
  void delete(String where, Object... values) throws Exception;

  /**
   * Custom pagination
   * 
   * @param where
   * @param pgn
   * @param values
   * @return
   * @throws Exception
   */
  OceanusResult<T> pagination(String where, Pagination pgn, Object... values) throws Exception;
}
