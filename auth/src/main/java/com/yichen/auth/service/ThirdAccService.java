package com.yichen.auth.service;

import com.chouchongkeji.goexplore.common.Response;
import com.yichen.auth.model.ThirdAccDetail;

/**
 * 第三方账号操作
 *
 * @author yichenshanren
 * @date 2017/11/30
 */

public interface ThirdAccService {

    /**
     * 根据openId和type获取第三方账号信息
     *
     * @param openId openId
     * @param type   账号类型
     * @return
     * @author yichenshanren
     * @date 2017/11/30
     */
    ThirdAccDetail getOpenAccDetail(String openId, int type);

    /**
     * 添加一个第三方账号
     *
     * @param openId
     * @param type
     * @param userName
     * @return
     * @author yichenshanren
     * @date 2017/11/30
     */
    Response addOpenAccDetail(String openId, int type, String userName);

}
