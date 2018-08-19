package com.moxi.pojo;

/**
 * Created by yangfeng on 14/08/2018.
 * 成绩信息
 */
public class ScoreInformation {

    /**
     * 招考序号
     */
    private String seriesnumber;

    /**
     * 报名序号
     */
    private String applyNumber;

    /**
     * 岗位代码
     */
    private String positionCode;

    /**
     * 招聘人数
     */
    private int needNumber;

    /**
     * 成绩字段（科目，分数，比重）
     */
    private String scoreJson;

    /**
     * 总成绩
     */
    private double finalScore;

    /**
     * 成绩排名
     */
    private int rank;

    public String getSeriesnumber() {
        return seriesnumber;
    }

    public void setSeriesnumber(String seriesnumber) {
        this.seriesnumber = seriesnumber;
    }

    public String getApplyNumber() {
        return applyNumber;
    }

    public void setApplyNumber(String applyNumber) {
        this.applyNumber = applyNumber;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public int getNeedNumber() {
        return needNumber;
    }

    public void setNeedNumber(int needNumber) {
        this.needNumber = needNumber;
    }

    public String getScoreJson() {
        return scoreJson;
    }

    public void setScoreJson(String scoreJson) {
        this.scoreJson = scoreJson;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
