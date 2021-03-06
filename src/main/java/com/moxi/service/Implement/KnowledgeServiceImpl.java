package com.moxi.service.Implement;

import com.moxi.dao.ScoreMapper;
import com.moxi.pojo.ScoreParam;
import com.moxi.pojo.ScoreUpload;
import com.moxi.service.IKnowledgeService;
import com.moxi.util.ExcelImportUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 上传岗位
 * Created by yangfeng on 20/08/2018.
 */
@Service
public class KnowledgeServiceImpl implements IKnowledgeService{

    Logger logger = LoggerFactory.getLogger(KnowledgeServiceImpl.class);

    @Autowired
    ScoreMapper scoreMapper;

    /**
     * 上传excel文件到临时目录后并开始解析
     * @param fileName
     * @return
     */
    @Override
    public String batchImport(String fileName, MultipartFile mfile, HttpServletRequest request){


        String path = request.getSession().getServletContext().getRealPath("/upload/");

        String name = mfile.getOriginalFilename();
        File uploadDir =  new File(path + "/" + name);


//        File uploadDir = new  File("/Users/yangfeng/Documents/moxi-master/files");
        //创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
        if (!uploadDir.exists()) uploadDir.mkdirs();
        //新建一个文件
//        File tempFile = new File("/Users/yangfeng/Documents/moxi-master/files/" + new Date().getTime() + ".xlsx");
        File tempFile = new File(path + "/" + name + "/" + new Date().getTime() + ".xlsx");
        //初始化输入流
        InputStream is = null;
        try{
            //将上传的文件写入新建的文件中
            mfile.transferTo(tempFile);

            //根据新建的文件实例化输入流
            is = new FileInputStream(tempFile);

            //根据版本选择创建Workbook的方式
            Workbook wb = null;
            //根据文件名判断文件是2003版本还是2007版本
            if(ExcelImportUtils.isExcel2007(fileName)){
                wb = new XSSFWorkbook(is);
            }else{
                wb = new HSSFWorkbook(is);
            }
            //根据excel里面的内容读取知识库信息
            List<ScoreUpload> dataInfo =  readExcelValue(wb,tempFile);

            // 把数据保存到数据库
            for (ScoreUpload scoreUpload : dataInfo) {
                ScoreParam scoreParam = new ScoreParam();
                scoreParam.setSeries_number(scoreUpload.getSeries_number());
                scoreParam.setApply_number(scoreUpload.getApply_number());
                scoreParam.setPosition_code(scoreUpload.getPosition_code());
                if (scoreMapper.ifExist(scoreParam) == 0) {
                    scoreMapper.insertScoreInfo(scoreUpload);
                }else{
                    // todo add log here
                    System.out.println("this score is already exist");
                }
            }
            return "导入成功";
        }catch(Exception e){
            // todo deal with except conditions
            e.printStackTrace();
        } finally{
            if(is !=null)
            {
                try{
                    is.close();
                }catch(IOException e){
                    is = null;
                    logger.error(e.getMessage(), e);
//                    e.printStackTrace();
                }
            }
        }
        logger.error("导入出错！请检查数据格式！");
        return "导入出错！请检查数据格式！";
    }


    @Override
    public ScoreUpload getScoreInfo(String apply_number) {
        ScoreUpload scoreUpload = scoreMapper.getScoreInformation(apply_number);
        if (scoreUpload == null) {
            System.out.println("score upload ");
            return scoreUpload;
        }else {
            return scoreUpload;
        }
    }


    /**
     * 解析Excel里面的数据
     * @param wb
     * @return
     */
    private List<ScoreUpload> readExcelValue(Workbook wb,File tempFile){

        // 错误信息接收器
        String errorMsg = "";
        // 得到第一个shell
        Sheet sheet=wb.getSheetAt(0);
        // 得到Excel的行数
        int totalRows=sheet.getPhysicalNumberOfRows();
        // 总列数
        int totalCells = 0;
        // 得到Excel的列数(前提是有行数)，从第二行算起
        if(totalRows>=2 && sheet.getRow(1) != null){
            totalCells=sheet.getRow(1).getPhysicalNumberOfCells();
        }
        List<ScoreUpload> userKnowledgeBaseList=new ArrayList<>();
        ScoreUpload scoreUpload;

        String br = "<br/>";

        //循环Excel行数,从第二行开始。标题不入库
        for(int r=1 ; r < totalRows ; r++){
            String rowMessage = "";
            Row row = sheet.getRow(r);
            if (row == null){
                errorMsg += br+"第"+(r+1)+"行数据有问题，请仔细检查！";
                continue;
            }
            scoreUpload = new ScoreUpload();
            String seriesNumber = "";
            String testNumber = "";
            String positionCode = "";
            String scoreJson = "";
            Double finalScore = 0D;
            Integer rank;

            //循环Excel的列
            for (int i = 0; i < totalCells; i++) {
                Cell cell = row.getCell(i);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                if (null != cell) {
                    if (i == 0) {
                        seriesNumber = (cell.getStringCellValue());
                        scoreUpload.setSeries_number(seriesNumber);
                    }else if (i == 1) {
                        testNumber = cell.getStringCellValue();
                        scoreUpload.setApply_number(testNumber);
                    }else if (i == 2) {
                        positionCode = cell.getStringCellValue();
                        scoreUpload.setPosition_code(positionCode);
                    }else if (i == 3) {
                        scoreJson = cell.getStringCellValue();
                        scoreUpload.setScores(scoreJson);
                    }else if (i == 4) {
                        finalScore = Double.valueOf(cell.getStringCellValue());
                        scoreUpload.setTotal_score(finalScore);
                    }else if (i == 5) {
                        rank = Integer.valueOf(cell.getStringCellValue());
                        scoreUpload.setRank_condition(rank);
                    }
                }else {
                    logger.error("传入的列为空 " + tempFile.getName());
                    System.out.println("传入的列为空");
                }
            }

            //拼接每行的错误提示
            if(!StringUtils.isEmpty(rowMessage)){
                errorMsg += br+"第"+(r+1)+"行，"+rowMessage;
            }else{
                userKnowledgeBaseList.add(scoreUpload);
            }
        }

        //删除上传的临时文件
        if(tempFile.exists()){
            tempFile.delete();
        }

        return userKnowledgeBaseList;
    }

}
