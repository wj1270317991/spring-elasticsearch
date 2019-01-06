package com.spring.es.springes.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

import java.time.LocalDate;

/**
 * Created by wangjun on 2019/1/6.
 */
@Document(indexName = "v_intelligent_store")
public class IntelligentStore {
    @Id
    private String id;
    /** 体验店名称 */
    private String name;
    /** 联系人姓名 */
    private String contactsName;
    /** 联系手机号 */
    private String phoneNumber;
    /** 联系电话 */
    private String telePhone;
    /** 场景分类 */
    private String category;
    /** 开店时间 */
    private LocalDate foundTime;
    /** 营业时间 */
    private String openHours;
    /** 主营产品 */
    private String mainProduct;
    /** 店铺面积 */
    private Double shopSize;
    /** xx省xx市xx区 */
    private String area;
    /** 店铺面积 */
    private String detailAddress;
    /** 经维度，中间逗号隔开 */
    @GeoPointField
    private String location;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getFoundTime() {
        return foundTime;
    }

    public void setFoundTime(LocalDate foundTime) {
        this.foundTime = foundTime;
    }

    public String getOpenHours() {
        return openHours;
    }

    public void setOpenHours(String openHours) {
        this.openHours = openHours;
    }

    public String getMainProduct() {
        return mainProduct;
    }

    public void setMainProduct(String mainProduct) {
        this.mainProduct = mainProduct;
    }

    public Double getShopSize() {
        return shopSize;
    }

    public void setShopSize(Double shopSize) {
        this.shopSize = shopSize;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
