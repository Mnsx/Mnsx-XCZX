package top.mnsx.content.service;

import org.springframework.stereotype.Service;
import top.mnsx.content.dto.AddCourseDto;
import top.mnsx.content.dto.CourseBaseInfoDto;
import top.mnsx.content.dto.QueryCourseParamsDto;
import top.mnsx.content.po.CourseBase;
import top.mnsx.model.PageParams;
import top.mnsx.model.PageResult;

/**
 * @Author Mnsx_x xx1527030652@gmail.com
 */
public interface CourseBaseInfoService {

    /**
     * 课程查询
     * @param params 分页参数
     * @param queryCourseParamsDto 查询跳进啊
     * @return 分页数据
     */
    public PageResult<CourseBase> queryCourseBaseList(PageParams params, QueryCourseParamsDto queryCourseParamsDto);

    /**
     * 新增课程
     * @param companyId 机构id
     * @param addCourseDto 添加参数
     * @return 相应参数
     */
    public CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto);
}
