package com.xuxin.utils.listener;

import android.view.View;

/**
 * 防爆点击
 */
public abstract class OnSubMultipleClickListener implements View.OnClickListener {

    private final static float DELAY_TIME = 1800;

    private long lastClickTime;

    public OnSubMultipleClickListener() {
        lastClickTime = 0;
    }

    @Override
    public void onClick(View v) {
        if (!canTransmit()) {
            return;
        }
        onSubClick(v);
    }

    abstract void onSubClick(View v);

    private boolean canTransmit() {
        long temTime = System.currentTimeMillis();
        float ttTime = Math.abs(temTime - lastClickTime);

        if (ttTime < DELAY_TIME && ttTime > 0) {
            return false;
        }

        lastClickTime = System.currentTimeMillis();
        return true;
    }
}
