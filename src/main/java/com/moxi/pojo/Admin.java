package com.moxi.pojo;

/**
 * 管理者
 * Created by yangfeng on 13/08/2018.
 * @author yangfeng
 */
public class Admin {

    /**
     * 管理员姓名
     */
    private String name;

    /**
     * 管理员类别
     */
    private String kinds;

    /***
     * 管理员编号
     */
     private String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKinds() {
        return kinds;
    }

    public void setKinds(String kinds) {
        this.kinds = kinds;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
