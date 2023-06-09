package com.example.e_market.util;

import java.io.Serializable;

/**
 * 异常结果类
 * @param <E> 响应数据的类型
 */
public class ErrorResult<E> implements Serializable {
    /** 状态码 */
    private Integer code;
    private String message;
    /** 数据 */
    private E data;

    public ErrorResult() {
        super();
    }

    public ErrorResult(Integer state) {
        super();
        this.code = state;
    }

    /** 出现异常时调用 */
    public ErrorResult(Throwable e) {
        super();
    }

    public ErrorResult(Integer state, E data) {
        super();
        this.code = state;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}

