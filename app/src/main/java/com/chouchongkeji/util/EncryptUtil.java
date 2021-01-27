package com.chouchongkeji.util;

import com.gexin.rp.sdk.base.uitls.MD5Util;

import java.security.MessageDigest;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/10/19 13:45
 */
public class EncryptUtil {
    /**
     * 创建MD5字段
     *
     * @param value
     * @return
     * @throws Exception
     */
    public static String md5(String value) {

        String temp = null;
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(value.getBytes());
            byte[] digest = md5.digest();
            temp = byte2hex(digest);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return temp;
    }

    /**
     *
     * @param bytes
     * @return
     */
    private static String byte2hex(byte[] bytes) {
        String hs = "";
        String stmp = "";
        for (int i = 0; i < bytes.length; i++) {
            stmp = (Integer.toHexString(bytes[i] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    public static void main(String args[]) {
        String value = EncryptUtil.md5("123456");
        System.out.println("value : " + value);
    }

    /**
     * 创建特殊MD5字段
     *
     * @param value
     * @return
     * @throws Exception
     */
    public static String myMd5(String value) {

        String temp = null;
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(value.getBytes("UTF-8"));
            byte[] digest = md5.digest();
            temp = byte2MyHex(digest);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return temp;
    }

    /**
     *
     * @param bytes
     * @return
     */
    private static String byte2MyHex(byte[] bytes) {
        String hs = "hzhl";
        String stmp = "";
        for (int i = 0; i < bytes.length; i++) {
            stmp = (Integer.toHexString(bytes[i] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "1" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }
}
