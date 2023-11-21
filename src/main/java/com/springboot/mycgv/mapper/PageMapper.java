package com.springboot.mycgv.mapper;

import com.springboot.mycgv.dto.PageDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PageMapper {

    int totalRowCount(PageDto pageDto);
}
