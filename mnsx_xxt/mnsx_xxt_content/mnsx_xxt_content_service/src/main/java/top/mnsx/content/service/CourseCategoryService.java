package top.mnsx.content.service;

import top.mnsx.content.dto.CourseCategoryTreeDto;

import java.util.List;

/**
 * @Author Mnsx_x xx1527030652@gmail.com
 */
public interface CourseCategoryService {
    public List<CourseCategoryTreeDto> queryTreeNodes(String id);
}
