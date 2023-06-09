package com.example.e_market.util;

import java.io.Serializable;

/**
 * 响应结果类
 * @param <E> 响应数据的类型
 */
public class JsonResult<E> implements Serializable {
    /** 状态码 */
    private Integer code;
    /** 数据 */
    private E data;

    public JsonResult() {
        super();
        this.code = 200;
    }

    public JsonResult(Integer state) {
        super();
        this.code = state;
    }

    /** 出现异常时调用 */
    public JsonResult(Throwable e) {
        super();
    }

    public JsonResult(E data) {
        super();
        this.code = 200;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
