package cn.wolfcode.wolf2w.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 游记内容
 */
@Setter
@Getter
@TableName("travel_content")
public class TravelContent implements Serializable {
    private Long id;
    private String content;
}

