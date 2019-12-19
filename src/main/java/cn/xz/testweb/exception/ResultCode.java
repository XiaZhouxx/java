package cn.xz.testweb.exception;

public enum ResultCode {
    NAME_NOT_FOUND("400","未找到该名字");

    /**
     * 错误码，根据业务定义 int 还是 String
     */
    private String code;
    private String msg;

    public String getCode() {

        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
