package com.itluobo.wordtool.dto;

/**
 * Created by hannahzhang on 16/5/28.
 */
public class ShanbayRespDTO {
    private String msg;
    private Integer status_code;
    private Object data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus_code() {
        return status_code;
    }

    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
