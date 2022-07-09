package cn.muratjan.admin.common.excaption;

/**
 * @author MRT
 * @date 2022/6/28 14:39
 */
public class RolePermissionException extends RuntimeException {

    public RolePermissionException(){super();}

    /**
     * 带信息的异常
     * @param msg 信息
     */
    public RolePermissionException(String msg){super((msg));}

    /**
     * 带异常类
     * @param msg 异常信息
     * @param cause 异常对象
     */
    public RolePermissionException(String msg, Throwable cause){
        super(msg,cause);
    }
    public RolePermissionException(Throwable cause) {
        super(cause);
    }

    protected RolePermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
