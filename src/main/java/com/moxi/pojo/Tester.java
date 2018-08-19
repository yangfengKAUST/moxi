package com.moxi.pojo;

/**
 * 普通应试者
 * Created by yangfeng on 13/08/2018.
 * @author yang
 */
public class Tester {

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号码
     */
    private String IDCard;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 考试代号
     */
    private String seriesNumber;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 手机号码
     */
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(String seriesNumber) {
        this.seriesNumber = seriesNumber;
    }
}
