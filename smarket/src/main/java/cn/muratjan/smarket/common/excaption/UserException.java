package cn.muratjan.smarket.common.excaption;

/**
 * @author MRT
 * @date 2022/6/28 14:39
 */
public class UserException extends ServiceException{
    public UserException() {
        super();
    }

    /**
     * 带信息的异常
     *
     * @param msg 信息
     */
    public UserException(String msg) {
        super(msg);
    }

    /**
     * 带异常类
     *
     * @param msg   异常信息
     * @param cause 异常对象
     */
    public UserException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    protected UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
