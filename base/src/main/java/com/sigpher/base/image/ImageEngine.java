package com.sigpher.base.image;

import android.content.Context;

import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * @author Aaron aaronzheng9603@gmail.com
 */
public interface ImageEngine<T extends ImageOption> {

    void init(Context context);

    void load(Context context, T option);

    File download(Context context, T option) throws ExecutionException, InterruptedException;

    Object get(Context context, T option) throws ExecutionException, InterruptedException;
}
