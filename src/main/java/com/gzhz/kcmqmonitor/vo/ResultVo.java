package com.gzhz.kcmqmonitor.vo;


import com.gzhz.kcmqmonitor.utils.PageHelper;

import java.io.Serializable;

public class ResultVo {
    private int status;
    private String message;
    private String umeCode;
    private String code;
    private Result result;
    private String detail;
    private PageHelper pageHelper;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUmeCode() {
        return umeCode;
    }

    public void setUmeCode(String umeCode) {
        this.umeCode = umeCode;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public PageHelper getPageHelper() {
        return pageHelper;
    }

    public void setPageHelper(PageHelper pageHelper) {
        this.pageHelper = pageHelper;
    }

    @Override
    public String toString() {
        return "ResultVo{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", umeCode='" + umeCode + '\'' +
                ", code='" + code + '\'' +
                ", result=" + result +
                '}';
    }
}
