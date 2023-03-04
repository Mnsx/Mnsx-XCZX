package top.mnsx.content.dto;

import lombok.Data;
import top.mnsx.content.po.CourseCategory;

import java.util.List;

/**
 * @Author Mnsx_x xx1527030652@gmail.com
 */
@Data
public class CourseCategoryTreeDto extends CourseCategory {

    List childrenTreeNodes;
}
