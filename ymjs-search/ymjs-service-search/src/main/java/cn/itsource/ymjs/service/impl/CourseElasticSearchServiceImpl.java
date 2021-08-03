package cn.itsource.ymjs.service.impl;

import cn.itsource.ymjs.CourseDoc;
import cn.itsource.ymjs.dto.CourseDto;
import cn.itsource.ymjs.mapper.HighlightResultMapper;
import cn.itsource.ymjs.repository.CourseElasticSearchRepository;
import cn.itsource.ymjs.service.CourseElasticSearchService;
import cn.itsource.ymjs.utils.PageList;
import cn.itsource.ymjs.vo.AggrTermsVo;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseElasticSearchServiceImpl implements CourseElasticSearchService {

    @Autowired
    private CourseElasticSearchRepository repository;

    @Autowired
    private ElasticsearchTemplate template;

    @Override
    public PageList<CourseDoc> searchCourse(CourseDto courseDto) {
        //* 1.把dto中的查询条件，转换成ES的查询：NativeSearchQueryBuilder
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        /**--------------------------------------------------------
         * 代码块说明：排序
         --------------------------------------------------------**/
        if(StringUtils.hasLength(courseDto.getSortField())){

            //默认新品
            String fieldName = null;

            switch (courseDto.getSortField().toLowerCase()){
                case "xl":fieldName = "saleCount";break;
                case "xp":fieldName = "onlineTime";break;
                case "pl":fieldName = "commentCount";break;
                case "jg":fieldName = "price";break;
                case "rq":fieldName = "viewCount";break;
                default: fieldName = "onlineTime";
            }

            SortOrder sortOrder = SortOrder.DESC;

            if(StringUtils.hasLength(courseDto.getSortType()) && courseDto.getSortType().toLowerCase().equals("asc")){
                sortOrder = SortOrder.ASC;
            }

            //排序
            queryBuilder.withSort(SortBuilders.fieldSort(fieldName).order(sortOrder));
        }

        /**--------------------------------------------------------
         * 代码块说明：分页
         --------------------------------------------------------**/
        queryBuilder.withPageable(PageRequest.of(courseDto.getPage() - 1, courseDto.getRows()));


        /**--------------------------------------------------------
         * 代码块说明：查询条件
         --------------------------------------------------------**/
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        //must :课程名，关键字查询
        if(StringUtils.hasLength(courseDto.getKeyword())){
            boolQuery.must(QueryBuilders.matchQuery("name", courseDto.getKeyword()));
        }

        //filter :

        //课程类型
        if(courseDto.getCourseTypeId() != null){
            boolQuery.filter(QueryBuilders.termQuery("courseTypeId", courseDto.getCourseTypeId()));
        }
        //等级名
        if(StringUtils.hasLength(courseDto.getGradeName())){
            boolQuery.filter(QueryBuilders.termQuery("gradeName", courseDto.getGradeName()));
        }
        //机构名
        if(StringUtils.hasLength(courseDto.getTenantName())){
            boolQuery.filter(QueryBuilders.termQuery("tenantName", courseDto.getTenantName()));
        }
        //最小价格
        if(null != courseDto.getPriceMin()){
            boolQuery.filter(QueryBuilders.rangeQuery("price").gte(courseDto.getPriceMin()));
        }
        //最大价格
        if(null != courseDto.getPriceMax()){
            boolQuery.filter(QueryBuilders.rangeQuery("price").lte(courseDto.getPriceMax()));
        }

        queryBuilder.withQuery(boolQuery);


        /**--------------------------------------------------------
         * 代码块说明：课程名字高亮
         --------------------------------------------------------**/
        //设置高亮显示
        HighlightBuilder.Field field = new HighlightBuilder.Field("name").preTags("<font style='color:red'><b>").postTags("</b></font>");

        queryBuilder.withHighlightFields(field);  // 名字高亮
     /*   //2.调用repository的search方法查询
        AggregatedPage<CourseDoc> page = template.queryForPage(queryBuilder.build(),CourseDoc.class,highlightResultMapper);
 */       /**--------------------------------------------------------
         * 代码块说明：查询结果
         --------------------------------------------------------**/
/*        //* 3.把ES的Page 转换成 PageList
        return new PageList<>(page.getTotalElements(),page.getContent());*/

        /**--------------------------------------------------------
         * 代码块说明：聚合查询
         --------------------------------------------------------**/
        //机构名聚合
        //{ "error" : "JsonGenerationException[Can not write a field name, expecting a value]"}//
        TermsAggregationBuilder tenantNameAgg = AggregationBuilders
                .terms("tenantNameAgg") //聚合名
                .field("tenantName")    //按照机构名聚合
               // .order(Terms.Order.count(false))    //按照count排序，倒排
                .size(20);//取前20条

        //等级聚合
        TermsAggregationBuilder gradeNameAgg = AggregationBuilders
                .terms("gradeNameAgg") //聚合名
                .field("gradeName")    //按照机构名聚合
                //.order(Terms.Order.count(false))    //按照count排序，倒排
                .size(20);//取前20条

        queryBuilder.addAggregation(tenantNameAgg).addAggregation(gradeNameAgg);

        /**--------------------------------------------------------
         * 代码块说明：查询结果
         --------------------------------------------------------**/
        //* 2.调用repository执行查询，得到Page
        //Page<CourseDoc> page = repository.search(queryBuilder.build());

        AggregatedPage<CourseDoc> page = template.queryForPage(queryBuilder.build(), CourseDoc.class);


        /**--------------------------------------------------------
         * 代码块说明：处理聚合结果
         --------------------------------------------------------**/
        //用来封装聚合结果
        Map<String, List<AggrTermsVo>> aggrMap = new HashMap<>();

        //遍历聚合结果
        Aggregations aggregations = page.getAggregations();

        Map<String, Aggregation> stringAggregationMap = aggregations.asMap();

        Set<Map.Entry<String, Aggregation>> entries = stringAggregationMap.entrySet();

        entries.forEach(e->{
            //集合的名字
            String key = e.getKey();
            //集合的值的处理
            StringTerms value = (StringTerms)e.getValue();
            List<StringTerms.Bucket> buckets = value.getBuckets();

            //使用lomda 把  List<StringTerms.Bucket>  转成  List<AggrTermsVo>
            List<AggrTermsVo> aggrTermsVos = buckets.stream().map(bucket -> {
                return new AggrTermsVo(bucket.getKeyAsString(), bucket.getDocCount());
            }).collect(Collectors.toList());

            aggrMap.put(key, aggrTermsVos);
        });
        /*{ "error" : "JsonGenerationException[Can not write a field name, expecting a value]"}*/
        PageList<CourseDoc> courseDocPageList = new PageList<>(page.getTotalElements(), page.getContent(), aggrMap);

        //* 3.把ES的Page 转换成 PageList
        return courseDocPageList;
    }
}
