package com.et.pro.user.controller;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.et.pro.user.entity.User;
import com.et.pro.user.mapper.UserMapper;
import com.et.pro.util.MD5Utils;
import com.et.pro.util.PageUtil;
import com.et.pro.util.PublicResult;
import com.et.pro.util.Result;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2019-03-21
 */
@RestController
@RequestMapping("/user")
public class UserController {


	@Autowired
	private UserMapper um;
	
	@PostMapping("/login")
	public Result login(@RequestBody User u, HttpServletRequest request) {
		QueryWrapper<User> qw = new QueryWrapper<>();
		qw.eq("username", u.getUserName()).eq("password", MD5Utils.md5(u.getPassword()));
		User user = um.selectOne(qw);
		if (user == null) {
			return PublicResult.ERROR("用户名或者密码不正确");
		}
		request.getSession().setAttribute("USER", user);
		return PublicResult.SUCCESS(user);
	}

	@PostMapping("/addUser")
	public Result addUser(@RequestBody User u) throws IllegalStateException, IOException {
		if (!StringUtils.isEmpty(u.getUserName()) && !StringUtils.isEmpty(u.getPassword())) {
			QueryWrapper<User> qw = new QueryWrapper<>();
			qw.eq("username", u.getUserName());
			if (um.selectOne(qw) == null) {
				/*String fileName = file.getOriginalFilename();
				String password = null;
				String pic = null;
				if (fileName==null) {
					String filePath = "E:/picture/upic/";
					File pack = new File(filePath);
					if (!pack.exists()) {
						pack.mkdirs();
					}
					File dest = new File(filePath + fileName);
					file.transferTo(dest);
					password = MD5Utils.md5(u.getPassword());
					pic = "picture/upic/" + fileName;
					
				}*/
				User user = new User(u.getUserName(), MD5Utils.md5(u.getPassword()), u.getNickname(), u.getUserType(), null,
						LocalDateTime.now());
				um.insert(user);
				return PublicResult.SUCCESS("注册成功");
			}
			return PublicResult.ERROR("用户名已经注册");
		}
		return PublicResult.ERROR("参数不正确");
	}

	@PostMapping("/getUserById")
	public Result getUserById(@RequestBody User u) {
		QueryWrapper<User> qw = new QueryWrapper<>();
		qw.eq("id", u.getId());
		return PublicResult.SUCCESS(um.selectOne(qw));
	}

	@PostMapping("/updateUserById")
	public Result updateUserById(@RequestBody User u) {
		if (u.getPassword() != null) {
			String password = MD5Utils.md5(u.getPassword());
			u.setPassword(password);
		}
		return um.updateById(u) > 0 ? PublicResult.SUCCESS("修改成功") : PublicResult.ERROR("修改失败");
	}

	@PostMapping("/getUsers")
	public Result addUser(@RequestBody Map<String, Object> param) {
		QueryWrapper<User> qw = new QueryWrapper<>();
		Page<User> page = PageUtil.getPage(param);
		if (param != null && param.get("username") != null) {
			qw.like("username", param.get("username"));
		}
		IPage<User> result = um.selectPage(page, qw);
		return PublicResult.SUCCESS(result.getRecords(), result.getTotal());
	}

	@PostMapping("/delUserById")
	public Result delUserById(@RequestBody User u) {
		return um.deleteById(u.getId()) > 0 ? PublicResult.SUCCESS("删除成功") : PublicResult.ERROR("删除失败");
	}

}

