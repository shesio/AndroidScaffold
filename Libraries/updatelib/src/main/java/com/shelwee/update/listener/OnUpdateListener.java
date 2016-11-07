package com.shelwee.update.listener;

import com.shelwee.update.pojo.UpdateInfo;


public interface OnUpdateListener {
    /**
     * on start check
     */
    public void onStartCheck();

    /**
     * on finish check
     */
    public void onFinishCheck(UpdateInfo info);

    public void onStartDownload();
    
    public void onDownloading(int progress);
    
    public void onFinshDownload();

}
