package top.mnsx.exception;

/**
 * @Author Mnsx_x xx1527030652@gmail.com
 */
public class XXTException extends RuntimeException {
    private String errMessage;

    public XXTException() {
        super();
    }

    public XXTException(String message) {
        super(message);
        this.errMessage = message;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public static void cast(String errMessage) {
        throw new XXTException(errMessage);
    }

    public static void cast(CommonError commonError) {
        throw new XXTException(commonError.getErrMessage());
    }
}
