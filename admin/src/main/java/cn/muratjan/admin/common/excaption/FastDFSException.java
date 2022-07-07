package cn.muratjan.admin.common.excaption;

/**
 * @author MRT
 * @date 2022/6/28 14:39
 */
public class FastDFSException extends RuntimeException {

    public FastDFSException(){super();}

    /**
     * 带信息的异常
     * @param msg 信息
     */
    public FastDFSException(String msg){super((msg));}

    /**
     * 带异常类
     * @param msg 异常信息
     * @param cause 异常对象
     */
    public FastDFSException(String msg, Throwable cause){
        super(msg,cause);
    }
    public FastDFSException(Throwable cause) {
        super(cause);
    }

    protected FastDFSException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
