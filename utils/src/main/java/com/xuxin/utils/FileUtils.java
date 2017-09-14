package com.xuxin.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DecimalFormat;

/**
 * 文件相关的工具类
 */
public class FileUtils {

    private FileUtils() {
        throw new UnsupportedOperationException("You do not need to instantiate!");
    }

    public static final int SIZETYPE_B = 1;//获取文件大小单位为B的double值
    public static final int SIZETYPE_KB = 2;//获取文件大小单位为KB的double值
    public static final int SIZETYPE_MB = 3;//获取文件大小单位为MB的double值
    public static final int SIZETYPE_GB = 4;//获取文件大小单位为GB的double值

    /**
     * 获取sdCard的路径，如果不存在就返回空
     *
     * @return SdCard 绝对路径 或 null (不存在sdcard)
     */
    public static String getSdCardPath() {
        String path = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return path;
    }

    /**
     * 判断路径下的文件是否存在
     *
     * @param address  父目录
     * @param fileName 文件名字
     * @return true 表示存在，false 表示不存在
     */
    public static boolean isFileExists(String address, String fileName) {
        File addressFile = new File(address);
        if (!addressFile.exists()) {
            return false;
        }
        File file = new File(address + "/" + fileName);
        return file.exists();
    }

    /**
     * 判断路径下的文件是否存在
     *
     * @return true 表示存在，false 表示不存在
     */
    public static boolean isFileExists(String filePath) {
        File addressFile = new File(filePath);
        return addressFile.exists();
    }

    /**
     * 创建文件目录
     *
     * @param address  父目录
     * @param fileName 文件名
     * @return 返回一个File 对象
     */
    public static File createFile(String address, String fileName) {
        File file = new File(address);
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(address + "/" + fileName);
    }

    /**
     * 复制文件 old->new 并选择是否删除old文件
     *
     * @param oldPath       旧文件
     * @param newPath       新文件
     * @param deleteOldFile 是否删除旧文件
     * @throws Exception 文件读写出错，不会删除源文件
     */
    public static void copyFile(String oldPath, String newPath, boolean deleteOldFile) throws Exception {
        int olderIndex = oldPath.lastIndexOf("/");
        File olderPath = new File(oldPath.substring(0, olderIndex));
        if (!olderPath.exists()) {
            olderPath.mkdirs();
        }

        int newIndex = newPath.lastIndexOf("/");
        File newerPath = new File(newPath.substring(0, newIndex));
        if (!newerPath.exists()) {
            newerPath.mkdirs();
        }

        copyFile(new File(oldPath), new File(newPath), deleteOldFile);
    }

    /**
     * 复制文件 old->new 并选择是否删除old文件
     *
     * @param oldFile       旧文件
     * @param newFile       新文件
     * @param deleteOldFile 是否删除旧文件
     * @throws IOException 文件读写出错，不会删除源文件
     */
    public static synchronized void copyFile(File oldFile, File newFile, boolean deleteOldFile) throws IOException {
        int byteSum = 0;
        int byteRead;
        if (oldFile.exists()) { //文件存在时
            FileInputStream inputStream = new FileInputStream(oldFile); //读入原文件
            FileOutputStream outputStream = new FileOutputStream(newFile);
            byte[] buffer = new byte[1024];
            while ((byteRead = inputStream.read(buffer)) != -1) {
                byteSum += byteRead; //字节数 文件大小
                System.out.println(byteSum);
                outputStream.write(buffer, 0, byteRead);
            }
            inputStream.close();
            outputStream.close();
            if (deleteOldFile) {
                oldFile.delete();
            }
        }
    }

