# 基础库（sigpher/Base）

- 自用基础库，只保留 xPDF 实际用到的能力：Activity/Fragment 基类、Glide 图片加载、状态栏工具、常用工具类
- 上游项目：[AaronZheng9603/Base](https://github.com/AaronZheng9603/Base)（原发布在已停止服务的 JCenter，坐标 `com.aaron:base`）
- 本 fork 改动：
  - 包名 `com.aaron.base` → `com.sigpher.base`（`BaseActivity`/`BaseFragment`/`ImageLoader`/`StatusBarUtils` 等类名不变）
  - 移除 bintray 发布配置与配置期对 `local.properties` 的强制读取
  - 通过 JitPack 发布 aar

## 1.2.0 瘦身说明

1.2.0 是一次"删除型"更新，只保留 `base/`、`image/`、`impl/`（`OnClickListenerImpl`、`TextWatcherImpl`、`OnPageChangeListenerImpl`）、`util/`：

- 删除 `http/`、`download/`、`callback/`、`webview/` 四个包，以及 `impl/ObserverImpl`、`impl/SingleObserverImpl`、`util/TimerUtils`
- 删除传递依赖：retrofit、converter-gson、adapter-rxjava2、rxjava/rxandroid、uber autodispose、tencent sonic、okdownload、jaeger statusbaruitl、constraintlayout、multidex、kotlin 插件
- `StatusBarUtils.setColor` 改为内联实现（不再需要 jaeger 的一行依赖）
- 构建升级到 AGP 8.5.2 / Gradle 8.7 / JDK 17 / compileSdk 34，改用 `maven-publish`（JitPack 通过 `publishToMavenLocal` 采集产物）
- 删除示例 `app/` 模块与 webview 相关资源，`proguard-rules.pro` 精简为 Glide + EventBus 的最小规则

## Gradle 依赖

```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.sigpher.Base:base:1.2.0'
}
```

> 多模块坐标：`group = com.github.sigpher.Base`，`artifactId = base`，版本号即 git tag。
> 首次拉取由 JitPack 按 tag 现场构建；也可以在本仓库执行 `:base:publishToMavenLocal` 后，在使用方加上 `mavenLocal()` 复用本地产物。

## 模块

- `base/`：基础库（唯一发布产物）

## 构建

```bash
./gradlew :base:assembleRelease       # 编译 aar
./gradlew :base:publishToMavenLocal   # 安装到 ~/.m2/repository/com/github/sigpher/Base/base/<version>
```

环境要求：JDK 17、Android SDK Platform 34、Gradle 8.7（随 wrapper 提供）。

> 本机若存在多个 JDK，可显式指定：`./gradlew -Dorg.gradle.java.home="<JDK17 路径>" :base:publishToMavenLocal`。
