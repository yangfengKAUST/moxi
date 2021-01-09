package com.moxi.pojo.house;

/**
 * Created by yangfeng on 09/01/2021.
 */
public class HouseReturnFormat {

    private String info;

    private boolean status;

//    private String houseInfo;

    private HouseInfo houseInfo;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

//    public String isHouseInfo() {
//        return houseInfo;
//    }
//
//    public void setHouseInfo(String  houseInfo) {
//        this.houseInfo = houseInfo;
//    }


    public HouseInfo getHouseInfo() {
        return houseInfo;
    }

    public void setHouseInfo(HouseInfo houseInfo) {
        this.houseInfo = houseInfo;
    }
}
