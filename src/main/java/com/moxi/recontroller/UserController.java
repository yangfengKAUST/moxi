package com.moxi.recontroller;

import com.fasterxml.jackson.annotation.JsonRawValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yangfeng on 19/08/2018.
 */
@Controller
public class UserController {

    @RequestMapping(value = "/applyTest/register.do", method = RequestMethod.GET)
    @ResponseBody
    @JsonRawValue
    public String testTheInterface() {
        return "success";
    }
}
