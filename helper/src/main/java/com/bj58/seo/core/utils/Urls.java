package com.bj58.seo.core.utils;

public class Urls {

  /**
   * http://bj.58.com/ --> http://m.58.com/bj/
   * @param pc
   * @return
   */
  public static String pc2M(String pc) {
    String m = pc;
    if (m.indexOf("http://") <= 0) {
      return m;
    }
    
    int bindex = m.indexOf("http://")+7;
    int eindex = m.substring(bindex).indexOf(".") + bindex;
    String local = m.substring(bindex,eindex);
    m = m.replace("http://"+local+".58.com/", "http://m.58.com/"+local+"/");
    return m;
  }
  
  public static void main(String[] args) {
    System.out.println(pc2M("http://bj.58.com/"));
    System.out.println(pc2M("http://abce.58.com/"));
  }

}
