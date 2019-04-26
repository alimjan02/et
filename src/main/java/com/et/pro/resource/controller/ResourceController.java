package com.et.pro.resource.controller;


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
import com.et.pro.resource.entity.Resource;
import com.et.pro.resource.mapper.ResourceMapper;
import com.et.pro.util.PageUtil;
import com.et.pro.util.PublicResult;
import com.et.pro.util.PublicUtils;
import com.et.pro.util.Result;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2019-03-21
 */
@Controller
public class ResourceController {

	@Autowired
	private ResourceMapper cm;

	@PostMapping("/addResource")
	public RedirectView addResource(Resource c, MultipartFile file) throws IllegalStateException, IOException {
		String fileName = file.getOriginalFilename();
		String filePath = "E:/picture/rpic/";
		File pack = new File(filePath);
		if (!pack.exists()) {
			pack.mkdirs();
		}
		File dest = new File(filePath + fileName);
		file.transferTo(dest);
		String pic = "picture/rpic/" + fileName;
		Resource Resource = new Resource(c.getDes(), pic, LocalDateTime.now(), PublicUtils.getCurrentUserId(), fileName);
		cm.insert(Resource);
		RedirectView rv = null;
		if (PublicUtils.getCurrentUserInfo().getUserType()==1) {
			rv = new RedirectView("t_resource.html");
		} else {
			rv = new RedirectView("resource.html");
		}
		return rv;
	}

	@ResponseBody
	@PostMapping("/resource/getResourceById")
	public Result getResourceById(@RequestBody Resource c) {
		QueryWrapper<Resource> qw = new QueryWrapper<>();
		qw.eq("id", c.getId());
		return PublicResult.SUCCESS(cm.selectOne(qw));
	}
	
	@ResponseBody
	@PostMapping("/resource/updateResourceById")
	public Result updateResourceById(@RequestBody Resource c) {
		return cm.updateById(c) > 0 ? PublicResult.SUCCESS("修改成功") : PublicResult.ERROR("修改失败");
	}

	@ResponseBody
	@PostMapping("/resource/getResources")
	public Result getResources(@RequestBody Map<String, Object> param) {
		QueryWrapper<Resource> qw = new QueryWrapper<>();
		Page<Resource> page = PageUtil.getPage(param);
		if (param != null && param.get("describe") != null) {
			qw.like("describe", param.get("describe"));
		}
		IPage<Resource> result = cm.selectPage(page, qw);
		return PublicResult.SUCCESS(result.getRecords(), result.getTotal());
	}

	@ResponseBody
	@PostMapping("/resource/delResourceById")
	public Result delResourceById(@RequestBody Resource u) {
		return cm.deleteById(u.getId()) > 0 ? PublicResult.SUCCESS("删除成功") : PublicResult.ERROR("删除失败");
	}


}

