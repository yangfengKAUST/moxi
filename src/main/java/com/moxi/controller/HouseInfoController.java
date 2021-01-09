package com.moxi.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moxi.pojo.house.HouseInfo;
import com.moxi.pojo.house.HouseReturnFormat;
import com.moxi.service.IGetHouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yangfeng on 09/01/2021.
 */
@Controller
@RestController
public class HouseInfoController {

    Logger logger = LoggerFactory.getLogger(HouseInfoController.class);


    @Autowired
    IGetHouseService getHouseService;


    @PostMapping("consult/area")
    @ResponseBody
    public String findByArea(@RequestParam String area,
                   @RequestParam String city) {
        HouseReturnFormat houseReturnFormat = new HouseReturnFormat();
        if (area == null || city == null) {
            houseReturnFormat.setStatus(false);
            return houseReturnFormat.toString();
        }
        JSONObject jsonObjectResult = new JSONObject();
        try {
            List<HouseInfo> houseInfoList = getHouseService.getHouseByArea(area, city);
            if (houseInfoList == null || houseInfoList.size() == 0) {
                houseReturnFormat.setStatus(false);
                houseReturnFormat.setInfo("given parameter cannot find house source in db");
                return houseReturnFormat.toString();
            } else {
//                houseReturnFormat.setStatus(true);
//                houseReturnFormat.setHouseInfo(houseInfo.toString());
//                houseReturnFormat.setInfo("success");
                JSONArray jsonArray=new JSONArray();
                for (HouseInfo houseInfo : houseInfoList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", houseInfo.getId());
                    jsonObject.put("city", houseInfo.getCity());
                    jsonObject.put("date", houseInfo.getDate());
                    jsonObject.put("district", houseInfo.getArea());
                    jsonObject.put("area", houseInfo.getArea());
                    jsonObject.put("xiaoqu", houseInfo.getXiaoqu());
                    jsonObject.put("price", houseInfo.getPrice());
                    jsonObject.put("sale", houseInfo.getSale());
                    jsonArray.add(jsonObject);
                }
                jsonObjectResult.put("status", true);
                jsonObjectResult.put("info","success");
                jsonObjectResult.put("houseInfo", jsonArray);
//                houseReturnFormat.setStatus(true);
//                houseReturnFormat.setHouseInfo(jsonArray.toString());
//                houseReturnFormat.setInfo("success");
            }
        }catch (Exception e) {
//            houseReturnFormat.setStatus(false);
//            houseReturnFormat.setInfo("exception " + e.getMessage());
            jsonObjectResult.put("status", false);
            jsonObjectResult.put("info",  e.getMessage());
        }
        return jsonObjectResult.toJSONString();
    }

    @PostMapping("consult/xiaoqu")
    @ResponseBody
    public String findByXiaoqu(@RequestParam String xiaoqu,
                             @RequestParam String city) {
        HouseReturnFormat houseReturnFormat = new HouseReturnFormat();
        if (xiaoqu == null || city == null) {
            houseReturnFormat.setStatus(false);
            houseReturnFormat.setInfo("xiaoqu or house is empty");
            return houseReturnFormat.toString();
        }

        try {
            HouseInfo houseInfo = getHouseService.getHouseByXiaoqu(xiaoqu, city);
            if (houseInfo == null) {
                houseReturnFormat.setStatus(false);
                houseReturnFormat.setInfo("given parameter cannot find house source in db");
                return houseReturnFormat.toString();
            } else {
                houseReturnFormat.setStatus(true);
//                String tmp = JSONArray.toJSONString(houseInfo);
                houseReturnFormat.setHouseInfo(houseInfo);
                houseReturnFormat.setInfo("success");
            }
        }catch (Exception e) {
            houseReturnFormat.setStatus(false);
            houseReturnFormat.setInfo("exception " + e.getMessage());
        }
        return JSONArray.toJSONString(houseReturnFormat);
    }

    @GetMapping("tmp/picupload")
    public String uploadPicture(Model model) {
        return "files/uploadpic";
    }

}
