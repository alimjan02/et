package com.et.pro.course.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.et.pro.course.entity.Course;
import com.et.pro.course.mapper.CourseMapper;
import com.et.pro.util.PageUtil;
import com.et.pro.util.PublicResult;
import com.et.pro.util.PublicUtils;
import com.et.pro.util.Result;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author
 * @since 2019-03-21
 */
@Controller
public class CourseController {

	@Autowired
	private CourseMapper cm;

	@PostMapping("/addCourse")
	public RedirectView addCourse(Course c, MultipartFile file) throws IllegalStateException, IOException {
		String fileName = file.getOriginalFilename();
		String filePath = "E:/picture/cpic/";
		File pack = new File(filePath);
		if (!pack.exists()) {
			pack.mkdirs();
		}
		File dest = new File(filePath + fileName);
		file.transferTo(dest);
		String pic = "picture/cpic/" + fileName;
		Course course = new Course(c.getTitle(), c.getDes(), c.getTeacherName(), pic, LocalDateTime.now());
		cm.insert(course);
		RedirectView rv = null;
		if (PublicUtils.getCurrentUserInfo().getUserType()==1) {
			rv = new RedirectView("t_course.html");
		} else {
			rv = new RedirectView("course.html");
		}
		return rv;
	}

	@ResponseBody
	@PostMapping("/course/getCourseById")
	public Result getCourseById(@RequestBody Course c) {
		QueryWrapper<Course> qw = new QueryWrapper<>();
		qw.eq("id", c.getId());
		return PublicResult.SUCCESS(cm.selectOne(qw));
	}

	@ResponseBody
	@PostMapping("/course/updateCourseById")
	public Result updateCourseById(@RequestBody Course c) {
		return cm.updateById(c) > 0 ? PublicResult.SUCCESS("修改成功") : PublicResult.ERROR("修改失败");
	}

	@ResponseBody
	@PostMapping("/course/getCourses")
	public Result getCourses(@RequestBody Map<String, Object> param) {
		QueryWrapper<Course> qw = new QueryWrapper<>();
		Page<Course> page = PageUtil.getPage(param);
		if (param != null && param.get("titile") != null) {
			qw.like("titile", param.get("titile"));
		}
		//qw.orderByDesc("id");
		IPage<Course> result = cm.selectPage(page, qw);
		return PublicResult.SUCCESS(result.getRecords(), result.getTotal());
	}
	
	@ResponseBody
	@PostMapping("/course/delCourseById")
	public Result delCourseById(@RequestBody Course u) {
		return cm.deleteById(u.getId()) > 0 ? PublicResult.SUCCESS("删除成功") : PublicResult.ERROR("删除失败");
	}
	
	@ResponseBody
	@PostMapping("/getTables")
	public Result getTables(@RequestBody Map<String, Object> param) {
		//qw.orderByDesc("id");
		return PublicResult.SUCCESS(cm.getTables());
	}

}
