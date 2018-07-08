package model;

import util.ConstantUtil;

public class CommonModel {
    //code 0:接口请求成功，有数据返回；1：接口请求成功，无数据返回
    private int code;
    private String msg;
    private Object data;

    public int getCode() {
        return code;
    }

    private void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setSuccess(){
        setCode(ConstantUtil.CODE_SUCCESS);
        setMsg(ConstantUtil.MESSAGE_SUCCESS);
    }

    public void setFail(){
        setCode(ConstantUtil.CODE_FAIL);
        setMsg(ConstantUtil.MESSAGE_FAIL);
    }
}
