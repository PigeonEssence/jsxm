package cn.itsource.ymjs.constants;

/**======================================================================================
 * 方法描述：页面传入排序编号，根据编号映射到对应字段
 ======================================================================================*/
public enum  SortConstants {

    //课程按照价格排序
    COURSE_SORT_FIELD_PRICE("price","jg");

    private String sn;
    private String field;

    SortConstants(String field, String sn) {
        this.field = field;
        this.sn = sn;
    }

    public String getSn() {
        return sn;
    }

    public String getField() {
        return field;
    }
    //根据编号获取字段
    public static String getField(String sn) {
        for (SortConstants value : SortConstants.values()) {
            if(value.getSn().equals(sn)){
                return value.getField();
            }
        }
        return "";
    }
}
