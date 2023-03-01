package top.mnsx.content.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.mnsx.content.model.dto.QueryCourseParamsDto;
import top.mnsx.content.model.po.CourseBase;
import top.mnsx.model.PageParams;
import top.mnsx.model.PageResult;

/**
 * @Author Mnsx_x xx1527030652@gmail.com
 */
@RestController
public class CourseBaseInfoController {

    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageParams pageParams, @RequestBody QueryCourseParamsDto queryCourseParamsDto) {
        return null;
    }
}
