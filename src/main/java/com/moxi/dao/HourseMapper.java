package com.moxi.dao;

import com.moxi.pojo.house.HouseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yangfeng on 07/01/2021.
 */
@Mapper
public interface HourseMapper {

    /**
     * 根据区域位置查询
     * @param area
     * @return
     */
    List<HouseInfo> selectHouseByArea(@Param("area") String area,
                                      @Param("city") String city,
                                      @Param("date") String date);

    HouseInfo selectHouseByXiaoqu(@Param("xiaoqu") String xiaoqu,
                                  @Param("city") String city,
                                  @Param("date") String date);


}
