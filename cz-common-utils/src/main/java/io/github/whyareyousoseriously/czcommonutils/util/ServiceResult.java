package io.github.whyareyousoseriously.czcommonutils.util;

import com.alibaba.fastjson.JSONObject;
import io.github.whyareyousoseriously.czcommonutils.exception.ServiceResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

/**
 * service层返回对象列表封装
 *
 * @param <T>
 * @author chenzhen
 */
public class ServiceResult<T> implements Serializable {

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

    public static <T> ServiceResult<T> success(int successCode, String message, T result) {
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

    public static <T> ServiceResult<T> failure(int errorCode, String errorMessage, T result) {
        ServiceResult<T> item = new ServiceResult<T>();
        item.code = errorCode;
        item.message = errorMessage;
        item.result = result;
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

    public static <T> void checkServiceResult(ServiceResult<T> serviceResult) throws ServiceResultException {
        if (serviceResult == null) {
            throw new ServiceResultException("待检测的serviceResult内容为空");
        } else {
            if (serviceResult.getCode() != HttpStatus.OK.value()) {
                throw new ServiceResultException(serviceResult.getMessage());
            }
        }
    }

    public static <T> T dealwithResponseEntity(ResponseEntity<ServiceResult<T>> responseEntity) throws ServiceResultException {
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            ServiceResult<T> body = responseEntity.getBody();
            ServiceResult.checkServiceResult(body);
            if (body.getCode() == HttpStatus.BAD_REQUEST.value()) {
                throw new ServiceResultException(body.getResult().toString());
            }
            if (body.getResult()==null){
                throw new ServiceResultException("请求下层返回结果为null");
            }
            return body.getResult();
        } else {
            throw new ServiceResultException(responseEntity.getStatusCode().getReasonPhrase());
        }
    }
}
