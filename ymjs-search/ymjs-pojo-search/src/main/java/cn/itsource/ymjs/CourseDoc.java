package cn.itsource.ymjs;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;


@Data
//标记该对象是ES的文档对象
//indexName 索引库
//type 类型
@Document(indexName = "ymjs",type = "course")
public class CourseDoc {

    //标记为文档ID
    @Id
    private Long id;

    //课程标题需要分词，和指定分词器
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String name;

    /**
     * 课程等级
     */
    @Field(type = FieldType.Keyword)
    private String gradeName;

    /**
     * 上线时间
     */
    @Field(type = FieldType.Date)
    private Date onlineTime;

}
