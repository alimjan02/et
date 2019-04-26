package com.et.pro.course.mapper;

import com.et.pro.course.entity.Course;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2019-03-21
 */
public interface CourseMapper extends BaseMapper<Course> {

	@Select("select table_name from information_schema.tables where table_schema='et'")
	List<Map<String,Object>> getTables();
}
