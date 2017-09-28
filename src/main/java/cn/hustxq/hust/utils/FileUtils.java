package cn.hustxq.hust.utils;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @Author hustxq.
 * @Date 2017/9/28 10:42
 */
public class FileUtils {
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+File.separator+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
