package com.bj58.seo.oceanusex.dal;

import java.util.ArrayList;
import java.util.List;

/**
 * 结果集封装
 * 
 * @author hui.deng
 *
 * @param <T>
 */
public class OceanusResult<T extends OceanusEntity> {

  private int total = 0;

  List<T> list = new ArrayList<T>();

  public OceanusResult() {

  }

  public OceanusResult(int total, List<T> lst) {
    this.total = total;
    this.list = lst;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }

}
