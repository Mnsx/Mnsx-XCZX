package top.mnsx.content.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.mnsx.content.dto.AddCourseDto;
import top.mnsx.content.dto.CourseBaseInfoDto;
import top.mnsx.content.dto.QueryCourseParamsDto;
import top.mnsx.content.service.CourseBaseInfoService;
import top.mnsx.model.PageParams;
import top.mnsx.model.PageResult;
import top.mnsx.content.po.CourseBase;

/**
 * @Author Mnsx_x xx1527030652@gmail.com
 */
@RestController
public class CourseBaseInfoController {
    @Autowired
    private CourseBaseInfoService courseBaseInfoService;

    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageParams pageParams, @RequestBody QueryCourseParamsDto queryCourseParams) {
        // 调用Service获取数据
        return courseBaseInfoService.queryCourseBaseList(pageParams, queryCourseParams);
    }

    @PostMapping("/course")
    public CourseBaseInfoDto createCourseBase(@RequestBody AddCourseDto addCOurseDto) {
        // 获取当前用户培训机构的Id
        Long companyId = 22L;

        return courseBaseInfoService.createCourseBase(companyId, addCOurseDto);
    }
}
