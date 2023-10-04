package com.springboot.mycgv.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class NoticeDto {

    @NotBlank(message = "제목은 필수 입력 항목입니다")
    private String ntitle;

    @NotBlank(message = "내용은 필수 입력 항목입니다")
    @Pattern(regexp = "^.{1,}$", message = "최소 1자 이상 입력하세요")
    private String ncontent;

    int rno, nhits;
    String nid, ndate;

//    private CommonsMultipartFile[] files;
//
//
//    String nfile1, nsfile1, nfile2, nsfile2;
//
//    ArrayList<String> nfiles = new ArrayList<String>();
//    ArrayList<String> nsfiles = new ArrayList<String>();

}
