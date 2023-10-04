//package com.springboot.mycgv.service;
//
//import com.springboot.mycgv.dto.BoardDto;
//import org.springframework.stereotype.Service;
//
//import java.io.File;
//import java.util.UUID;
//
//
//@Service
//public class FileUploadService {
//
//    /**file delete*/
//    public void fileDelete(String oldBsfile) throws Exception {
//        String projectPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\upload\\";
//        File deleteFile = new File(projectPath+oldBsfile);
//        if(deleteFile.exists()) {
//            deleteFile.delete();
//        }
//    }
//
//
//    /**file save*/
//    public void fileSave(BoardDto boardDto) throws Exception {
//        //upload location
//        String projectPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\upload\\";
//        System.out.println("path="+ projectPath);
//
//
//        //save on server if there's file
//        if(boardDto.getBfile() != null && !boardDto.getBfile().equals("")) {
//            File saveFile = new File(projectPath+boardDto.getBsfile());
//            boardDto.getFile1().transferTo(saveFile); //saving file in this directory
//        }
//
//    }
//
//
//    /**checking whether there's file or not*/
//
//    public Object fileCheck(BoardDto boardDto) {
//        if(boardDto.getFile1().getOriginalFilename() != null
//                && !boardDto.getFile1().getOriginalFilename().equals("")) { //if there is file
//
//            //BSFILE 파일 중복 처리
//            UUID uuid = UUID.randomUUID();
//            String bfile = boardDto.getFile1().getOriginalFilename();
//            String bsfile = uuid + "_" + bfile;
//
//            System.out.println("bfile="+bfile);
//            System.out.println("bsfile="+bsfile);
//
//            boardDto.setBfile(bfile);
//            boardDto.setBsfile(bsfile);
//        }else {
//            System.out.println("there's no file");
//        }
//        return boardDto;
//    }
//}
