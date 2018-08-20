package com.moxi.pojo;

/**
 *
 * Created by yangfeng on 20/08/2018.
 */
public class ScoreUpload {


    /**
     * 招考序号
     */
    private String seriesnumber;

    /**
     * 准考证号
     */
    private String testNumber;

    /**
     * 岗位代码
     */
    private String positionCode;


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

    public String getTestNumber() {
        return testNumber;
    }

    public void setTestNumber(String testNumber) {
        this.testNumber = testNumber;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
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
