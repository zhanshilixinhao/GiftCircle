package com.chouchongkeji.service.iwant.wallet;


import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.util.Constants;

/**
 * @author linqin
 * @date 2019/4/3
 */

public interface FireworksService {

    /**
     * 添加邀请好友记录
     *
     * @param userId       被邀请者id
     * @param parentUserId 邀请者id
     * @return
     * @author linqin
     * @date 2019/4/3
     */
    Integer addInviteUser(Integer userId, Integer parentUserId);



    /**
     * 用户礼花详情
     * @param userId
     * @return
     * @author linqin
     * @date 2019/4/3
     */
    Response fireworksDetail(Integer userId);

    /**
     * 我的团队
     * @param userId
     * @return
     * @author linqin
     * @date 2019/4/3
     */
    Response getInviteUserList(Integer userId);

    /**
     * 礼花收益记录
     *
     * @param userId
     * @param starting    开始时间
     * @param ending      截至时间
     * @return
     * @author linqin
     * @date 2019/4/3
     */
    Response earnRecordList(Integer userId, PageQuery pageQuery, Long starting, Long ending);


    /**
     * 礼花剩余数量
     *
     * @param userId
     * @return
     * @author linqin
     * @date 2019/4/3
     */
    Response numberRemaining(Integer userId);

    /**
     * 添加礼花/添加记录
     * @param userId
     * @param count
     * @param type
     * @param des
     * @param targetId
     * @return
     */
    int updateFireworks(Integer userId, Integer count, Constants.FIREWORKS_RECORD type, String des, Integer targetId);
}
