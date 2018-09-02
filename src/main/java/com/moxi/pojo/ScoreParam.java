package com.moxi.pojo;

/**
 * Created by yangfeng on 02/09/2018.
 * 部分的成绩查询功能
 */
public class ScoreParam {

    /**
     * 招考序号
     */
    private String series_number;

    /**
     * 准考证号
     */
    private String apply_number;

    /**
     * 岗位代码
     */
    private String position_code;

    public String getSeries_number() {
        return series_number;
    }

    public void setSeries_number(String series_number) {
        this.series_number = series_number;
    }

    public String getApply_number() {
        return apply_number;
    }

    public void setApply_number(String apply_number) {
        this.apply_number = apply_number;
    }

    public String getPosition_code() {
        return position_code;
    }

    public void setPosition_code(String position_code) {
        this.position_code = position_code;
    }
}
