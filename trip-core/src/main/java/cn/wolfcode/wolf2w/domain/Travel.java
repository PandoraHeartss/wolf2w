package cn.wolfcode.wolf2w.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 游记
 */
@Setter
@Getter
@TableName("travel")
public class Travel extends BaseDomain {

    public static final int STATE_NORMAL = 0;  //草稿
    public static final int STATE_WAITING = 1;  //待发布(待审核)
    public static final int STATE_RELEASE = 2;  //审核通过
    public static final int STATE_REJECT = 3;  //拒绝

    public static final int ISPUBLIC_NO = 0;
    public static final int ISPUBLIC_YES = 1;


    private Long destId;  //目的地

    private String destName;  //目的地

    private Long authorId;  //作者id

    @TableField(exist = false)
    private UserInfo author;

    private String title;  //标题

    private String summary;//概要

    private String coverUrl; //封面

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date travelTime; //旅游时间

    private Integer avgConsume; //人均消费

    private Integer day;  //旅游天数

    private Integer person;  //和谁旅游

    private Date createTime; //创建时间

    private Date releaseTime; //发布时间

    private Date lastUpdateTime; //最新更新时间内

    private Integer ispublic = ISPUBLIC_NO; //是否发布

    private Integer viewnum;  //点击/阅读数

    private Integer replynum; //回复数

    private Integer favornum;//收藏数

    private Integer sharenum;//分享数

    private Integer thumbsupnum;//点赞数

    private Integer state = STATE_NORMAL;//游记状态

    @TableField(exist = false)
    private TravelContent content;  //游记内容

    public String getStateDisplay() {
        if (state == STATE_NORMAL) {
            return "编辑中";
        } else if (state == STATE_WAITING) {
            return "待发布";
        } else if (state == STATE_RELEASE) {
            return "已发布";
        } else if (state == STATE_REJECT) {
            return "已拒绝";
        }
        return "";
    }

    public String getPersonDisplay() {
        if (person == 1) {
            return "一个人";
        } else if (person == 2) {
            return "情侣/夫妻";
        } else if (person == 3) {
            return "带孩子";
        } else if (person == 4) {
            return "家庭出游";
        } else if (person == 5) {
            return "和朋友";
        } else if (person == 6) {
            return "和同学";
        } else if (person == 7) {
            return "其它";
        }
        return "其它";
    }
}