package top.mnsx.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.mnsx.content.dto.AddCourseDto;
import top.mnsx.content.dto.CourseBaseInfoDto;
import top.mnsx.content.mapper.CourseBaseMapper;
import top.mnsx.content.mapper.CourseCategoryMapper;
import top.mnsx.content.mapper.CourseMarketMapper;
import top.mnsx.content.po.CourseCategory;
import top.mnsx.content.po.CourseMarket;
import top.mnsx.content.service.CourseBaseInfoService;
import top.mnsx.content.dto.QueryCourseParamsDto;
import top.mnsx.content.po.CourseBase;
import top.mnsx.exception.XXTException;
import top.mnsx.model.PageParams;
import top.mnsx.model.PageResult;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author Mnsx_x xx1527030652@gmail.com
 */
@Slf4j
@Service
public class CourseBaseInfoServiceImpl implements CourseBaseInfoService {
    @Resource
    private CourseBaseMapper courseBaseMapper;
    @Resource
    private CourseMarketMapper courseMarketMapper;
    @Resource
    private CourseCategoryMapper courseCategoryMapper;

    @Override
    public PageResult<CourseBase> queryCourseBaseList(PageParams params, QueryCourseParamsDto queryCourseParamsDto) {
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();

        // 拼接查询条件
        // 根据课程名称模糊查询
        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParamsDto.getCourseName()),
                        CourseBase::getName,
                        queryCourseParamsDto.getCourseName());

        // 根据课程审核状态
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getAuditStatus()),
                CourseBase::getAuditStatus,
                queryCourseParamsDto.getAuditStatus());

        // 根据课程发布状态
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getPublishStatus()),
                CourseBase::getStatus,
                queryCourseParamsDto.getPublishStatus());

        // 分页参数
        Page<CourseBase> page = new Page<>(params.getPageNo(), params.getPageSize());

        // 分页查询
        Page<CourseBase> pageResult = courseBaseMapper.selectPage(page, queryWrapper);

        // 封装返回数据
        // 数据
        List<CourseBase> items = pageResult.getRecords();
        // 总记录数
        long total = pageResult.getTotal();

        return new PageResult<>(items, total, params.getPageNo(), params.getPageSize());
    }

    @Override
    @Transactional
    public CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto dto) {
        // 对参数进行合法性的校验
        if (StringUtils.isBlank(dto.getName())) {
            throw new RuntimeException("课程名称为空");
        }

        if (StringUtils.isBlank(dto.getMt())) {
            throw new RuntimeException("课程分类为空");
        }

        if (StringUtils.isBlank(dto.getSt())) {
            throw new RuntimeException("课程分类为空");
        }

        if (StringUtils.isBlank(dto.getGrade())) {
            throw new RuntimeException("课程等级为空");
        }

        if (StringUtils.isBlank(dto.getTeachmode())) {
            throw new RuntimeException("教育模式为空");
        }

        if (StringUtils.isBlank(dto.getUsers())) {
            throw new RuntimeException("适应人群为空");
        }

        if (StringUtils.isBlank(dto.getCharge())) {
            throw new RuntimeException("收费规则为空");
        }

        // 对数据进行封装，调用mapper进行数据持久化
        CourseBase courseBase = new CourseBase();
        // 将传入dto的数据设置到courseBase中
        // 将dto中和courseBase属性名一样的属性值拷贝到coursebase中
        BeanUtils.copyProperties(dto, courseBase);

        // 设置机构Id
        courseBase.setCompanyId(companyId);
        // 创建时间
        courseBase.setCreateDate(LocalDateTime.now());
        // 审核状态未提交
        courseBase.setAuditStatus("202002");
        // 发布状态未发布
        courseBase.setStatus("203001");

        // 向课程信息表插入数据
        int insert = courseBaseMapper.insert(courseBase);
        // 获取课程id
        Long courseId = courseBase.getId();
        CourseMarket courseMarket = new CourseMarket();
        // 将dto中和courseBase属性名一样的属性值拷贝到coursebase中
        BeanUtils.copyProperties(dto, courseMarket);
        courseMarket.setId(courseId);

        // 如果课程收费，价格必须输入
        String charge = dto.getCharge();
        if (charge.equals("201001")) {
            if (courseMarket.getPrice() == null || courseMarket.getPrice() > 0) {
                XXTException.cast("课程为收费，但是价格为空");
            }
        }

        // 向课程营销表注入数据
        int insert1 = courseMarketMapper.insert(courseMarket);

        if (insert <= 0 || insert1 <= 0) {
            throw new RuntimeException("添加课程失败");
        }

        // 组装返回结构
        return null;
    }

    public CourseBaseInfoDto getCourseBaseInfo(Long courseId) {
        // 基础信息
        CourseBase courseBase = courseBaseMapper.selectById(courseId);

        // 营销信息
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);

        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
        BeanUtils.copyProperties(courseBase, courseBaseInfoDto);
        BeanUtils.copyProperties(courseMarket, courseBaseInfoDto);

        // 根据课程分类的id查询分类的名称
        String mt = courseBase.getMt();
        String st = courseBase.getSt();

        CourseCategory mtCategory = courseCategoryMapper.selectById(mt);
        CourseCategory stCategory = courseCategoryMapper.selectById(st);
        if (mtCategory != null) {
            // 分类名称
            String stName = mtCategory.getName();
            courseBaseInfoDto.setStName(stName);
        }
        if (stCategory != null) {
            // 分类名称
            String mtName = stCategory.getName();
            courseBaseInfoDto.setMtName(mtName);
        }

        return courseBaseInfoDto;
    }
}
