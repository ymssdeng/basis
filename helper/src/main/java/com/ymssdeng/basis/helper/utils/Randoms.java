package com.ymssdeng.basis.helper.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.util.Args;

import com.google.common.collect.Lists;

/**
 * Random utilities
 * 
 * @author hui.deng
 *
 */
public class Randoms {

  /**
   * Random select limit counts from collection
   * 
   * @param col
   * @param limit
   * @return
   */
  public static <E> Collection<E> rand(Collection<E> col, int limit) {
    Args.notNull(col, "col");
    Args.positive(limit, "limit");
    if (col.size() == 0) return col;

    List<E> list = new ArrayList<E>(col);
    Collections.shuffle(list);

    Collection<E> ret;
    if (col instanceof List) {
      ret = new ArrayList<E>(limit);
    } else if (col instanceof Set) {
      ret = new HashSet<E>(limit);
    } else {
      throw new IllegalArgumentException("unsupported collection");
    }

    for (E e : list) {
      ret.add(e);
      if (ret.size() >= limit) break;
    }

    return ret;
  }

  /**
   * Random select limit counts from collection, but excludes specified elements
   * 
   * @param col
   * @param limit
   * @param excludes
   * @return
   */
  public static <E> Collection<E> rand(Collection<E> col, int limit, E... excludes) {
    Args.notNull(col, "col");
    Args.positive(limit, "limit");
    if (col.size() == 0) return col;

    List<E> list = new ArrayList<E>();
    List<E> excludesLst = Lists.newArrayList(excludes);
    for (E e : col) {
      if (!excludesLst.contains(e)) list.add(e);
    }
    Collections.shuffle(list);

    Collection<E> ret;
    if (col instanceof List) {
      ret = new ArrayList<E>(limit);
    } else if (col instanceof Set) {
      ret = new HashSet<E>(limit);
    } else {
      throw new IllegalArgumentException("unsupported collection");
    }

    for (E e : list) {
      ret.add(e);
      if (ret.size() >= limit) break;
    }

    return ret;
  }
}
