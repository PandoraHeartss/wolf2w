package cn.wolfcode.wolf2w.query;


import lombok.Getter;
import lombok.Setter;

/**
 * 游记表查询参数封装对象
 */
@Setter
@Getter
public class TravelQuery extends QueryObject {

    private Long destId;

    private String orderBy = "create_time";  //排序

    private int travelTimeType = -1; //旅游时间

    private int consumeType = -1; //人均消费

    private int dayType = -1; //·旅游天数

}
