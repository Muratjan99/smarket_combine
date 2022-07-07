package cn.muratjan.admin.common.excaption;

/**
 * @author MRT
 * @date 2022/6/28 14:39
 */
public class AddressException extends RuntimeException {

    public AddressException(){super();}

    /**
     * 带信息的异常
     * @param msg 信息
     */
    public AddressException(String msg){super((msg));}

    /**
     * 带异常类
     * @param msg 异常信息
     * @param cause 异常对象
     */
    public AddressException(String msg, Throwable cause){
        super(msg,cause);
    }
    public AddressException(Throwable cause) {
        super(cause);
    }

    protected AddressException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
