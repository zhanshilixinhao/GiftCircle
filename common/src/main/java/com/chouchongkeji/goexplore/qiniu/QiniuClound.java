package com.chouchongkeji.goexplore.qiniu;

import com.qiniu.config.Config;
import com.qiniu.util.StringMap;

/**
 * @Author: ZhouYZ
 * @Description: Created by zhouyz on 2018/2/5.
 * @Modified By:
 */
public class QiniuClound {
    /**
     * scope = bucket
     * 一般情况下可通过此方法获取token
     *
     * @param bucket 空间名
     * @return 生成的上传token
     */
    public static String uploadToken(String bucket) {
        return Config.testAuth.uploadToken(bucket);
    }
    /**
     * scope = bucket:key
     * 同名文件覆盖操作、只能上传指定key的文件可以可通过此方法获取token
     *
     * @param bucket 空间名
     * @param key    key，可为 null
     * @return 生成的上传token
     */
    public static String uploadToken(String bucket, String key) {
        return Config.testAuth.uploadToken(bucket, key, 3600, null, true);
    }
    /**
     * 生成上传token
     *
     * @param bucket  空间名
     * @param key     key，可为 null
     * @param expires 有效时长，单位秒
     * @param policy  上传策略的其它参数，如 new StringMap().put("endUser", "uid").putNotEmpty("returnBody", "")。
     *                scope通过 bucket、key间接设置，deadline 通过 expires 间接设置
     * @return 生成的上传token
     */
    public static String uploadToken(String bucket, String key, long expires, StringMap policy) {
        return Config.testAuth.uploadToken(bucket, key, expires, policy, true);
    }
    /**
     * 生成上传token
     *
     * @param bucket  空间名
     * @param key     key，可为 null
     * @param expires 有效时长，单位秒。默认3600s
     * @param policy  上传策略的其它参数，如 new StringMap().put("endUser", "uid").putNotEmpty("returnBody", "")。
     *                scope通过 bucket、key间接设置，deadline 通过 expires 间接设置
     * @param strict  是否去除非限定的策略字段，默认true
     * @return 生成的上传token
     */
    public static String uploadToken(String bucket, String key, long expires, StringMap policy, boolean strict) {
        long deadline = System.currentTimeMillis() / 1000 + expires;
        return  Config.testAuth.uploadTokenWithDeadline(bucket, key, deadline, policy, strict);
    }
}

