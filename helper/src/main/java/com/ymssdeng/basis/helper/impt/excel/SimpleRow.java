package com.ymssdeng.basis.helper.impt.excel;

public interface SimpleRow {

  /**
   * 获取单元格数据
   * 
   * @param cellIndex zero-based
   * @return
   */
  public String getCellValue(int cellIndex);

  /**
   * 设置单元格数据
   * 
   * @param cellIndex zero-based
   * @param value
   */
  public void setCellValue(int cellIndex, String value);

  /**
   * 获取行号, 从0开始
   * 
   * @return zero-based row index
   */
  public int getRowIndex();
}
