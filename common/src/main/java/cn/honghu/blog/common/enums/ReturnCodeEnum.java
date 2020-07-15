package cn.honghu.blog.common.enums;

public enum ReturnCodeEnum {

    FAILED(0, "failed"),
    SUCCESS(200, "成功"),
    PARAM_ERROR(-8, "参数错误"),
    UN_SUPPORTED_HTTP_METHOD(402, "不支持的请求方式"),
    IP_INVALID(403, "IP_INVALID"),
    INTERNAL_SERVICE_EXCEPTION(500, "服务异常"),
    FREQUENCY_REQUEST(429, "请求过于频繁"),
    ;

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ReturnCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
