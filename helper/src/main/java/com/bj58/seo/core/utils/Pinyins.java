package com.bj58.seo.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.util.Args;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * Pinyin utilities
 * 
 * @author hui.deng
 *
 */
public class Pinyins {

    /**
     * Full Chinese pinyin
     * 
     * @param cn
     * @return
     */
    public static String fullPinyin(String cn) {
        Args.notNull(cn, "cn");

        String fullPinYin = "";
        for (int i = 0; i < cn.length(); i++) {
            String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(cn.charAt(i));
            if (pinyins != null && pinyins.length > 0) {
                // 去掉声调,多音字取第一个拼音
                Pattern p = Pattern.compile("[0-9]");
                Matcher m = p.matcher(pinyins[0]);
                String pinyin = m.replaceAll("");
                fullPinYin += pinyin;
            }
        }

        return fullPinYin;
    }

    /**
     * Short Chinese pinyin
     * 
     * @param cn
     * @return
     */
    public static String shortPinyin(String cn) {
        Args.notNull(cn, "cn");

        String shortPinYin = "";
        for (int i = 0; i < cn.length(); i++) {
            String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(cn.charAt(i));
            if (pinyins != null && pinyins.length > 0) {
                // 去掉声调,多音字取第一个拼音
                Pattern p = Pattern.compile("[0-9]");
                Matcher m = p.matcher(pinyins[0]);
                String pinyin = m.replaceAll("");
                shortPinYin += pinyin.charAt(0);
            }
        }

        return shortPinYin;
    }
}
