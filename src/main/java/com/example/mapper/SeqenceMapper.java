package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author hzq
 * @date 2019/7/28 0028 下午 5:37
 */
@Mapper
@Component
public interface SeqenceMapper {

    @Select("select ${seqName}.nextval from dual")
    Long getSequence(@Param("seqName") String seqName);
}
