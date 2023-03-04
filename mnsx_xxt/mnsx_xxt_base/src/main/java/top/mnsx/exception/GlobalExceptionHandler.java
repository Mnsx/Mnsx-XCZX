package top.mnsx.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author Mnsx_x xx1527030652@gmail.com
 */
@Order(9999999)
@ControllerAdvice(basePackages = "top.mnsx.content")
@Slf4j
public class GlobalExceptionHandler {

    // 处理XXTException
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 状态码返回500
    @ExceptionHandler(XXTException.class)
    public RestErrorResponse doXXTException(XXTException e) {
        log.error("捕获异常：{}", e.getErrMessage());

        return new RestErrorResponse(e.getErrMessage());
    }


    // 捕获不可预知的异常
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 状态码返回500
    @ExceptionHandler(Exception.class)
    public RestErrorResponse doException(Exception e) {
        log.error("捕获异常：{}", e.getMessage());
        e.printStackTrace();

        return new RestErrorResponse(CommonError.UNKOWN_ERROR.getErrMessage());
    }
}
