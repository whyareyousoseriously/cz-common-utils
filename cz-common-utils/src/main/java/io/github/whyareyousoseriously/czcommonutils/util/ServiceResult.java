package io.github.whyareyousoseriously.czcommonutils.util;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 *service层返回对象列表封装
 * @author chenzhen
 * @param <T>
 */
public class ServiceResult<T>  implements Serializable{

    private Integer code;

    private String message;

    private T result;

    private ServiceResult() {
    }

    public static <T> ServiceResult<T> success(T result) {
        ServiceResult<T> item = new ServiceResult<T>();
        item.result = result;
        item.code = HttpStatus.OK.value();
        item.message = "success";
        return item;
    }

    public static <T> ServiceResult<T> success(int successCode,String message,T result){
        ServiceResult<T> item = new ServiceResult<T>();
        item.result = result;
        item.code = successCode;
        item.message = message;
        return item;
    }

    public static <T> ServiceResult<T> failure(int errorCode, String errorMessage) {
        ServiceResult<T> item = new ServiceResult<T>();
        item.code = errorCode;
        item.message = errorMessage;
        return item;
    }

    public static <T> ServiceResult<T> failure(int errorCode) {
        ServiceResult<T> item = new ServiceResult<T>();
        item.code = errorCode;
        item.message = "failure";
        return item;
    }

    public boolean hasResult() {
        return result != null;
    }

    public T getResult() {
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
