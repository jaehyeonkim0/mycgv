package com.springboot.mycgv.service;

import com.springboot.mycgv.dto.BoardDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@Slf4j
public class FileUploadService {

    @Value("${upload.directory}")
    private String uploadPath;

    /**file delete*/
    public void fileDelete(String fileNameBeforeUpdate) throws IOException {
        File deleteFile = new File(uploadPath+fileNameBeforeUpdate);
        if(deleteFile.exists()) {
            deleteFile.delete();
        }
    }


    /**file save*/
    public void fileSave(BoardDto boardDto) throws IOException {
        if(boardDto.getBoardDtoFile() != null) {
            File saveFile = new File(uploadPath+boardDto.getSaveFileName());
            boardDto.getBoardDtoFile().transferTo(saveFile);
        }

    }


//    /**checking whether there's file or not*/
//
//    public Object fileCheck(BoardDto boardDto) {
//        if(boardDto.getBoardDtoFile().getOriginalFilename() != null
//                && !boardDto.getBoardDtoFile().getOriginalFilename().equals("")) { //if there is file
//
//            //BSFILE 파일 중복 처리
//            UUID uuid = UUID.randomUUID();
//            String originalFilename = boardDto.getBoardDtoFile().getOriginalFilename();
//            String saveFileName = uuid + "_" + originalFilename;
//
//            boardDto.setOriginalFileName(originalFilename);
//            boardDto.setSaveFileName(saveFileName);
//        }else {
//            System.out.println("there's no file");
//        }
//        return boardDto;
//    }

    /**checking whether there's file or not*/

    public Object fileCheck(BoardDto boardDto) {
        if(boardDto.getBoardDtoFile() != null) { //if there is file

            //BSFILE 파일 중복 처리
            UUID uuid = UUID.randomUUID();
            String originalFilename = boardDto.getBoardDtoFile().getOriginalFilename();
            String saveFileName = uuid + "_" + originalFilename;

            boardDto.setOriginalFileName(originalFilename);
            boardDto.setSaveFileName(saveFileName);
        }else {
            System.out.println("there's no file");
        }
        return boardDto;
    }

//    public Object multipleFileCheck(BoardDto boardDto) {
//        if(boardDto.getBoardDtoFile1() != null) { //파일이 있으면
//
//            //BSFILE 파일 중복 처리
//            List<String> originalFileNameList = new ArrayList<>();
//            List<String> saveFileNameList = new ArrayList<>();
//
//            for(MultipartFile file : boardDto.getBoardDtoFile1()) {
//                UUID uuid = UUID.randomUUID();
//                originalFileNameList.add(file.getOriginalFilename());
//                saveFileNameList.add(uuid + "_" + file.getOriginalFilename());
//            }
//            boardDto.setOriginalFileName1(originalFileNameList);
//            boardDto.setSaveFileName1(saveFileNameList);
//
//        }else {
//            System.out.println("there's no file");
//        }
//        return boardDto;
//    }

    /**
     * 게시판 파일 체크
     * @param boardDto (List<MultipartFile> 포함 폼 데이터)
     * @return boardDto (UUID + 파일명으로 변환한 폼 데이터)
     */
    public Object multipleFileCheck(BoardDto boardDto) {

        if(boardDto.getBoardDtoFile1().get(0).getOriginalFilename() != null
            && !boardDto.getBoardDtoFile1().get(0).getOriginalFilename().equals("")) { //파일이 있으면

            //BSFILE 파일 중복 처리
            List<String> originalFileNameList = new ArrayList<>();
            List<String> saveFileNameList = new ArrayList<>();

            for(MultipartFile file : boardDto.getBoardDtoFile1()) {
                UUID uuid = UUID.randomUUID();
                originalFileNameList.add(file.getOriginalFilename());
                saveFileNameList.add(uuid + "_" + file.getOriginalFilename());
            }
            boardDto.setOriginalFileName1(originalFileNameList);
            boardDto.setSaveFileName1(saveFileNameList);

        }else {
            System.out.println("there's no file");
        }
        return boardDto;
    }

    /**
     * 게시판 다중 파일 저장
     * @param boardDto 폼 데이터
     * @throws IOException
     */
    public void mutipleFileSave(BoardDto boardDto) throws IOException {
        if(boardDto.getBoardDtoFile1() != null) {

            for(int i=0; i<boardDto.getBoardDtoFile1().size(); i++) {
                File saveFile = new File(uploadPath+boardDto.getSaveFileName1().get(i));
                boardDto.getBoardDtoFile1().get(i).transferTo(saveFile);
            }
        }
    }

    /**
     * 다중 파일 삭제
     * @param boardDto
     * @throws IOException
     */
    public void multipleFileDelete(BoardDto boardDto) throws IOException {
//        List<File> deleteFiles = new ArrayList<>();
//
//        for (String fileName : boardDto.getFileNameBeforeUpdate1()) {
//            File deleteFile = new File(uploadPath + fileName);
//            deleteFiles.add(deleteFile);
//        }
//
//        for (File file : deleteFiles) {
//            if (file.exists()) {
//                file.delete();
//                System.out.println("파일 삭제 성공: " + file.getAbsolutePath());
//            } else {
//                System.out.println("파일이 존재하지 않습니다: " + file.getAbsolutePath());
//            }
//        }

        for(int i=0; i< boardDto.getFileNameBeforeUpdate1().size(); i++) {
            File deleteFile = new File(uploadPath+boardDto.getFileNameBeforeUpdate1().get(i));
            if(deleteFile.exists()) {
                deleteFile.delete();
            }
        }
    }
}
