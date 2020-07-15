package cn.honghu.blog.common.web.response;

import cn.honghu.blog.common.enums.ReturnCodeEnum;
import java.io.Serializable;

public class ResponseSet implements Serializable {

    int code;

    String msg;

    public ResponseSet() {
    }

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

    public ResponseSet(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseSet(ReturnCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }

    @Override
    public String toString() {
        return "ResponseSet{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
