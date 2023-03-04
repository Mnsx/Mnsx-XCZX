package top.mnsx.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.mnsx.content.dto.CourseCategoryTreeDto;
import top.mnsx.content.po.CourseCategory;

import java.util.List;

/**
 * <p>
 * 课程分类 Mapper 接口
 * </p>
 *
 * @author mnsx
 * @since 2023-03-03
 */
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {
    List<CourseCategoryTreeDto> selectTreeNodes(String id);
}
