package com.ymssdeng.basis.helper.dataimport.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.base.Function;
import com.ymssdeng.basis.helper.dal.IEntity;
import com.ymssdeng.basis.helper.dataimport.AbstractImporter;

/**
 * 处理较小的Excel
 * 
 * @author Administrator
 *
 */
public class SmallExcelImporter extends AbstractImporter {

  private static final int MAX_THREAD_SIZE = 5;
  private ExecutorService es;
  private Function<Row, ? extends IEntity> function;

  public SmallExcelImporter(String path, Function<Row, ? extends IEntity> function) {
    super(path);
    es = Executors.newFixedThreadPool(MAX_THREAD_SIZE);
    this.function = function;
  }

  public void exec() {
    for (File file : files) {
      es.submit(new Scaner(file));
    }
  }

  public void waitForComplete() {
    try {
      es.shutdown();
      es.awaitTermination(5, TimeUnit.DAYS);
    } catch (InterruptedException e) {}
  }

  class Scaner implements Runnable {
    private final File file;

    public Scaner(File file) {
      this.file = file;
    }

    @Override
    public void run() {
      try {
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
        for (Row row : wb.getSheetAt(0)) {
          function.apply(row);
        }

        logger.info("{} completed.", file.getName());
      } catch (Exception e) {
        logger.error("{} process failed.", file.getName(), e);
      }
    }
  }

}
