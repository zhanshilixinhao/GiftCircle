package com.chouchongkeji.controller.iwant.setting;

import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.service.iwant.setting.SuggestionService;
import com.github.pagehelper.util.StringUtil;
import com.yichen.auth.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linqin
 * @date 2018/6/7
 */
@RestController
@RequestMapping("auth/v1/suggestion")
public class SuggestionController {

    @Autowired
    private SuggestionService suggestionService;

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
    @PostMapping("/feedback")
    public Response suggestionFeedback(@AuthenticationPrincipal UserDetails userDetails, Integer type,
                                       String feedback, String contactWay) {
        //校验必传参数
        if (type != 1 && type != 2 && type != 3 && type != 4){
            return ResponseFactory.err("意见类型错误");
        }
        if (StringUtil.isEmpty(feedback)){
            return ResponseFactory.err("意见反馈不能为空");
        }
        if (StringUtil.isEmpty(contactWay)){
            return ResponseFactory.err("联系方式不能为空");
        }
       return suggestionService.getSuggestionFeedback(userDetails.getUserId(),type,feedback,contactWay);

    }


}
