package top.mnsx.content.model.dto;

import lombok.Data;

/**
 * @Author Mnsx_x xx1527030652@gmail.com
 */
@Data
public class QueryCourseParamsDto {
    //审核状态
    private String auditStatus;
    //课程名称
    private String courseName;
    //发布状态
    private String publishStatus;
}
