package com.chouchongkeji.service.iwant.setting;

import com.chouchongkeji.goexplore.common.Response;

/**
 * @author linqin
 * @date 2018/6/7
 */

public interface SuggestionService {
    /**
     * 意见反馈
     *
     * @param userDetails
     * @param type        意见类型，1-提个建议，2-程序出错啦，3-不好用，4-其他
     * @param feedback    意见反馈文字
     * @param contactWay  联系方式
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    Response getSuggestionFeedback(Integer userId, Integer type, String feedback, String contactWay);
}
