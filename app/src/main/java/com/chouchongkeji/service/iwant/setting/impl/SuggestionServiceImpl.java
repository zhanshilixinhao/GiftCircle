package com.chouchongkeji.service.iwant.setting.impl;

import com.chouchongkeji.dial.dao.iwant.setting.SuggestionMapper;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.dial.pojo.iwant.setting.Suggestion;
import com.chouchongkeji.service.iwant.setting.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linqin
 * @date 2018/6/7
 */
@Service
public class SuggestionServiceImpl implements SuggestionService {

    @Autowired
    private SuggestionMapper suggestionMapper;

    /**
     * 意见反馈
     *
     * @param type        意见类型，1-提个建议，2-程序出错啦，3-不好用，4-其他
     * @param feedback    意见反馈文字
     * @param contactWay  联系方式
     * @return
     * @author linqin
     * @date 2018/6/7
     */
    @Override
    public Response getSuggestionFeedback(Integer userId, Integer type, String feedback, String contactWay) {
        Suggestion suggestion = new Suggestion();
        suggestion.setUserId(userId);
        suggestion.setType(type.byteValue());
        suggestion.setFeedback(feedback);
        suggestion.setContactWay(contactWay);
        suggestionMapper.insert(suggestion);
        return ResponseFactory.sucMsg("反馈成功");
    }
}
