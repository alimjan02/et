package com.et.pro.post.controller;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.et.pro.msg.entity.Msg;
import com.et.pro.msg.mapper.MsgMapper;
import com.et.pro.post.entity.Post;
import com.et.pro.post.mapper.PostMapper;
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
@RestController
@RequestMapping("/post")
public class PostController {
	@Autowired
	private PostMapper cm;
	@Autowired
	private MsgMapper mm;

	@PostMapping("/addPost")
	public Result addPost(@RequestBody Post c) throws IllegalStateException, IOException {
		c.setCreateDate(LocalDateTime.now());
		c.setCreateUser(PublicUtils.getCurrentUserId());
		return cm.insert(c) > 0 ? PublicResult.SUCCESS("发布成功") : PublicResult.ERROR("发布失败");
	}

	@PostMapping("/getPostById")
	public Result getPostById(@RequestBody Post c) {
		QueryWrapper<Post> qw = new QueryWrapper<>();
		qw.eq("id", c.getId());
		Post p =cm.selectOne(qw);
		p.setMsg(mm.selectMsgList(c.getId()));
		return PublicResult.SUCCESS(p);
	}

	@PostMapping("/updatePostById")
	public Result updatePostById(@RequestBody Post c) {
		return cm.updateById(c) > 0 ? PublicResult.SUCCESS("修改成功") : PublicResult.ERROR("修改失败");
	}

	@PostMapping("/getPosts")
	public Result getPosts(@RequestBody Map<String, Object> param) {
		QueryWrapper<Post> qw = new QueryWrapper<>();
		Page<Post> page = PageUtil.getPage(param);
		if (param != null && param.get("titile") != null) {
			qw.like("titile", param.get("titile"));
		}
		qw.orderByDesc("id");
		IPage<Post> result = cm.selectPage(page, qw);
		List<Post> list =result.getRecords();
		for (Post post : list) {
		    QueryWrapper<Msg> mqw = new QueryWrapper<>();
		    mqw.eq("postId", post.getId());
		    post.setNum(mm.selectCount(mqw));
        }
		return PublicResult.SUCCESS(result.getRecords(), result.getTotal());
	}

	@PostMapping("/delPostById")
	public Result delPostById(@RequestBody Post u) {
		return cm.deleteById(u.getId()) > 0 ? PublicResult.SUCCESS("删除成功") : PublicResult.ERROR("删除失败");
	}
}

