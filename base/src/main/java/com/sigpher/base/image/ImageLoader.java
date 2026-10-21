package com.sigpher.base.image;

import android.content.Context;

import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * @author Aaron aaronzheng9603@gmail.com
 */
public final class ImageLoader {

    private static ImageEngine sImageEngine = new GlideEngine();

    public static void setImageEngine(ImageEngine engine) {
        sImageEngine = engine;
    }

    public static ImageEngine getImageEngine() {
        return sImageEngine;
    }

    public static void init(Context context) {
        sImageEngine.init(context);
    }

    @SuppressWarnings("unchecked")
    public static <T extends ImageOption> void load(Context context, T options) {
        sImageEngine.load(context, options);
    }

    @SuppressWarnings("unchecked")
    public static <T extends ImageOption> File download(Context context, T options) throws ExecutionException, InterruptedException {
        return sImageEngine.download(context, options);
    }

    @SuppressWarnings("unchecked")
    public static <T extends ImageOption> Object get(Context context, T options) throws ExecutionException, InterruptedException {
        return sImageEngine.get(context, options);
    }

    private ImageLoader() {
    }
}
