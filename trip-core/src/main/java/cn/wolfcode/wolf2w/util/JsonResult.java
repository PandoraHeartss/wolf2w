package cn.wolfcode.wolf2w.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * jsonResult
 * {
 * code:状态码
 * msg: 状态信息
 * data:返回数据
 * }
 */
@Setter
@Getter
@NoArgsConstructor
public class JsonResult<T> {
    public static final int CODE_SUCCESS = 200;
    public static final String MSG_SUCCESS = "操作成功";
    public static final int CODE_NOLOGIN = 401;
    public static final String MSG_NOLOGIN = "请先登录";
    public static final int CODE_ERROR = 500;
    public static final String MSG_ERROR = "系统异常，请联系管理员";
    public static final int CODE_ERROR_PARAM = 501;  //参数异常

    private int code;  //区分不同结果, 而不再是true或者false
    private String msg;
    private T data;  //除了操作结果之后, 还行携带数据返回

    public JsonResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> JsonResult success(T data) {
        return new JsonResult(CODE_SUCCESS, MSG_SUCCESS, data);
    }

    public static JsonResult success() {
        return new JsonResult(CODE_SUCCESS, MSG_SUCCESS, null);
    }

    public static <T> JsonResult error(int code, String msg, T data) {
        return new JsonResult(code, msg, data);
    }

    public static JsonResult defaultError() {
        return new JsonResult(CODE_ERROR, MSG_ERROR, null);
    }


    public static JsonResult noLogin() {
        return new JsonResult(CODE_NOLOGIN, MSG_NOLOGIN, null);
    }
}
