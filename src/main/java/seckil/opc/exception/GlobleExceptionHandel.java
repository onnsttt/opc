package seckil.opc.exception;


import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import seckil.opc.Result.CodeMsg;
import seckil.opc.Result.Result;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobleExceptionHandel {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
        if (e instanceof GlobleException) {
            GlobleException exception = (GlobleException) e;
            return Result.error(exception.getCodeMsg());
        }
        if (e instanceof BindException) {
            BindException exception = (BindException) e;

            ObjectError error = exception.getAllErrors().get(0);
            String msg = error.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
