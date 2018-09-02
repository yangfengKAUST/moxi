package com.moxi.dao;

import com.moxi.pojo.ScoreParam;
import com.moxi.pojo.ScoreUpload;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by yangfeng on 28/08/2018.
 * @author yangfeng
 * 插入考分的信息
 */
@Mapper
public interface ScoreMapper {

    /**
     *›
     */
    void insertScoreInfo(ScoreUpload scoreUpload);

    /**
     * 上传是否存在该记录
     * @return
     */
    int ifExist(ScoreParam scoreParam);

    /**
     * 根据准考证号获取考分信息
     * @param apply_number
     * @return
     */
    ScoreUpload getScoreInformation(@Param("apply_number") String apply_number);
}
