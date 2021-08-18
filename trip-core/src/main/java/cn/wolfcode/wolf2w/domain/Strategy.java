package cn.wolfcode.wolf2w.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 攻略
 */
@Setter
@Getter
@TableName("strategy")
public class Strategy extends BaseDomain {
    public static final int ABROAD_NO = 0;  //国内
    public static final int ABROAD_YES = 1;  //国外

    public static final int STATE_NORMAL = 0;  //带发布
    public static final int STATE_PUBLISH = 1; //发布

    private Long destId;  //关联的目的地
    private String destName;

    private Long themeId; //关联主题
    private String themeName;

    private Long catalogId;  //关联的分类
    private String catalogName;

    private String title;  //标题

    private String subTitle; //副标题

    private String summary;  //内容摘要

    private String coverUrl;  //封面

    private Date createTime;  //创建时间

    private Integer isabroad = ABROAD_NO;  //是否是国外

    private Integer viewnum;  //点击数

    private Integer replynum;  //攻略评论数

    private Integer favornum; //收藏数

    private Integer sharenum; //分享数

    private Integer thumbsupnum; //点赞个数

    private Integer state = STATE_NORMAL;  //状态

    @TableField(exist = false)
    private StrategyContent content; //攻略内容

    public String getStateDisplay() {
        return state == STATE_PUBLISH ? "发布" : "待发布";
    }
}
