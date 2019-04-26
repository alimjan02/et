package com.et.pro.teacher.controller;

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
import com.et.pro.teacher.entity.Teacher;
import com.et.pro.teacher.mapper.TeacherMapper;
import com.et.pro.util.PageUtil;
import com.et.pro.util.PublicResult;
import com.et.pro.util.PublicUtils;
import com.et.pro.util.Result;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author
 * @since 2019-03-22
 */
@Controller
public class TeacherController {

    @Autowired
    private TeacherMapper um;

    @PostMapping("/addTeacher")
    public RedirectView addTeacher(Teacher u, MultipartFile file)
            throws IllegalStateException, IOException {
        String fileName = file.getOriginalFilename();
        String filePath = "E:/picture/tpic/";
        File pack = new File(filePath);
        if (!pack.exists()) {
            pack.mkdirs();
        }
        File dest = new File(filePath + fileName);
        file.transferTo(dest);
        String pic = "picture/tpic/" + fileName;
        Teacher Teacher = new Teacher(u.getTeacherName(), u.getDes(), pic, LocalDateTime.now());
        um.insert(Teacher);
        RedirectView rv = null;
        if (PublicUtils.getCurrentUserInfo().getUserType() == 1) {
            rv = new RedirectView("t_teacher.html");
        } else {
            rv = new RedirectView("teacher.html");
        }
        return rv;
    }

    @ResponseBody
    @PostMapping("/teacher/getTeacherById")
    public Result getTeacherById(@RequestBody Teacher u) {
        QueryWrapper<Teacher> qw = new QueryWrapper<>();
        qw.eq("id", u.getId());
        return PublicResult.SUCCESS(um.selectOne(qw));
    }

    @ResponseBody
    @PostMapping("/teacher/updateTeacherById")
    public Result updateTeacherById(@RequestBody Teacher u) {
        return um.updateById(u) > 0 ? PublicResult.SUCCESS("修改成功") : PublicResult.ERROR("修改失败");
    }

    @ResponseBody
    @PostMapping("/teacher/getTeachers")
    public Result addTeacher(@RequestBody Map<String, Object> param) {
        QueryWrapper<Teacher> qw = new QueryWrapper<>();
        Page<Teacher> page = PageUtil.getPage(param);
        if (param != null && param.get("teachername") != null) {
            qw.like("teachername", param.get("teachername"));
        }
        qw.orderByDesc("id");
        IPage<Teacher> result = um.selectPage(page, qw);
        return PublicResult.SUCCESS(result.getRecords(), result.getTotal());
    }

    @ResponseBody
    @PostMapping("/teacher/delTeacherById")
    public Result delTeacherById(@RequestBody Teacher u) {
        return um.deleteById(u.getId()) > 0 ? PublicResult.SUCCESS("删除成功")
                : PublicResult.ERROR("删除失败");
    }
}
