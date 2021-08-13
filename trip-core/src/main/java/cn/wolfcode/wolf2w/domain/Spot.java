package cn.wolfcode.wolf2w.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("spot")
public class Spot {

    private Long id; //景点id
    private String name;//名称
    private Integer people;//人数
    private String phone;//联系电话
    private Integer time;//用时
    private String images;//图片
    //设置景点内容属性
    @TableField(exist = false)
    private SpotContent content;

}
