package com.chouchongkeji.service.iwant.wallet;


import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.query.PageQuery;

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
     * 添加用户礼花
     *
     * @param userId
     * @param count
     * @return
     * @author linqin
     * @date 2019/4/3
     */
    Integer addFirework(Integer userId, Integer count);

    /**
     * 添加礼花收益/使用记录
     * @param userId 用户id
     * @param count 礼花数
     * @param des 描述
     * @param targetId 目标id
     * @param type 1 邀请好友，2好友购买商品 3购买商品
     * @return
     */
    Integer addFireworkRecord(Integer userId,Integer count,String des,Integer targetId,Byte type);


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
     * @param userDetails
     * @param starting    开始时间
     * @param ending      截至时间
     * @return
     * @author linqin
     * @date 2019/4/3
     */
    Response earnRecordList(Integer userId, PageQuery pageQuery, Long starting, Long ending);
}
