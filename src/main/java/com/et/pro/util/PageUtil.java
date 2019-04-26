package com.et.pro.util;

import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class PageUtil {

	public static <T> Page<T> getPage(Map<String, Object> map) {
		int offset = Integer.valueOf(map.get("offset").toString());
		int limit = Integer.valueOf(map.get("limit").toString());
		int currentPage = (offset / limit) + 1;
		return new Page<T>(currentPage,limit);
	}
}
