package com.sigpher.base.download;

import androidx.annotation.NonNull;

import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.DownloadListener3;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class DownloadManager {

//    public static void register(Context context) {
//        Aria.download(context).register();
//    }
//
//    public static void unregister(Context context) {
//        Aria.download(context).unRegister();
//    }
//
//    public static void startDownload(Context context, String urls) {
//        String targetPath = SAVE_PATH + urls.substring(urls.lastIndexOf("/"));
//        Aria.download(context)
//                .load(urls)
//                .setFilePath(targetPath)
//                .start();
//    }
//
//    public static void startDownload(Context context, String urls, String savePath) {
//        Aria.download(context)
//                .load(urls)
//                .setFilePath(savePath)
//                .start();
//    }
//
//    public static void stopAllTasks(Context context) {
//        Aria.download(context).stopAllTask();
//    }

    private static Map<String, DownloadTask> sTaskMap = new HashMap<>();

    public static void cancel(String savePath) {
        DownloadTask task = sTaskMap.get(savePath);
        if (task != null) {
            task.cancel();
            sTaskMap.remove(savePath);
        }
    }

    public static void cancelAll() {
        Set<Map.Entry<String, DownloadTask>> set = sTaskMap.entrySet();
        for (Map.Entry<String, DownloadTask> taskEntry : set) {
            taskEntry.getValue().cancel();
        }
        sTaskMap.clear();
    }

    public static void startDownload(String url, File saveFile, DownloadListener listener) {
        DownloadTask task = new DownloadTask.Builder(url, saveFile)
                .setPassIfAlreadyCompleted(true)
                .build();
        sTaskMap.put(saveFile.getAbsolutePath(), task);
        task.enqueue(new DownloadListener3() {
            @Override
            protected void started(@NonNull DownloadTask task) {
                listener.onStart();
            }

            @Override
            protected void completed(@NonNull DownloadTask task) {
                listener.onComplete();
                File saveFile = task.getFile();
                if (saveFile != null) {
                    sTaskMap.remove(saveFile.getAbsolutePath());
                }
            }

            @Override
            protected void canceled(@NonNull DownloadTask task) {
                listener.onCancel();
                File saveFile = task.getFile();
                if (saveFile != null) {
                    sTaskMap.remove(saveFile.getAbsolutePath());
                }
            }

            @Override
            protected void error(@NonNull DownloadTask task, @NonNull Exception e) {
                listener.onError();
                File saveFile = task.getFile();
                if (saveFile != null) {
                    sTaskMap.remove(saveFile.getAbsolutePath());
                }
            }

            @Override
            protected void warn(@NonNull DownloadTask task) {

            }

            @Override
            public void retry(@NonNull DownloadTask task, @NonNull ResumeFailedCause cause) {

            }

            @Override
            public void connected(@NonNull DownloadTask task, int blockCount, long currentOffset, long totalLength) {

            }

            @Override
            public void progress(@NonNull DownloadTask task, long currentOffset, long totalLength) {
                listener.onProgress(currentOffset, totalLength);
            }
        });
    }

    public interface DownloadListener {
        void onStart();

        void onCancel();

        void onProgress(long currentOffset, long totalLength);

        void onComplete();

        void onError();
    }

    private DownloadManager() {}
}
