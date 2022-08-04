package com.gongdaeoppa.demo3.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.gongdaeoppa.demo3.vo.Board;

@Mapper
public interface BoardRepository {
	/*@Select(" SELECT * " +
			" FROM board AS B " +
			" WHERE B.id = #{id} " + 
			" AND B.delStatus = 0 ")*/
	Board getBoardById(@Param("id") int id);
}