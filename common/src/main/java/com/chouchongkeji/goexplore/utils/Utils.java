package com.chouchongkeji.goexplore.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;

/**
 * @author yichenshanren
 * @date 2017/10/9
 */

public class Utils {

    /**
     * 将字符串进行MD5加密
     */
    public static String toMD5(String info) {
        return toMD5(info, true);
    }

    public static String toMD5(String info, boolean toUpperCase) {
        try {
            return toMD5(info.getBytes("UTF-8"), toUpperCase);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String toMD5(byte[] bytes, boolean toUpperCase) {
        try {
            MessageDigest md5 = null;
            md5 = MessageDigest.getInstance("MD5");
            md5.update(bytes);
            byte[] encryption = md5.digest();
            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++) {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                } else {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }
            if (toUpperCase) {
                return strBuf.toString().toUpperCase();
            } else return strBuf.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    public static byte[] toMDBytes(byte[] bytes) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(bytes);
            byte[] encryption = md5.digest();
            return encryption;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }

    /**
     * 字符串转long
     *
     * @param str
     * @return 转换之后的long，失败返回-1
     */
    public static long string2long(String str) {
        long l = -1;
        try {
            l = Long.parseLong(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return l;
    }

    public static int string2int(String str) {
        int i = -1;
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static double string2double(String amount) {
        double d = -1;
        d = Double.parseDouble(amount);
        return 0;
    }

    public static String getPhone(String userName) {
        if (StringUtils.isNotBlank(userName) && userName.length() > 8) {
            return String.format("%s *** %s", userName.substring(0, 3),
                    userName.substring(userName.length() - 4, userName.length()));
        }
        return userName;
    }

    public static boolean getIds(String string, HashSet<Integer> idSet) {
        if (StringUtils.isBlank(string)) return true;
        String[] split = string.split(",");
        return getIds(split, idSet);
    }

    public static boolean getIds(String[] strings, HashSet<Integer> idSet) {
        try {
            Integer id = null;
            for (String idStr : strings) {
                id = Integer.parseInt(idStr);
                idSet.add(id);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    public static boolean getIdsForLong(String string, HashSet<Long> idSet) {
        if (StringUtils.isBlank(string)) return true;
        String[] split = string.split(",");
        return getIdsForLong(split, idSet);
    }

    public static boolean getIdsForLong(String[] strings, HashSet<Long> idSet) {
        try {
            Long id = null;
            for (String idStr : strings) {
                id = Long.parseLong(idStr);
                idSet.add(id);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    public static String toIds(HashSet<Integer> idSet) {
        if (CollectionUtils.isNotEmpty(idSet)) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            for (Integer integer : idSet) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(integer);
                i++;
            }
            return sb.toString();
        }
        return null;
    }
}
