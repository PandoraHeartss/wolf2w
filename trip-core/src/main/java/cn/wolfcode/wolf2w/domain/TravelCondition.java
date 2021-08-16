package cn.wolfcode.wolf2w.domain;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


@Getter
public class TravelCondition {

    //旅游时间
    public static final Map<Integer, TravelCondition> TIMEMAP = new HashMap<>();

    //旅游天数 
    public static final Map<Integer, TravelCondition> DAYMAP = new HashMap<>();

    //旅游人均 
    public static final Map<Integer, TravelCondition> CONSUMEMAP = new HashMap<>();

    static {
        TIMEMAP.put(1, new TravelCondition(1, 2));
        TIMEMAP.put(2, new TravelCondition(3, 4));
        TIMEMAP.put(3, new TravelCondition(5, 6));
        TIMEMAP.put(4, new TravelCondition(7, 8));
        TIMEMAP.put(5, new TravelCondition(9, 10));
        TIMEMAP.put(6, new TravelCondition(11, 12));

        DAYMAP.put(1, new TravelCondition(0, 3));
        DAYMAP.put(2, new TravelCondition(4, 7));
        DAYMAP.put(3, new TravelCondition(8, 14));
        DAYMAP.put(4, new TravelCondition(15, Integer.MAX_VALUE));

        CONSUMEMAP.put(1, new TravelCondition(0, 999));
        CONSUMEMAP.put(2, new TravelCondition(1000, 6000));
        CONSUMEMAP.put(3, new TravelCondition(6001, 20000));
        CONSUMEMAP.put(4, new TravelCondition(20001, Integer.MAX_VALUE));
    }

    private int min;
    private int max;

    public TravelCondition(int min, int max) {
        this.max = max;
        this.min = min;
    }

}