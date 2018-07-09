package com.chouchongkeji.service.mall.virtualItem.vo;

/**
 * 用于虚拟商品的分类列表显示
 *
 * @author yy
 * @date 2018/6/11
 **/
public class VirtualItemCateVo {
    // 虚拟商品分类id
    private Integer id;
    //  虚拟商品分类名称
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
