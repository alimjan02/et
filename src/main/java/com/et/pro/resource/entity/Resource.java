package com.et.pro.resource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2019-03-21
 */
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String des;

    @TableField("resPath")
    private String resPath;

    private Integer num;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField("createDate")
    private LocalDateTime createDate;

    @TableField("createUser")
    private Integer createUser;
    @TableField("fileName")
    private String fileName;

    public Resource() {
	}

	public Resource(String des, String resPath, LocalDateTime createDate, Integer createUser,
			String fileName) {
		this.des = des;
		this.resPath = resPath;
		this.createDate = createDate;
		this.createUser = createUser;
		this.fileName = fileName;
	}



	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getResPath() {
        return resPath;
    }

    public void setResPath(String resPath) {
        this.resPath = resPath;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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
    
    public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
    public String toString() {
        return "Resource{" +
        "id=" + id +
        ", des=" + des +
        ", resPath=" + resPath +
        ", num=" + num +
        ", createDate=" + createDate +
        ", createUser=" + createUser +
        "}";
    }
}
