package com.chouchongkeji.goexplore.utils;

import com.chouchongkeji.goexplore.pay.KeyUtil;
import com.chouchongkeji.goexplore.pay.alipay.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  * RSA加密与解密,采用Base64编码
 *  *
 *  * @author East271536394
 *  * @version 2012-6-25 上午9:18:57
 *  
 */
public class RSAProvider {

    /**
     * KEY_ALGORITHM
     */
    public static final String KEY_ALGORITHM = "RSA";
    /**
     * 加密Key的长度等于1024或者2048
     */
    public static int KEYSIZE = 2048;
    /**
     * 解密时必须按照此分组解密
     */
    public static int decodeLen = KEYSIZE / 8;
    /**
     * 加密时小于117即可
     */
    public static int encodeLen = 234;
    /**
     * 公钥
     */
    private static final String PUBLIC_KEY = "publicKey";
    /**
     * 私钥
     */
    private static final String PRIVATE_KEY = "privateKey";
    /**
     * MODULES
     */
    private static final String MODULES = "RSAModules";

    /**
     * 生成KeyPair
     *
     * @return
     * @throws Exception
     * @author East271536394
     */
    public static Map<String, Object> generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(KEYSIZE);
        KeyPair keyPair = keyPairGen.generateKeyPair();

        // 公钥   
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        // 私钥   
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        BigInteger modules = privateKey.getModulus();

