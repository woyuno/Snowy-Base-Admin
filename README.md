# Snowy Vibecoding Base

## 项目定位

本项目是一套面向中国区后台场景的 Java 单体基座，目标是：

- 结构清晰
- 默认主干轻量
- 更适合 AI / agent 持续协作开发
- 在保留系统管理能力的前提下，降低理解和扩展成本


## 技术栈

### 后端

- Java 17
- Spring Boot 3
- MyBatis-Plus
- Sa-Token
- Redis
- MySQL 5.7 / 8.0

### 前端

- Vue 3
- Ant Design Vue 4
- Vite

## 模块结构

```text
snowy
├─ snowy-common
├─ snowy-auth-contract
├─ snowy-system-contract
├─ snowy-dev-contract
├─ snowy-auth
├─ snowy-system
├─ snowy-dev
├─ snowy-boot
└─ snowy-admin-web
```

### 后端模块职责

- `snowy-common`：公共基础设施、配置、异常、缓存、通用工具
- `snowy-auth-contract`：认证域对外契约
- `snowy-system-contract`：系统域对外契约
- `snowy-dev-contract`：开发支撑域对外契约
- `snowy-auth`：登录、会话、Token、认证相关能力
- `snowy-system`：用户、角色、菜单、组织、岗位等系统域能力
- `snowy-dev`：字典、配置、文件、消息、日志、任务等开发支撑能力
- `snowy-boot`：启动聚合、运行配置、Spring 装配入口

### 前端主干目录

- `snowy-admin-web/src/views/auth`
- `snowy-admin-web/src/views/sys`
- `snowy-admin-web/src/views/dev`
- `snowy-admin-web/src/views/index`
- `snowy-admin-web/src/views/other`

## 默认主干能力

当前默认主干面向“后台基座”场景，包含：

- 登录认证
- 用户管理
- 角色管理
- 菜单与权限
- 组织与岗位
- 字典与系统配置
- 文件管理
- 日志与消息
- 定时任务
- 后台首页与基础壳层

## 扩展原则

### 新增后端能力

优先按领域模块化单体方式扩展：

- 认证相关能力优先进入 `snowy-auth`
- 系统域能力优先进入 `snowy-system`
- 开发支撑能力优先进入 `snowy-dev`
- 真正跨域复用的契约优先进入 `*-contract`
- 真正通用的基础设施才进入 `snowy-common`

### 新增前端能力

前端页面应优先进入明确业务域目录，不要把业务逻辑散落在首页组件或公共组件中。

如果新增的是系统域页面，优先放在：

- `src/views/sys`
- `src/views/dev`
- 或新的明确业务域目录

## 本地启动

## 环境要求

- JDK 17
- Maven 3.9+
- Node.js 18+
- MySQL
- Redis

## 数据库

默认本地配置：

- 数据库：`snowy`
- 用户名：`root`
- 密码：`root`

初始化 SQL：

- [snowy_mysql.sql](E:/ai-workspace/snowy/snowy-boot/src/main/resources/_sql/snowy_mysql.sql)

## 启动后端

在项目根目录执行：

```powershell
mvn -pl snowy-boot -am spring-boot:run
```

或先打包再运行：

```powershell
mvn -pl snowy-boot -am -DskipTests package
java -jar snowy-boot/target/snowy-boot-3.0.0.jar
```

默认访问地址：

- 后端：`http://127.0.0.1:82`
- 文档：`http://127.0.0.1:82/doc.html`

## 启动前端

在 `snowy-admin-web` 目录执行：

```powershell
npm install
npm run dev
```

构建命令：

```powershell
npm run build
```

## 默认账号

- 账号：`superAdmin`
- 密码：`123456`

## 开发与验证

任何涉及登录、菜单、权限、路由、系统管理的改动，建议至少验证：

- 后端编译
- 后端打包
- 后端启动
- 登录接口
- `getLoginUser`
- `loginMenu`
- 前端构建

常用命令：

```powershell
mvn -pl snowy-boot -am -DskipTests package
```

```powershell
cd snowy-admin-web
npm run build
```

## 上游维护策略

官方 Snowy 仍然作为参考源，但当前仓库采用：

**上游参考 + 选择性移植**

也就是说：

- 不直接合并上游结构性变化
- 安全修复优先移植
- 通用 bug 修复按价值移植
- 同步时按当前仓库结构重新落位

如果需要恢复某个已移出的能力，建议按当前仓库的模块边界重新引入，而不是直接回退到上游结构。
