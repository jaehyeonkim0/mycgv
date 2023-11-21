package com.springboot.mycgv.service;

import com.springboot.mycgv.dto.PageDto;
import com.springboot.mycgv.mapper.PageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageService {

    @Autowired
    private PageMapper pageMapper;

    public PageDto getPageResult(PageDto pageDto) {
        //페이징 처리 - startCount, endCount 구하기
        int startCount = 0;
        int endCount = 0;
        int pageSize = 0;	//한페이지당 게시물 수
        int reqPage = 1;	//요청페이지; default page
        int pageCount = 1;	//전체 페이지 수
        int dbCount = 0;	//DB에서 가져온 전체 행수

        dbCount = pageMapper.totalRowCount(pageDto);

        if(pageDto.getServiceName().equals("notice")) {
            //매개변수 serviceType을 noticeService 변환
            //들어올 땐 Object 타입이라 형변환 해야됨
            //noticeService = (NoticeService)serviceType;
            pageSize = 5;
        }else if(pageDto.getServiceName().equals("member")) {
            pageSize = 5;
        }else if(pageDto.getServiceName().equals("board")) {
            pageSize = 5;
        }

        //총 페이지 수 계산
        if(dbCount % pageSize == 0){ //DB에서 가져온 전체 행 수 % 페이지당 게시물 수 == 0
            pageCount = dbCount/pageSize;
        }else{
            pageCount = dbCount/pageSize+1;
        }

        //요청 페이지 계산
        if(pageDto.getPage() != null){ //처음 로딩이 아니고 페이지 정보가 있을 때, reqPage : 요청 페이지.
            reqPage = Integer.parseInt(pageDto.getPage());
            startCount = (reqPage-1) * pageSize+1; //페이지당 게시물 수(pageSize)가 5, 요청 페이지(reqPage)가 2일때, 게시물 시작 번호는 6
            endCount = reqPage * pageSize;
        }else{ //처음 로딩할때(page=null일 때)는 요청 페이지 없음 무조건 1페이지여서 startCount(데이터 1번부터) endCount(5번까지 보여줘라)
            startCount = 1;
            endCount = pageSize;
        }

        pageDto.setStartCount(startCount);
        pageDto.setEndCount(endCount);
        pageDto.setDbCount(dbCount);
        pageDto.setPageSize(pageSize);
        pageDto.setPageCount(pageCount);
        pageDto.setReqPage(reqPage);

        return pageDto;
    }

}
