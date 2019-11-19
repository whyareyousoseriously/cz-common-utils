package io.github.whyareyousoseriously.czcommonutils.exception;

/**
 * 服务层自定义异常
 * @author chenzhen
 * Created by chenzhen on 2019/9/4.
 */
public class ServiceResultException extends Exception{

    public ServiceResultException(String message) {
        super(message);
    }
}
