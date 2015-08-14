package com.bj58.seo.oceanusex.dal;


/**
 * Pagination of data access
 * 
 * @author hui.deng
 *
 */
public class Pagination {

  protected int pageNo = 1;
  protected int pageSize = 15;
  protected int total = 0;
  protected int pageTotal = 0;
  protected OrderBy order = OrderBy.ASC;
  protected String orderBy = null;

  public Pagination() {}

  public Pagination(int pageNo, int pageSize) {
    this.pageNo = pageNo;
    this.pageSize = pageSize;
  }

  public int getPageNo() {
    return pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getTotal() {
    return total;
  }

  public int getPageTotal() {
    return pageTotal;
  }

  public String getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

  public OrderBy getOrder() {
    return order;
  }

  public void setOrder(OrderBy order) {
    this.order = order;
  }

  public void setTotal(int total) {
    this.total = total;
    this.pageTotal = (int) Math.ceil((double) total / pageSize);
  }

}
