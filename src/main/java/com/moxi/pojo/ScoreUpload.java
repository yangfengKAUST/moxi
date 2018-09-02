package com.moxi.pojo;

/**
 *
 * Created by yangfeng on 20/08/2018.
 */
public class ScoreUpload {


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


    /**
     * 成绩字段（科目，分数，比重）
     */
    private String scores;

    /**
     * 总成绩
     */
    private double total_score;

    /**
     * 成绩排名
     */
    private int rank_condition;

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

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    public double getTotal_score() {
        return total_score;
    }

    public void setTotal_score(double total_score) {
        this.total_score = total_score;
    }

    public int getRank_condition() {
        return rank_condition;
    }

    public void setRank_condition(int rank_condition) {
        this.rank_condition = rank_condition;
    }
}
