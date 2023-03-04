package top.mnsx.content.service.impl;

import top.mnsx.content.model.po.CourseTeacher;
import top.mnsx.content.mapper.CourseTeacherMapper;
import top.mnsx.content.service.CourseTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程-教师关系表 服务实现类
 * </p>
 *
 * @author mnsx
 * @since 2023-03-03
 */
@Service
public class CourseTeacherServiceImpl extends ServiceImpl<CourseTeacherMapper, CourseTeacher> implements CourseTeacherService {

}
