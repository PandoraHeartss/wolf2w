package cn.wolfcode.wolf2w.domain;

import com.alibaba.fastjson.JSON;

import com.baomidou.mybatisplus.annotation.TableField;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Getter;

import lombok.Setter;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;

import java.util.Map;

/**
 * 目的地(行政地区：国家/省份/城市)
 */

@Setter
@Getter
@TableName("destination")

public class Destination extends BaseDomain {

    private String name;        //名称
    private String english;  //英文名
    private Long parentId; //上级目的地
    private String parentName;  //上级目的名
    private String info;    //简介
    private String coverUrl;
    @TableField(exist = false)
    private List<Destination> children = new ArrayList<>();

    public String getJsonString() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", super.getId());
        map.put("info", this.info);
        return JSON.toJSONString(map);
    }
}