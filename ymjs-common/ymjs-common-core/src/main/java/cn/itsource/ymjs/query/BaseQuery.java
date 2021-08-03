package cn.itsource.ymjs.query;

import lombok.Data;

@Data
public class BaseQuery {
    //第几页
    private Integer currentPage = 1;
    //每页条数
    private Integer pageSize = 10;
    //关键字查询
    private String keyword;
    //排序列
    private String sortField;
    //排序方式
    private String sortType;
    //分页开始位置
    public Integer getStart(){
        return (currentPage - 1) * pageSize;
    }
    //关键字查询去空字符串
    public String getKeyword() {
        return keyword == null || keyword.trim().length() == 0 ?null:keyword.trim();
    }
}
