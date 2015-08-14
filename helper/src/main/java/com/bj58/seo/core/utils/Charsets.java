package com.bj58.seo.core.utils;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符集相关工具类
 * 
 * @author hui.deng
 *
 */
public class Charsets {

    /**
     * 从字符串中移除4字节utf8编码字符
     * 
     * @param str
     * @return
     * @throws Exception
     */
    public static String remove4BytesUTF8Char(String str) throws Exception {
        // 参考: http://blog.csdn.net/wskings/article/details/12857993
        byte[] bb = str.getBytes("utf-8");
        byte[] cc = new byte[bb.length];
        int j = 0;
        for (int i = 0; i < bb.length; i++) {
            if ((bb[i] & 0xF8) == 0xF0) {
                i += 3;
            } else {
                cc[j++] = bb[i];
            }
        }
        return new String(Arrays.copyOf(cc, j), Charset.forName("utf-8"));
    }

    /**
     * 去除HTML模式
     * 
     * @param html
     * @return
     */
    public static String removeHtml(String html) {
        String content = html;

        String[][] replacements = new String[][] {
                // 去除HTML模式
                new String[] {"&(amp|#38);", "&"}, // 去掉&
                new String[] {"&(lt|#60);", "<"}, // 去掉<
                new String[] {"&(gt|#62);", ">"}, // 去掉>
                new String[] {"&(nbsp|#160);", ""}, // 去掉空格
                new String[] {"&(quot|#34);", "\""}, // 去掉"
                new String[] {"&(iexcl|#161);", "\\xa1"}, // 去掉¡
                new String[] {"&(cent|#162);", "\\xa2"}, // 去掉¢
                new String[] {"&(pound|#163);", "\\xa3"}, // 去掉£
                new String[] {"&(copy|#169);", "\\xa9"}, // 去掉©
                new String[] {"(\\s*(\r\n)\\s*)+", "\r\n"}, // 将多个回车换行整理为1个
                new String[] {"[\r\n\t]", "#nowrap#"}, // 去除回车、换行、制表符
                new String[] {"<style[^>]*?>.*?</style>", ""}, // 去除CSS
                new String[] {"<!--.*?-->", ""}, // 去除注释
                new String[] {"<([^>]*?)>", ""}, // 去除Html标签代码，<([^>]*?)+>存在引起CPU100%问题，修改为<([^>]*?)>
        };

        for (String[] rep : replacements) {
            Matcher matcher = Pattern.compile(rep[0]).matcher(content);
            content = matcher.replaceAll(rep[1]);
        }

        return content;
    }

    public static void main(String[] args) throws Exception {
        String t = "𠂇TR自行车";
        System.out.println(remove4BytesUTF8Char(t));
        System.out.println(remove4BytesUTF8Char("0000"));
        System.out.println(removeHtml("   <div> fas </div>"));
    }
}
