# CameraXDemo 实验报告（基于 CameraX 基础使用教程）

本项目根据教程《Android CameraX的基础使用》完成，已在当前工程 `CameraXDemo` 中实现以下功能：

- Camera 预览（`PreviewView`）
- 拍照并保存到系统相册（`ImageCapture`）
- 视频录制并保存到系统相册（`VideoCapture` + `Recorder`）
- 图像亮度分析（`ImageAnalysis`）
- 运行时权限申请（相机、麦克风、旧版存储写入）

参考教程：
- [Android CameraX的基础使用（CSDN）](https://blog.csdn.net/llfjfz/article/details/129924593)

## 1. 开发环境

- Android Studio：推荐 Hedgehog 及以上（教程要求 Arctic Fox 2020.3.1 或更高）
- Kotlin：2.0.21（项目已有）
- AGP：8.13.2（项目已有）
- minSdk：24（满足 CameraX 最低要求 21）
- compileSdk / targetSdk：36

## 2. 主要实现内容

### 2.1 Gradle 依赖

文件：`app/build.gradle.kts`

已加入 CameraX 相关依赖：

- `androidx.camera:camera-core:1.5.0`
- `androidx.camera:camera-camera2:1.5.0`
- `androidx.camera:camera-lifecycle:1.5.0`
- `androidx.camera:camera-video:1.5.0`
- `androidx.camera:camera-view:1.5.0`
- `androidx.camera:camera-extensions:1.5.0`

并启用：

- `viewBinding = true`

同时为了与教程结构一致，移除了 Compose 模板依赖与插件，改为 XML + ViewBinding 的 Activity 架构。

### 2.2 权限与硬件声明

文件：`app/src/main/AndroidManifest.xml`

新增：

- `<uses-feature android:name="android.hardware.camera.any" />`
- `CAMERA`
- `RECORD_AUDIO`
- `WRITE_EXTERNAL_STORAGE`（`maxSdkVersion=28`，兼容 Android 9 及以下）

### 2.3 页面布局

文件：`app/src/main/res/layout/activity_main.xml`

布局包含：

- 全屏 `PreviewView` 用于相机画面预览
- `Take Photo` 按钮用于拍照
- `Start/Stop Capture` 按钮用于录像开始/停止
- 中线 `Guideline` 控制双按钮分布

### 2.4 主逻辑代码

文件：`app/src/main/java/com/example/cameraxdemo/MainActivity.kt`

实现要点：

1. 权限处理  
`onCreate()` 中检查权限；未授权则请求，授权后启动相机。

2. 相机启动与用例绑定  
`startCamera()` 中通过 `ProcessCameraProvider` 绑定 4 个用例：
- `Preview`
- `ImageCapture`
- `ImageAnalysis`
- `VideoCapture`

3. 拍照  
`takePhoto()` 使用 `ImageCapture.takePicture()`，输出到 `MediaStore.Images`，高版本保存到 `Pictures/CameraX-Image`。

4. 录像  
`captureVideo()` 使用 `Recorder` 与 `MediaStoreOutputOptions`，输出到 `MediaStore.Video`，高版本保存到 `Movies/CameraX-Video`。

5. 图像分析  
`LuminosityAnalyzer` 分析 Y 平面像素，计算平均亮度并输出日志。

## 3. 如何运行

1. 用 Android Studio 打开本项目。
2. 等待 Gradle 同步完成。
3. 连接真机（推荐）或使用带摄像头能力的模拟器。
4. 运行 `app`。
5. 首次启动时同意相机和麦克风权限。
6. 点击：
- `Take Photo`：拍照并写入相册
- `Start Capture`：开始录制视频
- `Stop Capture`：结束录制并保存

## 4. 结果验证

可通过以下方式验证实验完成：

- 应用启动可实时预览相机画面；
- 点击拍照后弹出成功提示，系统相册出现新图片；
- 点击录像开始/停止后弹出成功提示，系统相册出现新视频；
- `Logcat` 中可看到 `Average luminosity` 亮度日志输出。

## 5. 常见问题与排查

1. 黑屏或无法预览  
- 检查权限是否被拒绝；
- 检查设备是否有可用摄像头；
- 确认未被其他应用占用摄像头。

2. 视频无法录音  
- 检查是否授予麦克风权限；
- 某些设备系统策略会限制录音，请在系统设置中再次确认。

3. Android 9 及以下保存失败  
- 已加入 `WRITE_EXTERNAL_STORAGE`（仅 <= 28）；
- 若仍失败，检查设备存储权限策略或厂商 ROM 限制。

4. 模拟器不可用  
- 部分模拟器摄像头能力有限，建议使用真机测试拍照和录像。

## 6. 与教程的对应关系说明

本实现与教程步骤一一对应，且在以下点做了当前工程适配：

- 教程示例包名为 `com.android.example.cameraxapp`，本项目保持 `com.example.cameraxdemo`；
- 教程使用较早的 CameraX 版本，项目升级到 `1.5.0`，API 用法保持一致；
- 教程从空项目开始，本项目是从 Compose 模板迁移到 XML + ViewBinding，便于对照教程代码结构。

## 7. 实验结论

本实验已在 `CameraXDemo` 工程下完成 CameraX 的基础四大用例（预览、拍照、分析、录像）和权限流程，具备完整的相机应用基础能力，可作为后续扩展（切换前后摄、对焦、滤镜、OCR、目标检测等）的起点。
