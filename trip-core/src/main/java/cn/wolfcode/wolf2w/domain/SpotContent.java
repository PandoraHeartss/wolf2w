package cn.wolfcode.wolf2w.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("spotContent")
public class SpotContent {

    private Long id;
    private String english;
    private String introduce;
    private String website;
    private String traffic;
    private String tickets;
    private String openingTime;
}
