package cn.xz.testweb.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xz
 * @ClassName GlobalExceptionHandler
 * @Description
 * @date 2019/12/19 0019 17:52
 **/
@ControllerAdvice
// @RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public APIResult exception(Exception e) {
        /**
         *         自定义异常 就从异常类中取得 ResultCode
         *         其他系统异常就做统一处理
         *         貌似只能返回对象 直接返回字符串会乱码
         */
        return new APIResult(ResultCode.NAME_NOT_FOUND);
    }
}
