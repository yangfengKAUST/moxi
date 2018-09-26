package com.moxi.controller;

import com.alibaba.fastjson.JSONObject;
import com.moxi.dao.TesterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by yangfeng on 24/09/2018.
 */
@Controller
@RequestMapping("/pic")
public class PictureCheckController {
    private Logger logger = LoggerFactory.getLogger(PictureCheckController.class);

    @Autowired
    TesterMapper testerMapper;

    @RequestMapping(value = "/pictureCheck", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<String> pictureCheck(HttpServletResponse response, HttpServletRequest request, String param) {

        //todo
        String path = request.getSession().getServletContext().getRealPath("/upload/");
        String pathTemp = path.replace("webapp", "resources/static");

        ArrayList<String> fileList = new ArrayList<>();
        ArrayList<String> returnList = new ArrayList<>();

        fileList = getFiles(pathTemp);

        return fileList;

    }

    public static ArrayList<String> getFiles(String filePath) {
        ArrayList<String> fileList = new ArrayList<String>();
        File root = new File(filePath);
        File[] files = root.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                /*
                 * 递归调用
                 */
                getFiles(file.getAbsolutePath());
                fileList.add(file.getAbsolutePath());
            } else {
                String picPathStr = file.getAbsolutePath();
                String[] picFiles = picPathStr.split("/");
                fileList.add("../upload/" + "/" + picFiles[picFiles.length - 1]);
            }
        }

        return fileList;
    }

    @GetMapping(value = "/pictureCheck")
    public String pictureCheck() {
        return "checkPicinfo";
    }


    /**
     * 根据身份证号码确定本人的照片不合格
     * @param idNumber 身份证号码
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/pictureCheckProcess", method = RequestMethod.POST)
    @ResponseBody
    public void pictureCheckProcess(@RequestBody String idNumber, HttpSession httpSession) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(idNumber);
            String temp = jsonObject.getString("result");
            // 1 代表未审核通过
            testerMapper.updatePicFailure(temp, 1);
        }catch (Exception e) {
            logger.error("update picture unpass error " + e.getMessage() + " id is " +idNumber);
            logger.error(e.getMessage(),e);
        }
    }

}
