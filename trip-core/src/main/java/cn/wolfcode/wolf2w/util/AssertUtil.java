package cn.wolfcode.wolf2w.util;
import cn.wolfcode.wolf2w.exception.LogicException;
import org.springframework.util.StringUtils;
/**
 * 参数断言工具类
 */
public class AssertUtil {

    private AssertUtil() {
    }

    /**
     * 判断指定参数是否为null 或者 "" ,如果是抛出 msg这个异常信息
     *
     * @param value
     * @param msg
     */
    public static void hasLength(String value, String msg) {
        if (!StringUtils.hasLength(value)) {
            throw new LogicException(msg);
        }
    }

    /**
     * 判断2个参数是否一致
     *
     * @param v1
     * @param v2
     * @param msg
     */
    public static void isEquals(String v1, String v2, String msg) {
        if (v1 == null || v2 == null) {
            throw new LogicException("输入参数数不能为null");
        }
        if (!v1.equals(v2)) {
            throw new LogicException(msg);
        }
    }
}
