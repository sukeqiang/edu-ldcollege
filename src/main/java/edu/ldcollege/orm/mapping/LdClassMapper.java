package edu.ldcollege.orm.mapping;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import edu.ldcollege.orm.domain.LdClass;

public interface LdClassMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ldclass
     *
     * @mbg.generated
     */
    @Delete({
        "delete from ldclass",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ldclass
     *
     * @mbg.generated
     */
    @Insert({
        "insert into ldclass (id, className, ",
        "createTime, endTime, ",
        "remark)",
        "values (#{id,jdbcType=INTEGER}, #{classname,jdbcType=VARCHAR}, ",
        "#{createtime,jdbcType=TIMESTAMP}, #{endtime,jdbcType=TIMESTAMP}, ",
        "#{remark,jdbcType=VARCHAR})"
    })
    int insert(LdClass record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ldclass
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, className, createTime, endTime, remark",
        "from ldclass",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="className", property="classname", jdbcType=JdbcType.VARCHAR),
        @Result(column="createTime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="endTime", property="endtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    LdClass selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ldclass
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, className, createTime, endTime, remark",
        "from ldclass"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="className", property="classname", jdbcType=JdbcType.VARCHAR),
        @Result(column="createTime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="endTime", property="endtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<LdClass> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ldclass
     *
     * @mbg.generated
     */
    @Update({
        "update ldclass",
        "set className = #{classname,jdbcType=VARCHAR},",
          "createTime = #{createtime,jdbcType=TIMESTAMP},",
          "endTime = #{endtime,jdbcType=TIMESTAMP},",
          "remark = #{remark,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(LdClass record);
}