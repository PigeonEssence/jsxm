package cn.itsource.ymjs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//封装terms聚合结果的vo
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AggrTermsVo {
    private String key;
    private Long docCount;

}
