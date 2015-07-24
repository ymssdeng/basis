package com.ymssdeng.basis.helper.dataimport.excel;

import java.io.File;
import java.net.URISyntaxException;

import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import com.google.common.base.Function;
import com.ymssdeng.basis.helper.dal.IEntity;
import com.ymssdeng.basis.helper.dataimport.excel.LargeExcelImporter;
import com.ymssdeng.basis.helper.dataimport.excel.SimpleRow;
import com.ymssdeng.basis.helper.dataimport.excel.SmallExcelImporter;

public class ExcelImporterTest {

  //@Test
  public void testSmall() throws URISyntaxException {
    String path =
        new File(this.getClass().getClassLoader().getResource("dataimport/small").toURI())
            .getAbsolutePath();
    SmallExcelImporter sei = new SmallExcelImporter(path, new SmallFunction());
    sei.exec();
    sei.waitForComplete();
  }

  @Test
  public void testLarge() throws URISyntaxException {
    String path =
        new File(this.getClass().getClassLoader().getResource("dataimport/large").toURI())
            .getAbsolutePath();
    LargeExcelImporter sei = new LargeExcelImporter(path, new LargeFunction());
    sei.exec();
    sei.waitForComplete();
  }

  class SmallFunction implements Function<Row, TestEntity> {

    @Override
    public TestEntity apply(Row input) {
      System.out.println("row " + input.getRowNum());
      return null;
    }

  }

  class LargeFunction implements Function<SimpleRow, TestEntity> {
    @Override
    public TestEntity apply(SimpleRow input) {
      System.out.println("row " + input.getRowIndex());
      return null;
    }
  }

  class TestEntity implements IEntity {

  }
}
