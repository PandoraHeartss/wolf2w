package cn.wolfcode.wolf2w.query;


import lombok.Getter;
import lombok.Setter;

/**
 * 攻略文章查询参数封装对象
 */
@Setter
@Getter
public class StrategyQuery extends QueryObject {

    private Long destId;
    private Long themeId;
    private Integer type;
    private Integer refid;
    private String orderBy;
}
