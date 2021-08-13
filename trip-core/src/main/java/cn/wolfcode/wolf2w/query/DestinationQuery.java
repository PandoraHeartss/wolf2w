package cn.wolfcode.wolf2w.query;


import lombok.Getter;
import lombok.Setter;

/**
 * 目的地表查询参数封装对象
 */
@Setter
@Getter
public class DestinationQuery extends QueryObject {

    private Long parentId;//父id

}
