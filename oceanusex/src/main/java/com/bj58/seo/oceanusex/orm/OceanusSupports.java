package com.bj58.seo.oceanusex.orm;

import java.util.Map;

import com.bj58.oceanus.client.Oceanus;
import com.bj58.oceanus.client.orm.OceanusSupportImpl;
import com.bj58.seo.oceanusex.dal.OceanusEntity;
import com.google.common.collect.Maps;

/**
 * Manage all singleton <code>OceanusSupport</code> interface instances
 * 
 * @author hui.deng
 *
 */
public class OceanusSupports {
  private static Map<Class<? extends OceanusEntity>, OceanusSupport<? extends OceanusEntity>> implMap;

  static {
    implMap = Maps.newHashMap();
  }

  /**
   * Get singleton implementation of <code>OceanusSupport</code> for specified class. 
   * Should call <code>init()</code> to init Oceanus first.
   * 
   * @param clazz
   * @return
   */
  @SuppressWarnings("unchecked")
  public static <T extends OceanusEntity> OceanusSupport<T> get(Class<T> clazz) {
    Oceanus.checkInit();

    OceanusSupport<?> support = implMap.get(clazz);
    if (support == null) {
      synchronized (OceanusSupports.class) {
        support = implMap.get(clazz);
        if (support == null) {
          support = new OceanusSupportImpl<T>(clazz);
          implMap.put(clazz, support);
        }
      }
    }

    return (OceanusSupport<T>) support;
  }
}
