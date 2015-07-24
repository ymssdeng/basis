package com.ymssdeng.basis.helper.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Copy utilities
 * 
 * @author hui.deng
 *
 */
public class Copys {

  /**
   * Deep copy an object
   * 
   * @param t
   * @return
   * @throws IOException
   * @throws ClassNotFoundException
   */
  @SuppressWarnings("unchecked")
  public static <T> T deepCopy(T t) throws IOException, ClassNotFoundException {
    ByteArrayOutputStream bo = new ByteArrayOutputStream();
    ObjectOutputStream oo = new ObjectOutputStream(bo);
    oo.writeObject(t);
    ObjectInputStream oi = new ObjectInputStream(new ByteArrayInputStream(bo.toByteArray()));
    return (T) oi.readObject();
  }
}
