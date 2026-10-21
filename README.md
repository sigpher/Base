# 基础库（sigpher/Base）

- 自用基础库，包含网络请求、图像加载、工具类、WebView 模板等
- 上游项目：[Aaronzzx/Base](https://github.com/Aaronzzx/Base)（原发布在已停止服务的 JCenter，坐标 `com.aaron:base`）
- 本 fork 改动：
  - 包名 `com.aaron.base` → `com.sigpher.base`（`BaseActivity`/`BaseFragment`/`ImageLoader`/`StatusBarUtils` 等类名不变）
  - 移除 bintray 发布配置与配置期对 `local.properties` 的强制读取
  - 通过 JitPack 发布 aar，`group = com.github.sigpher.Base`

## Gradle 依赖

```gradle
// 仓库
repositories {
    maven { url 'https://jitpack.io' }
}

// 依赖
implementation 'com.github.sigpher:Base:1.1.5-beta9'
```

> 版本号即 git tag。首次构建由 JitPack 按 tag 编译，之后可从 `https://jitpack.io/com/github/sigpher/Base/base/<tag>/` 下载 aar/pom。

## 模块

- `base/`：基础库（发布产物）
- `app/`：示例 Demo（`com.sigpher.demobase`，applicationId `com.sigpher.demo`），不参与发布

## 构建

```bash
./gradlew :base:assembleRelease   # 编译库
./gradlew :base:install           # 安装到本地 ~/.m2（等同 JitPack 的发布动作）
```

环境要求：JDK 8、Android SDK Platform 28、Gradle 5.1.1（随 wrapper 提供）。
