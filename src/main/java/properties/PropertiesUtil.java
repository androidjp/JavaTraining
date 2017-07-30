package properties;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 配置文件读写工具类
 * Created by androidjp on 2017/7/23.
 */
public class PropertiesUtil {

    /**
     * 输出JVM所有配置信息
     */
    public static void readJVM(){
        Properties properties = System.getProperties();
        properties.list(System.out);
    }

    /**
     * 根据key 获取 value
     * @param filePath 配置文件路径
     * @param key 键
     * @return 值
     */
    public static String getValueByKey(String filePath,String key){
        Properties properties = new Properties();
        try{
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            properties.load(in);
            String value = properties.getProperty(key);
            System.out.println(key + " = "+ value);
            return value;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取一个.properties 文件的所有key-value
     * @param filePath 配置文件路径
     * @throws IOException 文件IO异常
     */
    public static void getAllProperties(String filePath) throws IOException{
        Properties properties = new Properties();
        InputStream in = new BufferedInputStream(new FileInputStream(filePath));
        properties.load(in);
        Enumeration en = properties.propertyNames();
        while(en.hasMoreElements()){
            String key = (String) en.nextElement();
            String value = properties.getProperty(key);
            System.out.println(key +" = " + value);
        }
    }

    /**
     * 写入key-value 到文件
     * @param filePath 写入的目标配置文件路径
     * @param pKey 写入的key
     * @param pValue 写入的value
     * @throws IOException 文件IO异常
     */
    public static void writeProperties(String filePath, String pKey, String pValue) throws IOException{
        Properties pps  = new Properties();
        File file = new File(filePath);
        if(!(file.exists()))
            file.createNewFile();

        InputStream in  = new FileInputStream(filePath);
        pps.load(in);
        in.close();
        OutputStream out = new FileOutputStream(filePath);
        pps.setProperty(pKey,pValue);
        pps.store(out,"Update "+ pKey+" = "+ pValue);
        out.close();

    }
}
