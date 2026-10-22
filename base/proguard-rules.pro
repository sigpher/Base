# 本库只包含 Activity/Fragment 基类、Glide 图片加载、状态栏工具与通用工具，
# 这里只保留「使用方开启 R8 后仍需保留」的最小规则。

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}

-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}

-dontwarn com.bumptech.glide.**
