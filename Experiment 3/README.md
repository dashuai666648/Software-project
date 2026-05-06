# Experiment 3 - Android Kotlin/Compose 项目合集

## 目录结构

- `BasicsCodelab/`：Compose 基础交互示例（可展开/收起卡片）
- `LiteRTAIDemo/`：LiteRT AI 推理界面原型（UI 演示，含模型切换与结果展示）
- `MyFirstKotlinApp/`：最小可运行 Kotlin Compose 入门应用

## 子项目详细说明

### 1) BasicsCodelab

路径：`BasicsCodelab/`

功能概述：

- 展示两条 greeting（默认 `World` 和 `Compose`）
- 每条卡片带有 `Show more / Show less` 按钮
- 点击后通过状态切换控制额外底部间距，演示 Compose 响应式 UI

关键实现：

- 入口：`app/src/main/java/com/example/basicscodelab/MainActivity.kt`
- 状态：`val expanded = remember { mutableStateOf(false) }`
- 布局：`Column + Row + Surface + ElevatedButton`

配置要点：

- `compileSdk/targetSdk = 36`
- `minSdk = 24`
- Java/Kotlin 目标：11
- AGP：`8.13.2`，Kotlin：`2.0.21`


### 2) LiteRTAIDemo

路径：`LiteRTAIDemo/`

功能概述：

- AI 相机应用界面原型，包含：
- 顶部标题栏（`LiteRT AI Demo`）
- “Camera Preview” 占位区域
- 推理结果卡片（Model / Result / Confidence / Time）
- 操作按钮（拍照识别、相册导入、切换模型、清空结果）

当前行为：

- “切换模型”可在 `MobileNet` 与 `EfficientNet` 间切换，同时更新结果与耗时文案
- “清空结果”将结果字段重置为 `-`
- 暂未接入真实相机与模型推理，仅为 UI 和交互流程演示

关键实现：

- 入口：`app/src/main/java/com/example/litertaidemo/MainActivity.kt`
- 主界面：`LiteRtDemoScreen()`
- 可复用组件：`ResultLine()`、`ActionButton()`

配置要点：

- `compileSdk/targetSdk = 36`
- `minSdk = 24`
- Java/Kotlin 目标：11
- AGP：`8.13.2`，Kotlin：`2.0.21`

已知问题：

- 按钮中文文案出现乱码（如 `鎷嶇収璇嗗埆`），通常是源码文件编码不一致导致。
- 建议统一为 UTF-8 编码并修正文案。

后续可扩展方向：

- 接入 CameraX 实时预览
- 集成 TensorFlow Lite / Google AI Edge LiteRT 模型推理
- 增加图片输入预处理、Top-K 分类结果和错误处理

---

### 3) MyFirstKotlinApp

路径：`MyFirstKotlinApp/`

功能概述：

- 最小 Compose 应用
- 启动后展示 `Hello Android!`

关键实现：

- 入口：`app/src/main/java/com/example/myfirstkotlinapp/MainActivity.kt`
- 组件：`Greeting(name: String)`

配置要点：

- `compileSdk/targetSdk = 36`
- `minSdk = 24`
- Java/Kotlin 目标：17
- AGP：`8.7.3`，Kotlin：`2.0.21`

说明：

- 该项目依赖写法与另外两个项目略有不同（直接字符串坐标 + Compose BOM）。
- 是很好的“最小工程模板”参考。

