package com.sigpher.base.image;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.io.File;

/**
 * @author Aaron aaronzheng9603@gmail.com
 */
public final class DefaultOption extends ImageOption {

    protected DefaultOption(Builder builder) {
        super(builder);
    }

    public static final class Builder extends ImageOption.Builder<Builder, DefaultOption> {
        public Builder(String string) {
            super(string);
        }

        public Builder(Bitmap bitmap) {
            super(bitmap);
        }

        public Builder(File file) {
            super(file);
        }

        public Builder(Drawable drawable) {
            super(drawable);
        }

        public Builder(Uri uri) {
            super(uri);
        }

        public Builder(int drawableId) {
            super(drawableId);
        }

        @Override
        protected Builder actualBuilder() {
            return this;
        }

        @Override
        protected DefaultOption actualOption() {
            return new DefaultOption(this);
        }
    }
}
