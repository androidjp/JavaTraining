package ip;

import java.net.InetAddress;

/**
 * Created by androidjp on 2017/7/30.
 */
public class IPUtil {

    public static String getLocalHostName() {
        String hostName;
        try {
            InetAddress addr = InetAddress.getLocalHost();
            hostName = addr.getHostName();
        }catch (Exception e) {
            hostName="";
        }
        return hostName;
    }

    public static String[] getAllLocalHostIP() {
        String[] ret =null;
        try {
            String hostName = getLocalHostName();
            if(hostName.length()>0) {
                InetAddress[] addrs = InetAddress.getAllByName(hostName);
                if(addrs.length>0) {
                    ret = new String[addrs.length];
                    for(int i =0;i<addrs.length;i++){
                        ret[i]  =addrs[i].getHostAddress();
                    }
                }
            }
        }catch (Exception e) {
            ret = null;
        }
        return ret;
    }

    public static String getIp() {
        String ip = null;
        try {
            String info = InetAddress.getLocalHost().toString();
            int n = info.lastIndexOf("/");
            ip = info.substring(n+1);
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        return ip;
    }
}
