package com.springboot.mycgv.dto;


import lombok.*;
import org.springframework.data.domain.Page;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PageDto2 {

    private Long dbCount;

    private int totalPage;
    private int reqPage;
    private int size;
    private int pageSize;

    private int startBlockPage;
    private int endBlockPage;


    public static PageDto2 toPageDto(Page list) {
        PageDto2 pageDto2 = new PageDto2();
        pageDto2.setReqPage(list.getPageable().getPageNumber()); //요청 페이지
        pageDto2.setDbCount(list.getTotalElements()); //전체 개수
        pageDto2.setTotalPage(list.getTotalPages()); //전체 페이지
        pageDto2.setSize(list.getSize()); //한 페이지 표시할 갯수

        return pageDto2;
    }

}
