package com.xuxin.utils;

import android.os.Build;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * 设备相关的工具类
 */
public class DeviceUtils {

    private DeviceUtils() {
        throw new UnsupportedOperationException("You do not need to instantiate!");
    }

    /**
     * 获取设备MAC地址
     * <p>需添加权限 android.permission.ACCESS_WIFI_STATE</p>
     *
     * @return MAC地址
     */
    public static String getMacAddress() {
        String macAddress = null;
        LineNumberReader reader = null;
        try {
            Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            reader = new LineNumberReader(ir);
            macAddress = reader.readLine().replace(":", "");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return macAddress == null ? "" : macAddress;
    }

    /**
     * 获取设备厂商，如Xiaomi
     *
     * @return 设备厂商
     */
    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取设备型号，如MI2SC
     *
     * @return 设备型号
     */
    public static String getModel() {
        String model = Build.MODEL;
        if (model != null) {
            model = model.trim().replaceAll("\\s*", "");
        } else {
            model = "";
        }
        return model;
    }

    /**
     * 检测当前设备是否是特定的设备
     *
     * @param devices 设备（不定参数）
     * @return 是否是特定的设备
     */
    public static boolean isDevice(String... devices) {
        String model = DeviceUtils.getModel();
        if (devices != null && model != null) {
            for (String device : devices) {
                if (model.contains(device)) {
                    return true;
                }
            }
        }
        return false;
    }
}
