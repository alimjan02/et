package com.et.pro.msg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.et.pro.msg.entity.Msg;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author
 * @since 2019-03-21
 */
public interface MsgMapper extends BaseMapper<Msg> {

	@Select("select msg.id,u2.nickname n2,msg.msg,msg.createDate,user.nickname n1 "
			+ " from msg left join user on msg.createUser=user.id"
			+ " left join user u2 on u2.id=msg.msgTo where msg.postId=#{postId} order by msg.id asc")
	List<Msg> selectMsgList(IPage<Msg> page, @Param("postId") String postId);
	
	@Select("select msg.id,u2.nickname n2,msg.msg,msg.createDate,user.nickname n1 "
			+ " from msg left join user on msg.createUser=user.id"
			+ " left join user u2 on u2.id=msg.msgTo where msg.postId=#{postId} order by msg.id asc")
	List<Msg> selectMsgList(@Param("postId") Integer postId);
}
