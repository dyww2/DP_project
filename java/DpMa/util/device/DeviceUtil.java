package DpMa.util.device;


public class DeviceUtil {
    /**
     * 根据头信息判断用户是手机还是电脑
     *
     * @param userAgent
     * @return
     */
    public static boolean isMobile(String userAgent) {
        if (userAgent.contains("Android") || userAgent.contains("iPhone")) {
            return true;
        }
        return false;
    }
}
