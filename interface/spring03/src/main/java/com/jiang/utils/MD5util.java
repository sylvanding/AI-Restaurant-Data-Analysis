package com.jiang.utils;

import org.apache.commons.codec.digest.DigestUtils;
/**
 * MD5工具类
 */
public class MD5util {
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    private static final String salt="whatfuck";
    public static String inputPassToFormPass(String inputPass){
        String str = ""+salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass,String salt){
        String str = ""+salt.charAt(0)+salt.charAt(2)+formPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDBPass(String inputPass,String salt){
        String fromPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(fromPass,salt);
        return dbPass;
    }

    //测试加密功能
    public static void main(String[] args) {
        System.out.println(inputPassToFormPass("123456"));
        System.out.println(formPassToDBPass("123456","whatfuck"));
        System.out.println(inputPassToDBPass("123456","whatfuck"));
    }
}
