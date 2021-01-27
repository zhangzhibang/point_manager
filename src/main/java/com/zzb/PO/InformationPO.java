package com.zzb.PO;

import lombok.Data;

import java.io.Serializable;
/**数据库查询结果总报表对应展示实体类
 * @author zzbang
 */
@Data
public class InformationPO implements Serializable {
    private String projectnum;
    /**
     * 项目类型
     */

    private String ProName;
    private String projecttype;
    /**
     * 项目等级
     */
    private String projectrank;
    /**
     * 评价得分
     */
    private Double score;
    /**
     * 积分基数
     */
    private Double pointbase;

    /**
     * 项目阶段
     */
    private String projectstage;
    /**
     * 阶段占比
     */
    private Double stageradio;

    /**
     * 阶段开始日期
     */
    private String startdate;

    /**
     * 阶段结束日期
     */
    private String enddate;

    /**
     * 员工编号
     */
    private String sfid;

    /**
     * 部门名称
     */
    private String deptname;

    /**
     * 阶段名称
     */
    private String stagenum;

    /**
     * 员工该阶段占比
     */
    private Double Uradio;

    private String username;

    /**
     * 项目2018年占比
     */
    private Double radio2018;

    /**
     *项目2019占比
     */

    private Double radio2019;
    /**
     * 项目2020占比
     */
    private Double radio2020;

    /**
     * 个人在项目总积分
     */
    private Double total;
    /**
     * 2018年积分
     */
    private Double point2018;
    /**
     * 2019年积分
     */
    private Double point2019;
    /**
     * 2020年积分
     */
    private Double point2020;

    /**
     * 2018年项目数
     */
    private Integer projectCount2018;
    /**
     * 2019项目数量
     */
    private Integer projectCount2019;
    /**
     * 2020项目数量
     */
    private Integer projectCount2020;
}
