package com.chouchongkeji.service.iwant.merchant.vo;

import java.util.Date;
import java.util.List;

/**
 * @author yy
 * @date 2018/6/20
 **/
public class MerchantVo {
    private Integer id;
    // 企业名称
    private String name;
    // 用户id
    private Integer userId;
    // 企业地址
    private String address;
    // 企业注册号
    private String registrationNo;
    // 法人代表
    private String legalPerson;
    // 联系电话
    private String phone;
    // 营业执照照片
    private String licensePic;
    // 其他证件图片
    private List<String> otherPics;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLicensePic() {
        return licensePic;
    }

    public void setLicensePic(String licensePic) {
        this.licensePic = licensePic;
    }

    public List<String> getOtherPics() {
        return otherPics;
    }

    public void setOtherPics(List<String> otherPics) {
        this.otherPics = otherPics;
    }
}
