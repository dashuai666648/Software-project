Anaconda + VS Code + Android Studio 常用命令速查
一、Anaconda / Conda 常用命令
1. 环境管理
bash
运行
# 查看所有虚拟环境
conda env list

# 创建新环境（指定Python版本）
conda create -n 环境名 python=3.10

# 激活环境
conda activate 环境名

# 退出当前环境
conda deactivate

# 删除指定环境
conda remove -n 环境名 --all
2. 包管理
bash
运行
# 安装包
conda install 包名
pip install 包名

# 卸载包
conda remove 包名
pip uninstall 包名

# 查看已安装包
conda list
pip list

# 更新conda自身
conda update conda
3. Jupyter Notebook
bash
运行
# 启动Jupyter
jupyter notebook

# 指定端口启动
jupyter notebook --port 8889
二、VS Code 常用命令 & 快捷键
1. 终端常用命令
bash
运行
# 运行Python文件
python 文件名.py

# 查看Python版本
python --version

# 安装项目依赖
pip install -r requirements.txt

# 前端项目常用
npm install
npm run dev
npm run build
2. 常用快捷键
Ctrl + Shift + P：打开命令面板
Ctrl + ~：打开 / 关闭终端
Ctrl + /：快速注释 / 取消注释
Shift + Alt + F：代码格式化
Ctrl + F：查找
Ctrl + H：替换
Ctrl + S：保存文件
三、Android Studio 常用命令
1. Gradle 构建命令
bash
运行
# 清理项目
gradlew clean

# 构建Debug包
gradlew assembleDebug

# 构建Release包
gradlew assembleRelease

# 查看项目依赖
gradlew dependencies
2. ADB 常用命令
bash
运行
# 查看已连接设备/模拟器
adb devices

# 安装APK
adb install 路径/app.apk

# 覆盖安装
adb install -r 路径/app.apk

# 卸载应用
adb uninstall 应用包名

# 清空日志
adb logcat -c

# 查看运行日志
adb logcat

# 重启ADB服务
adb kill-server
adb start-server
3. 模拟器操作
bash
运行
# 查看所有模拟器
emulator -list-avds

# 启动指定模拟器
emulator -avd 模拟器名称