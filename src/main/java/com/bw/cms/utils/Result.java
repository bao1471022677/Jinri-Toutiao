package com.bw.cms.utils;
/**
 * 
 * @ClassName: Result 
 * @Description: ͳһ���ؽ��
 * @author: charles
 * @date: 2019��11��20�� ����9:43:28 
 * @param <T>
 */
public class Result<T> {

    /** ��Ϣ��. */
    private Integer code;

    /** ��ʾ��Ϣ. */
    private String msg;

    /** ���������. */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
