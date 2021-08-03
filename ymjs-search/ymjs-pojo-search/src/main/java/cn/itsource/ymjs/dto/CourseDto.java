package cn.itsource.ymjs.dto;

import lombok.Data;

@Data
public class CourseDto {
    private String  courseTypeId;
    private String  gradeName;
    private String  keyword;
    private Integer page = 2;
    private String  priceMax;
    private String  priceMin;
    private Integer rows = 20;
    private String  sortField;
    private String  sortType = "desc";
    private String  tenantName;
}
