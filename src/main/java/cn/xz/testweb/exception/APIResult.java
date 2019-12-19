package cn.xz.testweb.exception;

/**
 * @author xz
 * @ClassName APIResult
 * @Description
 * @date 2019/12/19 0019 18:35
 **/
public class APIResult<T> {
    /**
     * result message
     */
    private String msg;

    /**
     * result code/error_code
     */
    private String code;

    /**
     * result data
     */
    private T data;

    public APIResult(ResultCode result) {
        this.code = result.getCode();
        this.msg = result.getMsg();
    }

    public APIResult(ResultCode result, T data) {
        this.code = result.getCode();
        this.msg = result.getMsg();
        this.data = data;
    }

    public APIResult(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