        Map<String, Object> keys = new HashMap<String, Object>(3);
        keys.put(PUBLIC_KEY, publicKey);
        keys.put(PRIVATE_KEY, privateKey);
        keys.put(MODULES, modules);
        return keys;
    }

    /**
     * 用私钥加密
     *
     * @param text 要加密的文本
     * @param key  加密用的私钥
     * @return 加密之后的字符串
     * @throws Exception
     */

    public static String encrypt(String text, String key) {
        byte[] bytes = new byte[0];
        if (StringUtils.isAnyBlank(text, key)) {
            return "";
        }
        try {
            // 对密钥解密   
            // 取得私钥  
            byte[] keyBytes = Base64.decode(key);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

            // 对数据加密   
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            // 对字符串进行分组
            int start = 0;
            // 默认选择200个字
            int len = 200;
            // 要加密字符串的长度
            int textLen = text.length();
            // 每次取出的字符串
            String temp = "";
            // 循环结束条件
            StringBuffer sb = new StringBuffer();
            List<Integer> pointer = new ArrayList<>();
            while (start < textLen) {
                do {
                    temp = StringUtils.substring(text, start, start + len);
                    bytes = temp.getBytes("UTF-8");
                    // 如果太长 减去10
                    len -= 10;
                } while (bytes.length > encodeLen);
                bytes = cipher.doFinal(bytes);
                temp = Base64.encode(bytes);
                sb.append(temp);
                pointer.add(temp.length());
                // 计算已加密的长度
                len += 10;
                start += len;
                len = 200;
            }
            // 加入每段的长度
            if (CollectionUtils.isNotEmpty(pointer)) {
                sb.append("||");
                int i = 0;
                for (Integer p : pointer) {
                    if (i > 0) {
                        sb.append(",");
                    }
                    sb.append(p);
                    i++;
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param text 需要解密的字符串
     * @return 解密之后的字符串
     */

    public static String decrypt(String text, String key) {
        if (StringUtils.isAnyBlank(text, key)) {
            return "";
        }
        String[] str = text.split("\\|\\|");
        if (str.length != 2) return "error";
        text = str[0];
        String[] pointers = str[1].split(",");
        if (StringUtils.isBlank(text) || ArrayUtils.isEmpty(pointers)) return "error";
        try {
            // 对密钥解密   取得公钥
            byte[] keyBytes = Base64.decode(key);
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key publicKey = keyFactory.generatePublic(x509KeySpec);

            // 对数据解密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            byte[] bytes;
            String temp = "";
            int start = 0;
            int len = 0;
            StringBuffer sb = new StringBuffer();
            for (String po : pointers) {
                // 获取分段长度
                len = Utils.string2int(po);
                // 如果分段长度错误直接返回
                if (len == -1) return "error";
                // 获取分段字符串
                temp = StringUtils.substring(text, start, start + len);
                // 对字符串进行base64解码
                bytes = Base64.decode(temp);
                // 对字节数据解密
                bytes = cipher.doFinal(bytes);
                sb.append(new String(bytes, "UTF-8"));
                start += len;
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密private key
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     * @author East271536394
     */
    public static byte[] encryptPrivateKey(String data, String key) throws Exception {
        byte[] bytes = data.getBytes("UTF-8");
        byte[] encode = new byte[]{};
        for (int i = 0; i < bytes.length; i += encodeLen) {
            byte[] subarray = ArrayUtils.subarray(bytes, i, i + encodeLen);
            byte[] doFinal = encryptByPrivateKey(subarray, key);
            encode = ArrayUtils.addAll(encode, doFinal);
        }
        return encode;
    }

    /**
     * 解密 public key
     * 方法说明。
     *
     * @param encode
     * @param key
     * @return
     * @throws Exception
     * @author East271536394
     */
    public static String decryptPublicKey(byte[] encode, String key) throws Exception {
        byte[] buffers = new byte[]{};
        for (int i = 0; i < encode.length; i += decodeLen) {
            byte[] subarray = ArrayUtils.subarray(encode, i, i + decodeLen);
            byte[] doFinal = decryptByPublicKey(subarray, key);
            buffers = ArrayUtils.addAll(buffers, doFinal);
        }
        return new String(buffers, "UTF-8");
    }

    /**
     * 加密public key
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     * @author East271536394
     */
    public static byte[] encryptPublicKey(String data, String key) throws Exception {
        byte[] bytes = data.getBytes("UTF-8");
        byte[] encode = new byte[]{};
        for (int i = 0; i < bytes.length; i += encodeLen) {
            byte[] subarray = ArrayUtils.subarray(bytes, i, i + encodeLen);
            byte[] doFinal = encryptByPublicKey(subarray, key);
            encode = ArrayUtils.addAll(encode, doFinal);
        }
        return encode;
    }

    public static String decrypt(String text) {
        try {
            return new String(decryptByPrivateKey(Base64.decode(text), KeyUtil.PRIVATE_KEY));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密 private key
     * 方法说明。
     *
     * @param encode
     * @param key
     * @return
     * @throws Exception
     * @author East271536394
     */
    public static String decryptPrivateKey(byte[] encode, String key) throws Exception {
        byte[] buffers = new byte[]{};
        for (int i = 0; i < encode.length; i += decodeLen) {
            byte[] subarray = ArrayUtils.subarray(encode, i, i + decodeLen);
            byte[] doFinal = decryptByPrivateKey(subarray, key);
            buffers = ArrayUtils.addAll(buffers, doFinal);
        }
        return new String(buffers, "UTF-8");
    }

    /**
     * 用私钥解密
     *
     * @param data
     * @param keyBytes
     * @return
     * @throws Exception
     * @author East271536394
     */
    private static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception {
        // 对密钥解密  取得私钥  
        byte[] keyBytes = Base64.decode(key);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 对数据解密   
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     *  
     * 用公钥解密
     *
     * @param data
     * @param keyBytes
     * @return
     * @throws Exception
     * @author East271536394
     */
    private static byte[] decryptByPublicKey(byte[] data, String key) throws Exception {
        // 对密钥解密   取得公钥
        byte[] keyBytes = Base64.decode(key);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    /**
     * 用公钥加密
     *
     * @param data
     * @param keyBytes
     * @return
     * @throws Exception
     * @author East271536394
     */
    private static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {
        // 对公钥解密   
        // 取得公钥  
        byte[] keyBytes = Base64.decode(key);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据加密   
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    public static String encrypt(String text) {
        try {
            return Base64.encode(encryptByPublicKey(text.getBytes(), KeyUtil.PUBLIICK_KEY));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用私钥加密
     *
     * @param data
     * @param keyBytes
     * @return
     * @throws Exception
     * @author East271536394
     */
    private static byte[] encryptByPrivateKey(byte[] data, String key) throws Exception {
        // 对密钥解密   
        // 取得私钥  
        byte[] keyBytes = Base64.decode(key);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 对数据加密   
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * @return
     * @author East271536394
     */
    public static byte[] getModulesBytes(Map<String, Object> keys) {
        BigInteger big = (BigInteger) keys.get(MODULES);
        return big.toByteArray();

    }

    /**
     * 取得私钥
     *
     * @return
     * @throws Exception
     * @author East271536394
     */
    public static String getPrivateKeyBytes(Map<String, Object> keys) throws Exception {
        Key key = (Key) keys.get(PRIVATE_KEY);
        return Base64.encode(key.getEncoded());
    }

    /**
     * 取得公钥
     *
     * @return
     * @throws Exception
     * @author East271536394
     */
    public static String getPublicKeyBytes(Map<String, Object> keys) throws Exception {
        Key key = (Key) keys.get(PUBLIC_KEY);
        return Base64.encode(key.getEncoded());
    }

//    public static class Base64 {
//        /**
//         * 转为base64
//         *
//         * @param s 原始字符串
//         * @return String base64
//         */
//        public static String getBASE64(String s) {
//            if (s == null)
//                return null;
//            try {
//                return (new BASE64Encoder()).encode(s.getBytes("UTF-8"));
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//
//        /**
//         * 从base64还原
//         *
//         * @param s base64串
//         * @return String 原始串
//         */
//        public static String getFromBASE64(String s) {
//            if (s == null)
//                return null;
//            BASE64Decoder decoder = new BASE64Decoder();
//            try {
//                byte[] b = decoder.decodeBuffer(s);
//                return new String(b, "UTF-8");
//            } catch (Exception e) {
//                return null;
//            }
//        }
//
//        /**
//         * 从byte转到base64
//         *
//         * @param bs 原始串
//         * @return String base64串
//         */
//        public static String byteToBase64(byte[] bs) {
//            BASE64Encoder encoder = new BASE64Encoder();
//            return encoder.encode(bs);
//        }
//
//        /**
//         * 从base64转到byte
//         *
//         * @param base64 base64串
//         * @return byte 原始串
//         */
//        public static byte[] base64ToByte(String base64) {
//            BASE64Decoder decoder = new BASE64Decoder();
//            try {
//                return decoder.decodeBuffer(base64);
//            } catch (IOException e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//    }

    public static void main(String[] args) {
        String text = "飞洒地方435tfgdsgfsd";
        String en = encrypt(text, KeyUtil.PRIVATE_KEY);
//        System.out.println(en);
        String de = decrypt(en, KeyUtil.PUBLIICK_KEY);
//        System.out.println(de);
        System.out.println(KeyUtil.PUBLIICK_KEY);
    }

}
