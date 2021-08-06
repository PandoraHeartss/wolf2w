package cn.wolfcode.wolf2w.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public  abstract  class BaseDomain implements Serializable {

    @TableId(type= IdType.AUTO)
    protected Long id;

}



















