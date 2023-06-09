package com.example.e_market.controller;

import com.example.e_market.service.ex.ServiceException;
import com.example.e_market.util.ErrorResult;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.bind.annotation.ExceptionHandler;


/** 控制器类的基类 */
public class BaseController {
    /** 操作成功的状态码 */
    public static final int OK = 200;

    /** @ExceptionHandler用于统一处理方法抛出的异常 */
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public ErrorResult<Void> handleException(Throwable e) {
        ErrorResult<Void> result = new ErrorResult<Void>(e);
        result.setCode(500);
        result.setMessage(e.getMessage());
        return result;
    }
}

