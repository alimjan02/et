package com.et.pro.msg.controller;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.et.pro.msg.entity.Msg;
import com.et.pro.msg.mapper.MsgMapper;
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
@RequestMapping("/msg")
public class MsgController {

	@Autowired
	private MsgMapper mm;

	@PostMapping("/addMsg")
	public Result addMsg(@RequestBody Msg c) throws IllegalStateException, IOException {
		Msg Msg = new Msg(c.getMsg(), c.getMsgTo(), c.getPostId(), LocalDateTime.now(),
				PublicUtils.getCurrentUserId());
		return mm.insert(Msg) > 0 ? PublicResult.SUCCESS("注册成功") : PublicResult.ERROR("注册失败");
	}

	@PostMapping("/getMsgById")
	public Result getMsgById(@RequestBody Msg c) {
		QueryWrapper<Msg> qw = new QueryWrapper<>();
		qw.eq("id", c.getId());
		return PublicResult.SUCCESS(mm.selectOne(qw));
	}

	@PostMapping("/updateMsgById")
	public Result updateMsgById(@RequestBody Msg c) {
		return mm.updateById(c) > 0 ? PublicResult.SUCCESS("修改成功") : PublicResult.ERROR("修改失败");
	}

	@PostMapping("/getMsgs")
	public Result getMsgs(@RequestBody Map<String, Object> param) {
		Page<Msg> page = PageUtil.getPage(param);
		page.setRecords(mm.selectMsgList(page, param.get("postId").toString()));
		return PublicResult.SUCCESS(page);
	}

	@PostMapping("/delMsgById")
	public Result delMsgById(@RequestBody Msg m) {
		return mm.deleteById(m.getId()) > 0 ? PublicResult.SUCCESS("删除成功") : PublicResult.ERROR("删除失败");
	}

}

