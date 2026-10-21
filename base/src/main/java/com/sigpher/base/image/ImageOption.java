package com.sigpher.base.image;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;

import java.io.File;

/**
 * @author Aaron aaronzheng9603@gmail.com
 */
public abstract class ImageOption {

    protected String mString;
    protected Bitmap mBitmap;
    protected File mFile;
    protected Drawable mDrawable;
    protected Uri mUri;
    protected int mDrawableId;
    protected boolean mDownloadOnly;
    protected boolean mGet;

    protected boolean mAsDrawable, mAsBitmap, mAsFile, mAsGif;

    protected int mPlaceholderId;
    protected Drawable mPlaceholder;
    protected int mErrorId;
    protected Drawable mError;

    protected float mSizeMultiplier;
    protected String mThumbnailUrl;

    protected int mResizeX;
    protected int mResizeY;

    protected int mCrossFadeDuration;

    protected boolean mSkipMemoryCache, mSkipDiskCache;

    protected Priority mPriority;
    protected ScaleType mScaleType;

    protected LoadListener mListener;
    protected ImageView mTarget;

    protected ImageOption(Builder builder) {
        this.mString = builder.string;
        this.mBitmap = builder.bitmap;
        this.mFile = builder.file;
        this.mDrawable = builder.drawable;
        this.mUri = builder.uri;
        this.mDrawableId = builder.drawableId;
        this.mDownloadOnly = builder.downloadOnly;
        this.mGet = builder.get;

        this.mAsDrawable = builder.asDrawable;
        this.mAsBitmap = builder.asBitmap;
        this.mAsFile = builder.asFile;
        this.mAsGif = builder.asGif;

        this.mPlaceholderId = builder.placeholderId;
        this.mPlaceholder = builder.placeholder;
        this.mErrorId = builder.errorId;
        this.mError = builder.error;

        this.mCrossFadeDuration = builder.crossFadeDuration;

        this.mSizeMultiplier = builder.sizeMultiplier;
        this.mThumbnailUrl = builder.thumbnailUrl;
        this.mResizeX = builder.resizeX;
        this.mResizeY = builder.resizeY;

        this.mSkipMemoryCache = builder.skipMemoryCache;
        this.mSkipDiskCache = builder.skipDiskCache;

        this.mPriority = builder.priority;
        this.mScaleType = builder.scaleType;

        this.mListener = builder.listener;

        this.mTarget = builder.target;
    }

    public String getString() {
        return mString;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public File getFile() {
        return mFile;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    public Uri getUri() {
        return mUri;
    }

    public int getDrawableId() {
        return mDrawableId;
    }

    public boolean isDownloadOnly() {
        return mDownloadOnly;
    }

    public boolean isGet() {
        return mGet;
    }

    public boolean asDrawable() {
        return mAsDrawable;
    }

    public boolean asBitmap() {
        return mAsBitmap;
    }

    public boolean asFile() {
        return mAsFile;
    }

    public boolean asGif() {
        return mAsGif;
    }

    public int getPlaceholderId() {
        return mPlaceholderId;
    }

    public Drawable getPlaceholder() {
        return mPlaceholder;
    }

    public int getErrorId() {
        return mErrorId;
    }

    public Drawable getError() {
        return mError;
    }

    public int getCrossFadeDuration() {
        return mCrossFadeDuration;
    }

    public float getSizeMultiplier() {
        return mSizeMultiplier;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    public int getResizeX() {
        return mResizeX;
    }

    public int getResizeY() {
        return mResizeY;
    }

    public boolean isSkipMemoryCache() {
        return mSkipMemoryCache;
    }

    public boolean isSkipDiskCache() {
        return mSkipDiskCache;
    }

    public Priority getPriority() {
        return mPriority;
    }

    public ScaleType getScaleType() {
        return mScaleType;
    }

    public LoadListener getListener() {
        return mListener;
    }

    public ImageView getTarget() {
        return mTarget;
    }

    public static abstract class Builder<Builder extends ImageOption.Builder, Option extends ImageOption> {
        protected String string;
        protected Bitmap bitmap;
        protected File file;
        protected Drawable drawable;
        protected Uri uri;
        protected int drawableId;
        protected boolean downloadOnly;
        protected boolean get;

        protected boolean asDrawable, asBitmap, asFile, asGif;

        protected int placeholderId;
        protected Drawable placeholder;
        protected int errorId;
        protected Drawable error;
        protected int crossFadeDuration;

        protected float sizeMultiplier;
        protected String thumbnailUrl;

        protected int resizeX;
        protected int resizeY;

        protected boolean skipMemoryCache, skipDiskCache;

        protected Priority priority;
        protected ScaleType scaleType;

        protected LoadListener listener;

        protected ImageView target;

        public Builder(String string) {
            this.string = string;
        }

        public Builder(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        public Builder(File file) {
            this.file = file;
        }

        public Builder(Drawable drawable) {
            this.drawable = drawable;
        }

        public Builder(Uri uri) {
            this.uri = uri;
        }

        public Builder(@DrawableRes int drawableId) {
            this.drawableId = drawableId;
        }

        public Option downloadOnly() {
            this.downloadOnly = true;
            return actualOption();
        }

        public Option get() {
            this.get = true;
            return actualOption();
        }

        public Builder asDrawable() {
            this.asDrawable = true;
            return actualBuilder();
        }

        public Builder asBitmap() {
            this.asBitmap = true;
            return actualBuilder();
        }

        public Builder asFile() {
            this.asFile = true;
            return actualBuilder();
        }

        public Builder asGif() {
            this.asGif = true;
            return actualBuilder();
        }

        public Builder placeholder(@DrawableRes int drawableId) {
            this.placeholderId = drawableId;
            return actualBuilder();
        }

        public Builder placeholder(Drawable drawable) {
            this.placeholder = drawable;
            return actualBuilder();
        }

        public Builder error(@DrawableRes int drawableId) {
            this.errorId = drawableId;
            return actualBuilder();
        }

        public Builder error(Drawable drawable) {
            this.error = drawable;
            return actualBuilder();
        }

        public Builder crossFade(int duration) {
            this.crossFadeDuration = duration;
            return actualBuilder();
        }

        public Builder thumbnail(@FloatRange(from = 0.0, to = 1.0) float sizeMultiplier) {
            this.sizeMultiplier = sizeMultiplier;
            return actualBuilder();
        }

        public Builder thumbnail(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
            return actualBuilder();
        }

        public Builder override(int size) {
            this.resizeX = size;
            this.resizeY = size;
            return actualBuilder();
        }

        public Builder override(int resizeX, int resizeY) {
            this.resizeX = resizeX;
            this.resizeY = resizeY;
            return actualBuilder();
        }

        public Builder skipMemoryCache(boolean skip) {
            this.skipMemoryCache = skip;
            return actualBuilder();
        }

        public Builder skipDiskCache(boolean skip) {
            this.skipDiskCache = skip;
            return actualBuilder();
        }

        public Builder priority(Priority priority) {
            this.priority = priority;
            return actualBuilder();
        }

        public Builder scaleType(ScaleType scaleType) {
            this.scaleType = scaleType;
            return actualBuilder();
        }

        public Builder addListener(LoadListener listener) {
            this.listener = listener;
            return actualBuilder();
        }

        public Option into(ImageView target) {
            this.target = target;
            return actualOption();
        }

        protected abstract Builder actualBuilder();

        protected abstract Option actualOption();
    }
}
