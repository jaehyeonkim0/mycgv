package com.springboot.mycgv.dto;


import lombok.*;
import org.springframework.data.domain.Page;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PageDto2 {

    private int page;
    private Long dbCount;
    private String serviceName;
    private int startPage;
    private int endPage;
    private Long pageSize;
    private int totalPage;
    private int reqPage;

    public static PageDto2 toPageDto(Page list) {
        PageDto2 pageDto2 = new PageDto2();
        pageDto2.setReqPage(list.getPageable().getPageNumber());
        pageDto2.setDbCount(list.getTotalElements());
        pageDto2.setStartPage(Math.max(1, list.getPageable().getPageNumber() - 5));
        pageDto2.setEndPage(Math.min(list.getTotalPages(), list.getPageable().getPageNumber() + 5));
        pageDto2.setTotalPage(list.getTotalPages());

        return pageDto2;
    }


    //serviceName : member ,notice, boardController에서 호출


}
