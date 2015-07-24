package com.mdeng.common.dataimport;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象导入器
 * 
 * @author Administrator
 *
 */
public class AbstractImporter {
  protected Logger logger = LoggerFactory.getLogger(this.getClass());
  protected File[] files;

  /**
   * 指定输入文件路径
   * 
   * @param path
   */
  public AbstractImporter(String path) {
    File file = new File(path);
    if (file.isDirectory()) {
      files = file.listFiles();
    } else if (file.isFile()) {
      files = new File[1];
      files[0] = file;
    }
  }
}
