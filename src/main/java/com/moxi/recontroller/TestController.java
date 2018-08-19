package com.moxi.recontroller;

import com.fasterxml.jackson.annotation.JsonRawValue;
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

    @RequestMapping(value = "/test.do", method = RequestMethod.GET)
    @ResponseBody
    @JsonRawValue
    public String testTheInterface() {
        return "success";
    }


}
