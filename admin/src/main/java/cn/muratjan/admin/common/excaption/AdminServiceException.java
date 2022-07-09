package cn.muratjan.admin.common.excaption;

/**
 * @author MRT
 * @date 2022/6/28 14:39
 */
public class AdminServiceException extends RuntimeException {

    public AdminServiceException(){super();}

    /**
     * 带信息的异常
     * @param msg 信息
     */
    public AdminServiceException(String msg){super((msg));}

    /**
     * 带异常类
     * @param msg 异常信息
     * @param cause 异常对象
     */
    public AdminServiceException(String msg, Throwable cause){
        super(msg,cause);
    }
    public AdminServiceException(Throwable cause) {
        super(cause);
    }

    protected AdminServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
