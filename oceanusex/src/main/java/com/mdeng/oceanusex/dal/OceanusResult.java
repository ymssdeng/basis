package com.mdeng.oceanusex.dal;

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

  List<T> oceanusList = new ArrayList<T>();

  public OceanusResult() {

  }

  public OceanusResult(int total, List<T> lst) {
    this.total = total;
    this.oceanusList = lst;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public List<T> getOceanusList() {
    return oceanusList;
  }

  public void setOceanusList(List<T> oceanusList) {
    this.oceanusList = oceanusList;
  }

}
