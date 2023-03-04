package top.mnsx.content.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @Author Mnsx_x xx1527030652@gmail.com
 */
@Data
@ToString
public class QueryCourseParamsDto {
    //审核状态
    private String auditStatus;
    //课程名称
    private String courseName;
    //发布状态
    private String publishStatus;
}
