package cn.wolfcode.wolf2w.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.io.Serializable;

@Setter
@Getter
public class QueryObject implements Serializable {

    private int currentPage = 1;
    private int pageSize = 10;

    private String keyword;

    public String getKeyword() {
        return StringUtils.hasLength(keyword) ? keyword : null;
    }


}
