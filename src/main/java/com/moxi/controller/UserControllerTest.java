package com.moxi.controller;

import com.alibaba.fastjson.JSON;
import com.moxi.dao.TesterMapper;
import com.moxi.pojo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangfeng on 18/09/2018.
 */
@Controller
@RequestMapping("/user")
public class UserControllerTest {
    private Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    @Autowired
    TesterMapper testerMapper;

    @RequestMapping(value = "/queryPersonalInfo", method = RequestMethod.POST)
    @ResponseBody
    public APIResponse<List<PersonalInfomation>> queryPersonalInfo(
            @RequestBody QueryPersonalParam queryPersonalParam,
            HttpServletResponse response) {

        try {
            int count = testerMapper.getIfPersonalInfoExit(queryPersonalParam.getSeriesNumber(), queryPersonalParam.getIdNumber());
            if (count == 0) {
                logger.error("given param is error, 传入参数 = {} " + JSON.toJSONString(queryPersonalParam));
                return APIResponse.returnFail("given param is error");
            } else {
                PersonalInfomation personalInfomation = testerMapper.getPersonalInfo(queryPersonalParam.getSeriesNumber(), queryPersonalParam.getIdNumber());
                List<PersonalInfomation> list =  new ArrayList<>();
                list.add(personalInfomation);
                return APIResponse.returnSuccess(list);
            }
        }catch (Exception e) {
            logger.error("query personal info error " + e.getMessage());
            logger.error(e.getMessage(), e);
            return APIResponse.returnFail("given param is error " + e.getMessage());
        }
    }

    @GetMapping(value = "/queryPersonalInfo")
    public String queryPersonalInfo() {
        return "readPersonalInfo";
    }
}
