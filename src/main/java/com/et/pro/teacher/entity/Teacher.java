package com.et.pro.teacher.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2019-03-22
 */
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("teacherName")
    private String teacherName;

    private String des;

    private String pic;

    @TableField("createDate")
    private LocalDateTime createDate;
    
    public Teacher() {
	}
    
	public Teacher(String teacherName, String des, String pic, LocalDateTime createDate) {
		this.teacherName = teacherName;
		this.des = des;
		this.pic = pic;
		this.createDate = createDate;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Teacher{" +
        "id=" + id +
        ", teacherName=" + teacherName +
        ", des=" + des +
        ", pic=" + pic +
        ", createDate=" + createDate +
        "}";
    }
}
