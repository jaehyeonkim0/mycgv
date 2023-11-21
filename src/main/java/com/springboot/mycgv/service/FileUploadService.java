package com.springboot.mycgv.service;

import com.springboot.mycgv.dto.BoardDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;


@Service
@Slf4j
public class FileUploadService {

    @Value("${upload.directory}")
    private String uploadPath;


    /**
     * 업로드 파일 디렉토리 저장
     * @param boardDto
     * @throws IOException
     */
    public void imageSave(BoardDto boardDto) throws IOException {
        if(boardDto.getBoardImages() != null &&
                !boardDto.getBoardImages().get(0).getOriginalFilename().equals("")) {

            for(int i=0; i<boardDto.getBoardImages().size(); i++) {
                File saveFile = new File(uploadPath+boardDto.getStoredImageNameList().get(i));
                boardDto.getBoardImages().get(i).transferTo(saveFile);
            }
        }
    }

    /**
     * 다중 파일 삭제 (삭제)
     * @param boardDto
     * @throws IOException
     */
    public void imageDelete(BoardDto boardDto) throws IOException {

        for(int i=0; i< boardDto.getStoredImageNameList().size(); i++) {
            File deleteFile = new File(uploadPath+boardDto.getStoredImageNameList().get(i));
            if(deleteFile.exists()) {
                deleteFile.delete();
            }
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
}
