package com.bj58.seo.core.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.util.Args;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
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
    if (col.size() == 0 || col.size() <= limit) return col;

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

  public static <E> Collection<E> rand(Collection<E> col, int limit, final E... excludes) {
    Args.notNull(col, "col");
    Args.positive(limit, "limit");

    Collection<E> tmp = Collections2.filter(col, new Predicate<E>() {

      @Override
      public boolean apply(E input) {
        for (E e : excludes) {
          if (e.equals(input)) return false;
        }
        return true;
      }

    });

    List<E> list = new ArrayList<E>(tmp);
    return rand(list, limit);
  }

  /**
   * 将列表分组，每组最多num个
   * 
   * @param lst
   * @param num
   * @return
   */
  public static <E> List<List<E>> group(List<E> lst, int num) {
    Args.notNull(lst, "lst");
    Args.positive(num, "num");

    // 可以分g组
    int g = lst.size() / num;
    if (lst.size() % num > 0) {
      g++;
    }

    List<List<E>> ret = Lists.newArrayList();
    for (int i = 0; i < g; i++) {
      ret.add(lst.subList(i * num, Math.min((i + 1) * num, lst.size())));
    }

    return ret;
  }
}
