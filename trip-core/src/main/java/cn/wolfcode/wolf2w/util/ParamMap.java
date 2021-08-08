package cn.wolfcode.wolf2w.util;

import java.util.HashMap;

/**
 * 参数映射对象
 */
public class ParamMap extends HashMap<String, Object> {

    @Override
    public ParamMap put(String key, Object value) {
        super.put(key, value);
        return this;
    }


    public static ParamMap newInstance() {
        return new ParamMap();
    }
}
