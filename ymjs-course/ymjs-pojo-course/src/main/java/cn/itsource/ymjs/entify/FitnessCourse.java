package cn.itsource.ymjs.entify;


import cn.itsource.ymjs.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

//健身课程
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "t_fitness_course")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    //卡路里
    @Column(name = "calories")
    private Integer calories;

    //适用人群
    @Column(name = "calories")
    private Integer forUser;

    @Column
    private boolean official;
    //课程状态
    @Column(name = "status")
    private Integer status;
    //上线时间
    @Column(name = "online_time")
    private Date onlineTime;
}
