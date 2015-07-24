package com.mdeng.oceanusex.orm;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bj58.oceanus.client.Oceanus;
import com.bj58.oceanus.client.orm.OceanusSupportImpl;
import com.bj58.oceanus.core.exception.ConfigurationException;
import com.google.common.collect.Maps;
import com.mdeng.oceanusex.dal.OceanusEntity;

/**
 * Manage all singleton <code>OceanusSupport</code> interface instances
 * 
 * @author hui.deng
 *
 */
public class OceanusSupports {
  private static Logger log = LoggerFactory.getLogger(OceanusSupports.class);
  private static Map<Class<? extends OceanusEntity>, OceanusSupport<? extends OceanusEntity>> implMap;

  static {
    implMap = Maps.newHashMap();
  }

  /**
   * Init Oceanus framework
   * 
   * @param configPath
   */
  public static void init(String configPath) {
    try {
      Oceanus.checkInit();
    } catch (ConfigurationException e) {
      try {
        Oceanus.init(configPath);
        log.info("Oceanus init using " + configPath);
      } catch (ConfigurationException e2) {
        log.warn(e2.getMessage());
      }
    }
  }

  /**
   * Get singleton implementation of <code>OceanusSupport</code> for specified class. 
   * Should call <code>init()</code> to init Oceanus first.
   * 
   * @param clazz
   * @return
   */
  @SuppressWarnings("unchecked")
  public static <T extends OceanusEntity> OceanusSupport<T> getSupport(Class<T> clazz) {
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
