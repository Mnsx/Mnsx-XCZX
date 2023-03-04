package top.mnsx.content;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.mnsx.content.dto.CourseCategoryTreeDto;
import top.mnsx.content.mapper.CourseBaseMapper;
import top.mnsx.content.service.CourseBaseInfoService;
import top.mnsx.content.dto.QueryCourseParamsDto;
import top.mnsx.content.po.CourseBase;
import top.mnsx.content.service.CourseCategoryService;
import top.mnsx.model.PageParams;
import top.mnsx.model.PageResult;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;

/**
 * @Author Mnsx_x xx1527030652@gmail.com
 */
@SpringBootTest
public class CourseBaseMapperTests {
    @Resource
    CourseBaseMapper courseBaseMapper;
    @Autowired
    CourseCategoryService courseCategoryService;
    @Autowired
    CourseBaseInfoService courseBaseInfoService;

    @Test
    void testCourseCategoryService() {
        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryService.queryTreeNodes("1");
        System.out.println(courseCategoryTreeDtos);
    }

    @Test
    void testCourseBaseMapper() {
        CourseBase courseBase = courseBaseMapper.selectById(74L);
        Assertions.assertNotNull(courseBase);
    }

    @Test
    void testCourseBaseInfoService() {
        PageParams pageParams = new PageParams();
        PageResult<CourseBase> courseBasePageResult = courseBaseInfoService.queryCourseBaseList(pageParams, new QueryCourseParamsDto());
        System.out.println(courseBasePageResult);
    }
}
