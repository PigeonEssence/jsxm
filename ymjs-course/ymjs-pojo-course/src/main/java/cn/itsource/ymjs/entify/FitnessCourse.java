package cn.itsource.ymjs.entify;


import cn.itsource.ymjs.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

//健身课程
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "t_course")
@Data
public class FitnessCourse extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //课程名
    @Column
    private String name;
    //图片
    @Column
    private String pic;
    //提示
    @Column
    private String prompt;

    //课程价格
    @Column(columnDefinition = "decimal(18,0) ")
    private BigDecimal amount;
    //等级名
    @Column(name = "grade_name")
    private String gradeName;
    //等级id
    @Column(name = "grade_id")
    private Long gradeId;
    //课程时长
    @Column(name = "time_long")
    private Integer timeLong;
    //创建者用户名
    @Column(name = "create_user_name")
    private String createUserName;

    @Column
    private boolean official;
}
