package com.moxi.pojo;

/**
 * Created by yangfeng on 14/08/2018.
 * 招考序号
 * 报名时可能未确定准考证制作时间与成绩查询时间
 */
public class TestSeriesNumber {

    /**
     * 招考序号
     */
    private String seriesNumber;

    /**
     * 考试名称
     */
    private String subjectName;

    /**
     * 报名时间
     */
    private String applyTime;

    /**
     * 审核时间
     */
    private String checkTime;

    /**
     * 缴费时间
     */
    private String payTime;

    /**
     * 准考证打印时间
     */
    private String printTestPassTime;

    /**
     * 成绩查询时间
     */
    private String scoreCheckTime;

    public String getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(String seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getScoreCheckTime() {
        return scoreCheckTime;
    }

    public void setScoreCheckTime(String scoreCheckTime) {
        this.scoreCheckTime = scoreCheckTime;
    }

    public String getPrintTestPassTime() {
        return printTestPassTime;
    }

    public void setPrintTestPassTime(String printTestPassTime) {
        this.printTestPassTime = printTestPassTime;
    }
}
