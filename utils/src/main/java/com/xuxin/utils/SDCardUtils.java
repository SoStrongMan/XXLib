package com.xuxin.utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * SD卡相关的工具类
 */
public class SDCardUtils {

    private SDCardUtils() {
        throw new UnsupportedOperationException("You do not need to instantiate!");
    }

    /**
     * 获取设备SD卡是否可用
     *
     * @return true : 可用<br>false : 不可用
     */
    public static boolean isSDCardEnable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取设备SD卡路径
     * <p>一般是/storage/emulated/0/</p>
     *
     * @return SD卡路径
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    /**
     * 获取SD卡的剩余容量 单位byte
     *
     * @return 剩余容量
     */
    public static long getSDCardAllSize() {
        if (isSDCardEnable()) {
            StatFs stat = new StatFs(getSDCardPath());
            // 获取空闲的数据块的数量
            long availableBlocks = (long) stat.getAvailableBlocks() - 4;
            // 获取单个数据块的大小（byte）
            long freeBlocks = stat.getAvailableBlocks();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }


    /**
     * 获取系统存储路径
     *
     * @return 存储路径
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }

    /**
     * 检查文件是否存在于SD卡中
     *
     * @param filePath 文件路径
     * @param fileName 文件名称
     * @return
     */
    public static boolean isFileExistsInSDCard(String filePath, String fileName) {
        boolean flag = false;
        if (isSDCardEnable()) {
            File file = new File(filePath, fileName);
            if (file.exists()) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 将文件写入到SD卡中并返回成功与否
     *
     * @param filePath 文件路径
     * @param filename 文件名称
     * @param content  文件内容
     * @return 是，否
     * @throws Exception 异常
     */
    public static boolean saveFileToSDCard(String filePath, String filename, String content)
            throws Exception {
        boolean flag = false;
        if (isSDCardEnable()) {
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(filePath, filename);
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(content.getBytes());
            outStream.close();
            flag = true;
        }
        return flag;
    }

    /**
     * 从SD卡中读取文件
     *
     * @param fileName String PATH =
     *                 Environment.getExternalStorageDirectory().getAbsolutePath() +
     *                 "/dirName";
     * @return byte数组
     */
    public static byte[] readFileFromSDCard(String filePath, String fileName) {
        byte[] buffer = null;
        FileInputStream fin = null;
        try {
            if (isSDCardEnable()) {
                String filePaht = filePath + "/" + fileName;
                fin = new FileInputStream(filePaht);
                int length = fin.available();
                buffer = new byte[length];
                fin.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fin != null) fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer;
    }

    /**
     * 删除文件
     *
     * @param filePath filePath =
     *                 android.os.Environment.getExternalStorageDirectory().getPath()
     * @param fileName 文件名称
     * @return 是否删除
     */
    public static boolean deleteSDFile(String filePath, String fileName) {
        File file = new File(filePath + "/" + fileName);
        return !(!file.exists() || file.isDirectory()) && file.delete();
    }
}
