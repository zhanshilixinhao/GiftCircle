package com.chouchongkeji.service.mall.item.impl;

import com.chouchongkeji.dial.dao.gift.item.LabelItemMapper;
import com.chouchongkeji.dial.dao.gift.item.LabelMapper;
import com.chouchongkeji.dial.pojo.gift.item.Label;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.query.PageQuery;
import com.chouchongkeji.service.mall.item.LabelService;
import com.chouchongkeji.service.mall.item.vo.ItemListVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author linqin
 * @date 2019/1/10 16:14
 */
@Service
@Transactional(rollbackFor = Exception.class,isolation = Isolation.REPEATABLE_READ)
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelMapper labelMapper;

    @Autowired
    private LabelItemMapper labelItemMapper;

    /**
     * 商城标签列表
     *
     * @return
     * @author linqin
     * @date 2019/1/10 16:13
     */
    @Override
    public Response getLabelList() {
        List<Label> list = labelMapper.selectAll();
        return ResponseFactory.sucData(list);
    }

    /**
     * 获取标签商城列表
     *
     * @param id 标签id
     * @param pageQuery
     * @return
     * @author linqin
     * @date 2019/1/10 16:13
     */
    @Override
    public Response getLabelItemList(Integer id, PageQuery pageQuery) {
        PageHelper.startPage(pageQuery.getPageNum(),pageQuery.getPageSize());
        List<ItemListVo> itemListVos = labelItemMapper.selectByLabelId(id);
        return ResponseFactory.sucData(itemListVos);
    }
}
