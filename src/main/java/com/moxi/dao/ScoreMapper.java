package com.moxi.dao;

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
     *
     * @param seriesNumber 招考序号
     * @param applyNumber 准考证号码
     * @param positionCode 岗位代码
     * @param scores 分数说明
     * @param score 总分数
     * @param rank 排名
     */
//    void insertScoreInfo(@Param("series_number") String seriesNumber,
//                         @Param("apply_number") String applyNumber,
//                         @Param("position_code") String positionCode,
//                         @Param("scores") String scores,
//                         @Param("total_score") double score,
//                         @Param("rank") int rank);
    void insertScoreInfo(ScoreUpload scoreUpload);
}
