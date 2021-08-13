package cn.wolfcode.wolf2w.domain;

import com.alibaba.fastjson.JSON;

import com.baomidou.mybatisplus.annotation.TableField;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;

import lombok.Setter;

import org.springframework.util.StringUtils;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Map;

/**
 * 区域
 */

@Setter
@Getter
@TableName("region")
public class Region extends BaseDomain {

    public static final int STATE_HOT = 1;
    public static final int STATE_NORMAL = 0;
    private String name;        //地区名
    private String sn;          //地区编码
    private String refIds;     //关联的id， 多个以，隔开
    private Integer ishot = STATE_NORMAL;         //是否为热点
    private Integer seq;   //序号
    private String info;  //简介

    public List<Long> parseRefIds() {
        List<Long> ids = new ArrayList<>();
        if (StringUtils.hasLength(refIds)) {
            String[] split = refIds.split(",");
            if (split != null && split.length > 0) {
                for (int i = 0; i < split.length; i++) {
                    ids.add(Long.parseLong(split[i]));
                }
            }
        }
        return ids;
    }

    public String getJsonString() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("sn", sn);
        map.put("refIds", getRefIds());
        map.put("ishot", ishot);
        map.put("seq", seq);
        map.put("info", info);
        return JSON.toJSONString(map);
    }

}