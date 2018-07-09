package com.chouchongkeji.service.mall.item.impl;

import com.chouchongkeji.dial.dao.gift.item.CartMapper;
import com.chouchongkeji.dial.dao.gift.item.ItemSkuMapper;
import com.chouchongkeji.goexplore.common.ResponseFactory;
import com.chouchongkeji.goexplore.common.Response;
import com.chouchongkeji.dial.pojo.gift.item.Cart;
import com.chouchongkeji.dial.pojo.gift.item.ItemSku;
import com.chouchongkeji.service.mall.item.CartService;
import com.chouchongkeji.service.mall.item.vo.CartListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linqin
 * @date 2018/6/13
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ItemSkuMapper itemSkuMapper;

    /**
     * 购物车列表
     *
     * @param userId
     * @return
     * @author linqin
     * @date 2018/6/13
     */
    @Override
    public Response getCartList(Integer userId) {
        List<CartListVo> cart = cartMapper.selectByUserId(userId);

        return ResponseFactory.sucData(cart);
    }


    /**
     * 商品加入购物车
     *
     * @param userId
     * @param skuId
     * @return
     * @author linqin
     * @date 2018/6/13
     */
    @Override
    public Response addItemToCart(Integer userId, Integer skuId) {
        Cart cartSku = cartMapper.selectBySkuIAndUserId(userId, skuId);

        ItemSku itemSku = itemSkuMapper.selectBySkuId(skuId);
        if (itemSku == null) {
            return ResponseFactory.errMissingParameter();
        }
        //校验cartSku是否存在
        if (cartSku == null) {
            //如果不存在插入一条数据
            if (itemSku.getStock() < 1) {
                return ResponseFactory.err("库存不足,无法加入购物车");
            }
            cartSku = new Cart();
            cartSku.setUserId(userId);
            cartSku.setItemId(itemSku.getItemId());
            cartSku.setSkuId(itemSku.getId());
            cartSku.setPrice(itemSku.getPrice());
            cartSku.setQuantity(1);
            cartMapper.insert(cartSku);
            return ResponseFactory.sucMsg("加入购物车成功");
        }
        if (itemSku.getStock() <= cartSku.getQuantity()) {
            return ResponseFactory.err("库存不足,无法加入购物车");
        }
        cartSku.setQuantity(cartSku.getQuantity() + 1);
        cartMapper.updateByPrimaryKeySelective(cartSku);
        return ResponseFactory.sucMsg("加入购物车成功");
    }

    /**
     * 增加或减少购物车商品数量
     ** @param skuId 商品最小销售单元id
     * @param userId 用户id
     * @param change 增加或减少商品数量
     * @return
     * @author linqin
     * @date 2018/6/13
     */
    @Override
    public Response changeQuantity(Integer userId, Integer skuId,Integer change) {
        //取出购物车数据
        Cart cartSku = cartMapper.selectBySkuIAndUserId(userId, skuId);
        //校验cartSku是否存在
        if (cartSku == null){
            return ResponseFactory.err("商品不存在");
        }
        //取出商品库存
        ItemSku itemSku = itemSkuMapper.selectBySkuId(skuId);
        if (itemSku == null) {
            return ResponseFactory.errMissingParameter();
        }
        //比较商品数量和库存
       int count =  cartSku.getQuantity()+change;
       //判断商品修改后数量是否小于1
        if (count<1){
            //小于1，返回错误
            return ResponseFactory.err("修改后的数量不能小于1");
        }
        //判断商品修改后数量是否大于商品库存
        if (count>itemSku.getStock()){
            return ResponseFactory.err("修改后数量不能大于商品库存");
        }
        Cart cart = new Cart();
        cart.setId(cartSku.getId());
        cart.setQuantity(count);
        cartMapper.updateByPrimaryKeySelective(cart);
        return ResponseFactory.suc();
    }

    /**
     * 删除购物车商品
     *
     * @param userId
     * @param skuId 商品最小销售单元id
     * @return
     * @author linqin
     * @date 2018/6/13
     */
    @Override
    public Response deleteItem(Integer userId, Integer skuId) {
        int count = cartMapper.deleteByUserIdAndskuId(userId, skuId);
        if (count == 0){
            return ResponseFactory.err("删除失败");
        }

        return ResponseFactory.sucMsg("删除成功");
    }
}
