package top.mnsx.content.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mnsx.content.dto.CourseCategoryTreeDto;
import top.mnsx.content.po.CourseCategory;
import top.mnsx.content.service.CourseCategoryService;

import java.util.List;

/**
 * @Author Mnsx_x xx1527030652@gmail.com
 */
@RestController
@Slf4j
public class CourseCategoryController {
    @Autowired
    private CourseCategoryService courseCategoryService;

    @GetMapping("/course-category/tree-nodes")
    public List<CourseCategoryTreeDto> queryTreeNodes() {
        return courseCategoryService.queryTreeNodes("1");
    }
}
