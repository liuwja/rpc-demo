package com.liuwjg.proto;

import lombok.Data;

/**
 * 表示响应结果
 *
 * @author liuwj
 */
@Data
public class Response {
    /**
     * 响应码  0=成功 ，非0=失败
     */
    private int code;
    /**
     * 具体错误信息
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;
}
