package com.et.pro.course.entity;

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
 * @since 2019-03-21
 */
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private String des;

    @TableField("teacherName")
    private String teacherName;

    private String pic;

    @TableField("createDate")
    private LocalDateTime createDate;
    
    public Course() {
	}

	public Course(String title, String des, String teacherName, String pic, LocalDateTime createDate) {
		this.title = title;
		this.des = des;
		this.teacherName = teacherName;
		this.pic = pic;
		this.createDate = createDate;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
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
        return "Course{" +
        "id=" + id +
        ", title=" + title +
        ", des=" + des +
        ", teacherName=" + teacherName +
        ", pic=" + pic +
        ", createDate=" + createDate +
        "}";
    }
}