    /**
     * 拷贝
     *
     * @param in  in
     * @param out out
     * @throws IOException 文件读写出错，不会删除源文件
     */
    public static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    /**
     * 从context文件中去读内容
     *
     * @param context  上下文
     * @param fileName 文件名字
     * @return String
     */
    public static String getStringFromAssetsDir(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 获取一个打开ppt的应用的intent
     *
     * @param param 文件目录
     * @return 跳转意向
     */
    public static Intent getPptFileIntent(String param) {
        return getIntentByType(param, "application/vnd.ms-powerpoint");
    }

    /**
     * android获取一个用于打开Excel文件的intent
     *
     * @param param 文件目录
     * @return 跳转意向
     */
    public static Intent getExcelFileIntent(String param) {
        return getIntentByType(param, "application/vnd.ms-excel");
    }

    /**
     * android获取一个用于打开Word文件的intent
     *
     * @param param 文件目录
     * @return 跳转意向
     */
    public static Intent getWordFileIntent(String param) {
        return getIntentByType(param, "application/msword");
    }

    /**
     * android获取一个用于打开文本文件的intent
     *
     * @param param 文件目录
     * @return 跳转意向
     */
    public static Intent getTextFileIntent(String param) {
        return getIntentByType(param, "text/plain");
    }

    /**
     * android获取一个用于打开PDF文件的intent
     *
     * @param param 文件目录
     * @return 跳转意向
     */
    public static Intent getPdfFileIntent(String param) {
        return getIntentByType(param, "application/pdf");
    }

    private static Intent getIntentByType(String param, String type) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, type);
        return intent;
    }

    /**
     * 删除文件或文件夹
     *
     * @param file 文件对象
     */
    public static void delete(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }

        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }
            for (int i = 0; i < childFiles.length; i++) {
                delete(childFiles[i]);
            }
            file.delete();
        }
    }

    public static boolean deleteFiles(File file) {
        if (file.isFile()) {
            return file.delete();
        }

        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                return true;
            }
            for (int i = 0; i < childFiles.length; i++) {
                delete(childFiles[i]);
            }
            return true;
        }
        return true;
    }

    /**
     * 获取文件指定文件的指定单位的大小
     *
     * @param filePath 文件路径
     * @param sizeType 获取大小的类型1为B、2为KB、3为MB、4为GB
     * @return double值的大小
     */
    public static double getFileOrFilesSize(String filePath, int sizeType) {
        File file = new File(filePath);
        long blockSize = 0;
        try {
            if (file.isDirectory()) {
                blockSize = getFileSizes(file);
            } else {
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FormatFileSize(blockSize, sizeType);
    }

    /**
     * 调用此方法自动计算指定文件或指定文件夹的大小
     *
     * @param filePath 文件路径
     * @return 计算好的带B、KB、MB、GB的字符串
     */
    public static String getAutoFileOrFilesSize(String filePath) {
        File file = new File(filePath);
        long blockSize = 0;
        try {
            if (file.isDirectory()) {
                blockSize = getFileSizes(file);
            } else {
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FormatFileSize(blockSize);
    }

    /**
     * 获取指定文件大小
     *
     * @param file 文件
     * @return 文件大小
     * @throws Exception 异常
     */
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
        }
        return size;
    }

    /**
     * 获取多个文件夹大小
     *
     * @param f 多个文件
     * @return 多个文件夹大小
     * @throws Exception 异常
     */
    public static long getFileSizes(File f) throws Exception {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSizes(flist[i]);
            } else {
                size = size + getFileSize(flist[i]);
            }
        }
        return size;
    }

    /**
     * 转换文件大小
     *
     * @param fileS 文件大小
     * @return 转化后的文件大小
     */
    public static String FormatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString;
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 转换文件大小,指定转换的类型
     *
     * @param fileS    文件大小
     * @param sizeType 指定类型
     * @return 转化后指定类型的文件大小
     */
    public static double FormatFileSize(long fileS, int sizeType) {
        DecimalFormat df = new DecimalFormat("#.00");
        double fileSizeLong = 0;
        switch (sizeType) {
            case SIZETYPE_B:
                fileSizeLong = Double.valueOf(df.format((double) fileS));
                break;
            case SIZETYPE_KB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1024));
                break;
            case SIZETYPE_MB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1048576));
                break;
            case SIZETYPE_GB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1073741824));
                break;
            default:
                break;
        }
        return fileSizeLong;
    }

    public static void copyAssets(Context context, String src, String dist) {
        AssetManager assetManager = context.getAssets();
        String[] files = null;
        try {
            files = assetManager.list(src);
        } catch (Exception e) {
            XLog.e("tag", "Failed to get asset file list.", e);
        }
        assert files != null;
        for (String filename : files) {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open(src + File.separator + filename);
                File outFile = new File(dist + File.separator + src, filename);
                out = new FileOutputStream(outFile);
                copyFile(in, out);
            } catch (Exception e) {
                XLog.e("tag", "Failed to copy asset file: " + filename, e);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                        in = null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (out != null) {
                    try {
                        out.flush();
                        out.close();
                        out = null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
