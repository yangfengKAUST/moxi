package com.moxi.service.Implement;

import com.moxi.dao.HourseMapper;
import com.moxi.pojo.house.HouseInfo;
import com.moxi.service.IGetHouseService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yangfeng on 09/01/2021.
 */
@Service
public class GetHouseServiceImpl implements IGetHouseService{

    Logger logger = LoggerFactory.getLogger(KnowledgeServiceImpl.class);

    @Autowired
    HourseMapper hourseMapper;

    @Override
    public HouseInfo getHouseByXiaoqu(String xiaoqu, String city) {
        city = "上海";
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(d);
        HouseInfo houseInfo = hourseMapper.selectHouseByXiaoqu(xiaoqu, city, date);
        return houseInfo;
    }

    @Override
    public List<HouseInfo> getHouseByArea(String area, String city) {
        city = "上海";
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(d);
        List<HouseInfo> houseInfoList = hourseMapper.selectHouseByArea(area, city, date);
        return houseInfoList;
    }

}
