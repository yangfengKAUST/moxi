package com.moxi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by yangfeng on 24/09/2018.
 */
@Controller
@RequestMapping("/pic")
public class PictureCheckController {
    private Logger logger = LoggerFactory.getLogger(PictureCheckController.class);

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

}
