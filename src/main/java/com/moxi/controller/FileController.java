package com.moxi.controller;

import com.moxi.service.IKnowledgeService;
import com.moxi.util.ExcelImportUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * 关于文件上传和下载
 * Created by yangfeng on 20/08/2018.
 */
@Controller
public class FileController {

    Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    IKnowledgeService knowledgeService;

    @GetMapping("console/test")
    public String test(Model model) {
        return "/read/testread";
    }

    @PostMapping("console/batchImport")
    public String batchImportUserKnowledge (@RequestParam(value="filename") MultipartFile file,
                                            HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        if(file==null){
            session.setAttribute("msg","文件不能为空！");
            //todo redirect
            return "redirect:upload";
        }

        // get file name

        String fileName=file.getOriginalFilename();

        if(!ExcelImportUtils.validateExcel(fileName)){
            session.setAttribute("msg","文件必须是excel格式！");
            //todo redirect
            return "redirect:upload";
        }

        //进一步判断文件内容是否为空（即判断其大小是否为0或其名称是否为null）
        long size=file.getSize();
        if(StringUtils.isEmpty(fileName) || size==0){
            session.setAttribute("msg","文件不能为空！");
            return "redirect:upload";
        }
        String message = knowledgeService.batchImport(fileName,file, request);
        session.setAttribute("msg",message);
        // todo which html should go to
        return "login";
    }

    @PostMapping("console/picupload")
    public void uploadPicture(@RequestParam(value="pic") MultipartFile picture, HttpServletRequest request) {
        if (picture == null) {
            logger.info("given picture is null " + picture.getName());
            System.out.println("it is null");
        }else {
            System.out.println("it is not null");
        }
        String path = request.getSession().getServletContext().getRealPath("upload");
        String picName = picture.getOriginalFilename();
        File dir = new File(path);
        File newPicture =  new File(path + "/" + picName);
        System.out.println(picture.getSize());
        if(!dir.exists()){
            dir.mkdirs();
        }
        //MultipartFile自带的解析方法
        try {
            picture.transferTo(newPicture);
        }catch (Exception e) {
            System.out.println("error " + e.getMessage());
            logger.error("error " + e.getMessage() + " " + picture.getName());
            logger.error(e.getMessage(), e);
        }
        // todo need to rewrite
//        return "/upload"+"/"+picName;
    }

    @GetMapping("console/picupload")
    public String uploadPicture(Model model) {
        return "files/uploadpic";
    }

}
