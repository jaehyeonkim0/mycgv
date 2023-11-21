package com.springboot.mycgv.mapper;

import com.springboot.mycgv.dto.NoticeDto;
import com.springboot.mycgv.dto.PageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    NoticeDto content(String nid);
    List<NoticeDto> list(PageDto pageDto);

    int save(NoticeDto noticeDto);

}
