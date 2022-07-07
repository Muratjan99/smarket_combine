package cn.muratjan.smarket.common.excaption;

/**
 * @author MRT
 * @date 2022/6/28 14:39
 */
public class OrderException extends RuntimeException {

    public OrderException(){super();}

    /**
     * 带信息的异常
     * @param msg 信息
     */
    public OrderException(String msg){super((msg));}

    /**
     * 带异常类
     * @param msg 异常信息
     * @param cause 异常对象
     */
    public OrderException(String msg, Throwable cause){
        super(msg,cause);
    }
    public OrderException(Throwable cause) {
        super(cause);
    }

    protected OrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
