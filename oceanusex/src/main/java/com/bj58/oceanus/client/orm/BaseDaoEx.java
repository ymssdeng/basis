package com.bj58.oceanus.client.orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bj58.oceanus.client.Oceanus;

/**
 * BaseDao extension
 * 
 * @author hui.deng
 *
 */
public class BaseDaoEx extends BaseDao {

  /**
   * Execute update and get generated key
   * 
   * @param sql
   * @param objects
   * @return
   * @throws Exception
   */
  public Object insertAndGetKey(String sql, Object... objects) throws Exception {
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = Oceanus.getConnection();
      ps = connection.prepareStatement(sql);
      if (objects != null && objects.length > 0) {
        for (int i = 0; i < objects.length; i++) {
          ps.setObject(i + 1, objects[i]);
        }
      }

      ps.executeUpdate();

      // 暂不支持!
      /*
       * ResultSet rs = ps.getGeneratedKeys(); if (rs.next()) { return rs.getObject(1); }
       */

      return null;
    } finally {
      Oceanus.closeStatement(ps);
      Oceanus.closeConnection(connection);
    }
  }

  /**
   * Batch operation using single SQL
   * 
   * @param sql
   * @param objects parameters for each statement
   * @throws SQLException
   */
  public void batch(String sql, List<Object[]> objects) throws SQLException {
    Connection connection = null;
    PreparedStatement ps = null;
    try {
      connection = Oceanus.getConnection();
      Oceanus.beginTransaction(connection);
      ps = connection.prepareStatement(sql);
      if (objects != null && objects.size() > 0) {
        for (int i = 0; i < objects.size(); i++) {
          if (objects.get(i) != null && objects.get(i).length > 0) {
            for (int j = 0; j < objects.get(i).length; j++) {
              ps.setObject(j + 1, objects.get(i)[j]);
            }
            ps.addBatch();
          }
        }
        ps.executeBatch();
      }

      // commit
      Oceanus.endTransaction(connection);
      // log.debug("Transaction end with sql: " + sql);
    } finally {
      Oceanus.closeStatement(ps);
      Oceanus.closeConnection(connection);
      Oceanus.releaseTransaction();
    }
  }

  /**
   * 
   * @param sql
   * @param values
   * @return
   * @throws SQLException
   */
  public int excuteCount(String sql, Object... objects) throws Exception {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      connection = Oceanus.getConnection();
      ps = connection.prepareStatement(sql);
      if (objects != null && objects.length > 0) {
        for (int i = 0; i < objects.length; i++) {
          ps.setObject(i + 1, objects[i]);
        }
      }
      rs = ps.executeQuery();
      if (rs.next()) {
        return rs.getInt(1);
      }

      throw new SQLException("Can not get count from result set");
    } finally {
      Oceanus.close(rs, ps, connection);
    }
  }

}
