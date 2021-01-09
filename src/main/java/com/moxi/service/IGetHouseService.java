package com.moxi.service;

import com.moxi.pojo.house.HouseInfo;

import java.util.List;

/**
 * Created by yangfeng on 09/01/2021.
 */
public interface IGetHouseService {

    HouseInfo getHouseByXiaoqu(String xiaoqu, String city);

    List<HouseInfo> getHouseByArea(String area, String city);
}
