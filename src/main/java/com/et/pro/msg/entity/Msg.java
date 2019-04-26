package com.et.pro.msg.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2019-03-21
 */
public class Msg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;	

    private String msg;

    /**
     * 回复给。。
     */
    @TableField("msgTo")
    private Integer msgTo;

    @TableField("postId")
    private Integer postId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField("createDate")
    private LocalDateTime createDate;

    @TableField("createUser")
    private Integer createUser;
    @TableField(exist = false)
    private String n1;
    @TableField(exist = false)
    private String n2;

    public Msg() {
	}

	public Msg(String msg, Integer msgTo, Integer postId, LocalDateTime createDate, Integer createUser) {
		this.msg = msg;
		this.msgTo = msgTo;
		this.postId = postId;
		this.createDate = createDate;
		this.createUser = createUser;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getMsgTo() {
        return msgTo;
    }

    public void setMsgTo(Integer msgTo) {
        this.msgTo = msgTo;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }
    
    public String getN1() {
		return n1;
	}
    
	public void setN1(String n1) {
		this.n1 = n1;
	}
	
	public String getN2() {
		return n2;
	}

	public void setN2(String n2) {
		this.n2 = n2;
	}

	@Override
	public String toString() {
		return "Msg [id=" + id + ", msg=" + msg + ", msgTo=" + msgTo + ", postId=" + postId + ", createDate="
				+ createDate + ", createUser=" + createUser + ", n1=" + n1 + ", n2=" + n2 + "]";
	}

}
