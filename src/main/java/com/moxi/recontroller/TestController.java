package com.moxi.recontroller;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moxi.pojo.ScoreUpload;
import com.moxi.service.IKnowledgeService;
import com.moxi.util.ExcelImportUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 可以改造
 * Created by yangfeng on 12/08/2018.
 */
@Controller
public class TestController {

    @Autowired
    IKnowledgeService iKnowledgeService;

    @RequestMapping(value = "check.do", method = RequestMethod.GET)
    @ResponseBody
    @JsonRawValue
    public String testTheInterface(String seriesNumber) {

        ObjectMapper objectMapper = new ObjectMapper();
        ScoreUpload scoreUpload = iKnowledgeService.getScoreInfo(seriesNumber);
        if(scoreUpload == null) {
            return "it is empty";
        }
        try {
            String scoreToJson = objectMapper.writeValueAsString(scoreUpload);
            return scoreToJson;
        }catch (Exception e) {
            System.out.println("error" + e.getMessage());
            return e.getMessage();
        }

    }




}
