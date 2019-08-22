package com.taoqy.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/8/12
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */
public interface ScheduleMapper {
    @Select("SELECT CRON FROM schedule WHERE SCHEDULE_ID = #{id}")
    String getCron(@Param("id") Integer id);
}
