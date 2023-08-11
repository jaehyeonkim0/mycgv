package com.springboot.mycgv.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BoardDto {

    private String page;
    private int rno;
    private int bhits;
    private String bid;
    private String btitle;
    private String bcontent;
    private String id;
    private String bdate;
    private String bfile;
    private String bsfile;

    private MultipartFile file1;

}
