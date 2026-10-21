package com.sigpher.base.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * @author Aaron aaronzheng9603@gmail.com
 */
final class GlideEngine implements ImageEngine<DefaultOption> {

    GlideEngine() {}

    @Override
    public void init(Context context) {

    }

    @Override
    public void load(Context context, DefaultOption option) {
        RequestManager manager = Glide.with(context);
        if (option.asFile()) {
            RequestBuilder<File> builder = setListener(manager.asFile(), option);
            request(context, builder, option);
        } else if (option.asGif()) {
            RequestBuilder<GifDrawable> builder = setListener(manager.asGif(), option);
            request(context, builder, option);
        } else if (option.asBitmap()) {
            int crossFadeDuration = option.getCrossFadeDuration();
            RequestBuilder<Bitmap> builder = setListener(manager.asBitmap(), option);
            if (crossFadeDuration != 0) {
                request(context, builder.transition(BitmapTransitionOptions.withCrossFade(crossFadeDuration)), option);
            } else {
                request(context, builder, option);
            }
        } else {
            int crossFadeDuration = option.getCrossFadeDuration();
            RequestBuilder<Drawable> builder = setListener(manager.asDrawable(), option);
            if (crossFadeDuration != 0) {
                request(context, builder.transition(DrawableTransitionOptions.withCrossFade(crossFadeDuration)), option);
            } else {
                request(context, builder, option);
            }
        }
    }

    @Override
    public File download(Context context, DefaultOption option) throws ExecutionException, InterruptedException {
        if (!option.isDownloadOnly()) throw new IllegalStateException("You must call downloadOnly() that can download.");
        String url = option.getString();
        RequestManager manager = Glide.with(context);
        return manager.downloadOnly()
                .load(url)
                .submit()
                .get();
    }

    @Override
    public Object get(Context context, DefaultOption option) throws ExecutionException, InterruptedException {
        if (!option.isGet()) throw new IllegalStateException("You must call get() that can get resource object.");
        Object obj;
        RequestManager manager = Glide.with(context);
        if (option.asBitmap()) {
            obj = get(manager.asBitmap(), option);
        } else if (option.asFile()) {
            obj = get(manager.asFile(), option);
        } else if (option.asGif()) {
            obj = get(manager.asGif(), option);
        } else {
            obj = get(manager.asDrawable(), option);
        }
        return obj;
    }

    private <T> RequestBuilder<T> setListener(RequestBuilder<T> builder, ImageOption option) {
        LoadListener listener = option.getListener();
        if (listener == null) {
            return builder;
        }
        return builder.listener(new RequestListener<T>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<T> target, boolean isFirstResource) {
                return listener.onFailure(e);
            }

            @Override
            public boolean onResourceReady(T resource, Object model, Target<T> target, DataSource dataSource, boolean isFirstResource) {
                return listener.onSuccess(resource);
            }
        });
    }

    @SuppressWarnings("unchecked")
    private <T> void request(Context context, RequestBuilder<T> builder, DefaultOption option) {
        String string           = option.getString();
        Bitmap bitmap           = option.getBitmap();
        File file               = option.getFile();
        Drawable drawable       = option.getDrawable();
        Uri uri                 = option.getUri();
        int drawableId          = option.getDrawableId();
        int placeholderId       = option.getPlaceholderId();
        Drawable placeholder    = option.getPlaceholder();
        int errorId             = option.getErrorId();
        Drawable error          = option.getError();
        float sizeMultiplier    = option.getSizeMultiplier();
        String thumbnailUrl     = option.getThumbnailUrl();
        int resizeX             = option.getResizeX();
        int resizeY             = option.getResizeY();
        boolean skipMemoryCache = option.isSkipMemoryCache();
        boolean skipDiskCache   = option.isSkipDiskCache();
        Priority priority       = option.getPriority();
        ScaleType scaleType     = option.getScaleType();

        if (string != null) {
            builder = builder.load(string);
        } else if (bitmap != null) {
            builder = builder.load(bitmap);
        } else if (file != null) {
            builder = builder.load(file);
        } else if (drawable != null) {
            builder = builder.load(drawable);
        } else if (uri != null) {
            builder = builder.load(uri);
        } else if (drawableId != 0) {
            builder = builder.load(drawableId);
        } else {
            throw new IllegalArgumentException("Parameter cannot be null.");
        }

        if (placeholderId != 0) {
            builder = builder.placeholder(placeholderId);
        } else if (placeholder != null) {
            builder = builder.placeholder(placeholder);
        }
        if (errorId != 0) {
            builder = builder.error(errorId);
        } else if (error != null) {
            builder = builder.error(error);
        }

        if (!StringUtils.isEmpty(thumbnailUrl)) {
            builder = builder.thumbnail((RequestBuilder<T>) Glide.with(context).load(thumbnailUrl));
        } else if (sizeMultiplier != 0.0F) {
            builder = builder.thumbnail(sizeMultiplier);
        }

        if (resizeX != 0 || resizeY != 0) {
            builder = builder.override(resizeX, resizeY);
        }

        builder = builder.skipMemoryCache(skipMemoryCache);
        builder = skipDiskCache ? builder.diskCacheStrategy(DiskCacheStrategy.NONE) : builder;

        if (priority != null) {
            switch (priority) {
                case LOW:
                    builder = builder.priority(com.bumptech.glide.Priority.LOW);
                    break;
                case NORMAL:
                    builder = builder.priority(com.bumptech.glide.Priority.NORMAL);
                    break;
                case HIGH:
                    builder = builder.priority(com.bumptech.glide.Priority.HIGH);
                    break;
            }
        }

        if (scaleType != null) {
            switch (scaleType) {
                case FIT_CENTER:    builder = builder.fitCenter();    break;
                case CENTER_INSIDE: builder = builder.centerInside(); break;
                case CENTER_CROP:   builder = builder.centerCrop();   break;
                case CIRCLE_CROP:   builder = builder.circleCrop();   break;
            }
        }
        builder.into(option.getTarget());
    }

    private <T> Object get(RequestBuilder<T> builder, DefaultOption option) throws ExecutionException, InterruptedException {
        String string           = option.getString();
        Bitmap bitmap           = option.getBitmap();
        File file               = option.getFile();
        Drawable drawable       = option.getDrawable();
        Uri uri                 = option.getUri();
        int drawableId          = option.getDrawableId();
        if (string != null) {
            builder = builder.load(string);
        } else if (bitmap != null) {
            builder = builder.load(bitmap);
        } else if (file != null) {
            builder = builder.load(file);
        } else if (drawable != null) {
            builder = builder.load(drawable);
        } else if (uri != null) {
            builder = builder.load(uri);
        } else if (drawableId != 0) {
            builder = builder.load(drawableId);
        } else {
            throw new IllegalArgumentException("Parameter cannot be null.");
        }
        return builder.submit().get();
    }
}
