# Snowy Vibecoding 基座改造 Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 将 `snowy` 重构为更适合 vibecoding 的极简领域模块化单体，并同步瘦身 `snowy-admin-web`，最终以新仓库方式维护。

**Architecture:** 先重组 Maven 聚合和启动模块，将 `plugin / plugin-api` 收敛为 `common + auth + system + dev + boot` 主干；再移出 `client/mobile/gen/biz` 并同步前端菜单、路由、页面和 API；最后做最小链路验证、更新 README 与 AGENTS.md，并删除原 `.git` 重新初始化。整个过程坚持“先结构后细节、先删减后美化”的原则。

**Tech Stack:** Java 17, Spring Boot 3.5, Maven 多模块, MyBatis-Plus, Vue 3, Vite, Ant Design Vue, Git

---

## 文件结构映射

### 后端核心文件

- 修改：`E:\ai-workspace\snowy\pom.xml`
- 修改：`E:\ai-workspace\snowy\snowy-web-app\pom.xml`
- 修改：`E:\ai-workspace\snowy\snowy-web-app\src\main\java\vip\xiaonuo\Application.java`
- 修改：`E:\ai-workspace\snowy\snowy-web-app\src\main\resources\_sql\snowy_mysql.sql`
- 修改：`E:\ai-workspace\snowy\snowy-plugin\pom.xml`
- 修改：`E:\ai-workspace\snowy\snowy-plugin-api\pom.xml`
- 修改：`E:\ai-workspace\snowy\snowy-plugin\snowy-plugin-auth\pom.xml`
- 修改：`E:\ai-workspace\snowy\snowy-plugin\snowy-plugin-sys\pom.xml`
- 修改：`E:\ai-workspace\snowy\snowy-plugin\snowy-plugin-dev\pom.xml`
- 修改：`E:\ai-workspace\snowy\snowy-plugin-api\snowy-plugin-auth-api\pom.xml`
- 修改：`E:\ai-workspace\snowy\snowy-plugin-api\snowy-plugin-sys-api\pom.xml`
- 修改：`E:\ai-workspace\snowy\snowy-plugin-api\snowy-plugin-dev-api\pom.xml`
- 创建或迁移目录：`E:\ai-workspace\snowy\snowy-auth\**`
- 创建或迁移目录：`E:\ai-workspace\snowy\snowy-system\**`
- 创建或迁移目录：`E:\ai-workspace\snowy\snowy-dev\**`
- 保留：`E:\ai-workspace\snowy\snowy-common\**`
- 创建：`E:\ai-workspace\snowy\snowy-boot\**`

### 前端核心文件

- 修改：`E:\ai-workspace\snowy\snowy-admin-web\package.json`
- 修改：`E:\ai-workspace\snowy\snowy-admin-web\src\router\index.js`
- 修改：`E:\ai-workspace\snowy\snowy-admin-web\src\router\systemRouter.js`
- 修改：`E:\ai-workspace\snowy\snowy-admin-web\src\router\clientBaseRouter.js`
- 修改：`E:\ai-workspace\snowy\snowy-admin-web\src\config\route.js`
- 修改：`E:\ai-workspace\snowy\snowy-admin-web\src\store\menu.js`
- 修改：`E:\ai-workspace\snowy\snowy-admin-web\src\store\user.js`
- 删除目录：`E:\ai-workspace\snowy\snowy-admin-web\src\views\biz\**`
- 删除目录：`E:\ai-workspace\snowy\snowy-admin-web\src\views\client\**`
- 删除目录：`E:\ai-workspace\snowy\snowy-admin-web\src\views\gen\**`
- 删除目录：`E:\ai-workspace\snowy\snowy-admin-web\src\views\mobile\**`
- 删除目录：`E:\ai-workspace\snowy\snowy-admin-web\src\views\exm\**`
- 删除目录：`E:\ai-workspace\snowy\snowy-admin-web\src\views\front\**`
- 删除目录：`E:\ai-workspace\snowy\snowy-admin-web\src\views\other\**`
- 删除目录：`E:\ai-workspace\snowy\snowy-admin-web\src\api\biz\**`
- 删除目录：`E:\ai-workspace\snowy\snowy-admin-web\src\api\client\**`
- 删除目录：`E:\ai-workspace\snowy\snowy-admin-web\src\api\gen\**`
- 删除目录：`E:\ai-workspace\snowy\snowy-admin-web\src\api\mobile\**`

### 文档与收尾

- 修改：`E:\ai-workspace\snowy\README.md`
- 创建或修改：`E:\ai-workspace\snowy\AGENTS.md`
- 删除：`E:\ai-workspace\snowy\.git`

## Task 1: 收敛 Maven 主干骨架

**Files:**
- Modify: `E:\ai-workspace\snowy\pom.xml`
- Modify: `E:\ai-workspace\snowy\snowy-web-app\pom.xml`
- Modify: `E:\ai-workspace\snowy\snowy-plugin\pom.xml`
- Modify: `E:\ai-workspace\snowy\snowy-plugin-api\pom.xml`
- Create: `E:\ai-workspace\snowy\snowy-auth\pom.xml`
- Create: `E:\ai-workspace\snowy\snowy-system\pom.xml`
- Create: `E:\ai-workspace\snowy\snowy-dev\pom.xml`
- Create: `E:\ai-workspace\snowy\snowy-boot\pom.xml`

- [ ] **Step 1: 写出新旧模块映射**

```text
snowy-plugin/snowy-plugin-auth  -> snowy-auth
snowy-plugin/snowy-plugin-sys   -> snowy-system
snowy-plugin/snowy-plugin-dev   -> snowy-dev
snowy-web-app                   -> snowy-boot
snowy-plugin-api/*              -> 合并进对应领域模块
```

- [ ] **Step 2: 修改根聚合 `pom.xml`**

将 `<modules>` 收敛为：

```xml
<modules>
    <module>snowy-common</module>
    <module>snowy-auth</module>
    <module>snowy-system</module>
    <module>snowy-dev</module>
    <module>snowy-boot</module>
</modules>
```

- [ ] **Step 3: 创建新模块骨架**

创建：

```text
snowy-auth
snowy-system
snowy-dev
snowy-boot
```

每个模块先复制原 `pom.xml` 作为起点，并修正 `artifactId`。

- [ ] **Step 4: 改写 `snowy-boot/pom.xml` 默认依赖**

只保留：

```xml
<artifactId>snowy-auth</artifactId>
<artifactId>snowy-system</artifactId>
<artifactId>snowy-dev</artifactId>
```

- [ ] **Step 5: 运行 Maven 校验**

Run: `mvn -f E:\ai-workspace\snowy\pom.xml -q -DskipTests validate`

Expected: 聚合结构有效，后续报错只与源码迁移有关。

- [ ] **Step 6: Commit**

```bash
git add pom.xml snowy-auth/pom.xml snowy-system/pom.xml snowy-dev/pom.xml snowy-boot/pom.xml snowy-web-app/pom.xml
git commit -m "refactor: reshape snowy modules into vibecoding backbone"
```

## Task 2: 迁移 auth / system / dev 主干并回收 plugin-api

**Files:**
- Create/Move: `E:\ai-workspace\snowy\snowy-auth\src\main\java\vip\xiaonuo\auth\**`
- Create/Move: `E:\ai-workspace\snowy\snowy-system\src\main\java\vip\xiaonuo\sys\**`
- Create/Move: `E:\ai-workspace\snowy\snowy-dev\src\main\java\vip\xiaonuo\dev\**`
- Create/Move: `E:\ai-workspace\snowy\snowy-boot\src\main\java\vip\xiaonuo\Application.java`
- Modify: `E:\ai-workspace\snowy\snowy-web-app\src\main\java\vip\xiaonuo\Application.java`
- Modify: `E:\ai-workspace\snowy\snowy-plugin\snowy-plugin-auth\**`
- Modify: `E:\ai-workspace\snowy\snowy-plugin\snowy-plugin-sys\**`
- Modify: `E:\ai-workspace\snowy\snowy-plugin\snowy-plugin-dev\**`
- Modify: `E:\ai-workspace\snowy\snowy-plugin-api\snowy-plugin-auth-api\**`
- Modify: `E:\ai-workspace\snowy\snowy-plugin-api\snowy-plugin-sys-api\**`
- Modify: `E:\ai-workspace\snowy\snowy-plugin-api\snowy-plugin-dev-api\**`

- [ ] **Step 1: 迁移 `auth` 代码**

迁移：

```text
vip/xiaonuo/auth/core/**
vip/xiaonuo/auth/modular/**
```

将 `snowy-plugin-auth-api` 中仍被 `auth` 直接消费的接口、常量、provider 回收进 `snowy-auth`。

- [ ] **Step 2: 迁移 `system` 代码**

迁移：

```text
vip/xiaonuo/sys/core/**
vip/xiaonuo/sys/modular/**
```

将 `snowy-plugin-sys-api` 合并进 `snowy-system`。

- [ ] **Step 3: 迁移 `dev` 代码**

迁移：

```text
vip/xiaonuo/dev/core/**
vip/xiaonuo/dev/modular/**
```

同时标记只服务于 `client/mobile/gen/biz` 的支撑代码，后续清理。

- [ ] **Step 4: 迁移启动类与公共配置**

将原 `snowy-web-app` 的启动入口和 `vip/xiaonuo/core/config/**`、`handler/**`、`resources/**` 迁入 `snowy-boot`。

- [ ] **Step 5: 编译主干**

Run: `mvn -f E:\ai-workspace\snowy\pom.xml -pl snowy-common,snowy-auth,snowy-system,snowy-dev,snowy-boot -DskipTests compile`

Expected: 主干可编译，`plugin-api` 不再是必需依赖。

- [ ] **Step 6: Commit**

```bash
git add snowy-auth snowy-system snowy-dev snowy-boot pom.xml
git commit -m "refactor: migrate core domains out of plugin architecture"
```

## Task 3: 移出非主干后端域并精简 SQL

**Files:**
- Modify: `E:\ai-workspace\snowy\pom.xml`
- Modify: `E:\ai-workspace\snowy\snowy-boot\pom.xml`
- Modify: `E:\ai-workspace\snowy\snowy-web-app\src\main\resources\_sql\snowy_mysql.sql`
- Modify: `E:\ai-workspace\snowy\README.md`

- [ ] **Step 1: 从启动依赖移除非主干域**

移除：

```xml
<artifactId>snowy-plugin-biz</artifactId>
<artifactId>snowy-plugin-client</artifactId>
<artifactId>snowy-plugin-gen</artifactId>
<artifactId>snowy-plugin-mobile</artifactId>
```

- [ ] **Step 2: 从根聚合移除非主干域**

不再默认构建：

```text
client
mobile
gen
biz
```

- [ ] **Step 3: 精简初始化 SQL**

在 `snowy_mysql.sql` 中移除：

```text
CLIENT_*
MOBILE_*
GEN_*
```

并审视 `BIZ_NOTICE` 是否保留；默认推荐移出。

- [ ] **Step 4: 更新 README 中的模块说明**

补充：

```text
默认主干：common + auth + system + dev + boot
移出主干：client + mobile + gen + biz
维护模式：上游参考 + 选择性移植
```

- [ ] **Step 5: 重新编译主干**

Run: `mvn -f E:\ai-workspace\snowy\pom.xml -pl snowy-common,snowy-auth,snowy-system,snowy-dev,snowy-boot -DskipTests compile`

Expected: BUILD SUCCESS

- [ ] **Step 6: Commit**

```bash
git add pom.xml snowy-boot/pom.xml snowy-web-app/src/main/resources/_sql/snowy_mysql.sql README.md
git commit -m "refactor: trim non-core domains from default backbone"
```

## Task 4: 同步瘦身前端视图、路由和 API

**Files:**
- Modify: `E:\ai-workspace\snowy\snowy-admin-web\src\router\index.js`
- Modify: `E:\ai-workspace\snowy\snowy-admin-web\src\router\systemRouter.js`
- Modify: `E:\ai-workspace\snowy\snowy-admin-web\src\router\clientBaseRouter.js`
- Modify: `E:\ai-workspace\snowy\snowy-admin-web\src\config\route.js`
- Modify: `E:\ai-workspace\snowy\snowy-admin-web\src\store\menu.js`
- Modify: `E:\ai-workspace\snowy\snowy-admin-web\src\store\user.js`
- Delete: `E:\ai-workspace\snowy\snowy-admin-web\src\views\biz\**`
- Delete: `E:\ai-workspace\snowy\snowy-admin-web\src\views\client\**`
- Delete: `E:\ai-workspace\snowy\snowy-admin-web\src\views\gen\**`
- Delete: `E:\ai-workspace\snowy\snowy-admin-web\src\views\mobile\**`
- Delete: `E:\ai-workspace\snowy\snowy-admin-web\src\views\exm\**`
- Delete: `E:\ai-workspace\snowy\snowy-admin-web\src\views\front\**`
- Delete: `E:\ai-workspace\snowy\snowy-admin-web\src\views\other\**`
- Delete: `E:\ai-workspace\snowy\snowy-admin-web\src\api\biz\**`
- Delete: `E:\ai-workspace\snowy\snowy-admin-web\src\api\client\**`
- Delete: `E:\ai-workspace\snowy\snowy-admin-web\src\api\gen\**`
- Delete: `E:\ai-workspace\snowy\snowy-admin-web\src\api\mobile\**`

- [ ] **Step 1: 去掉 `client` 路由入口**

在 `src/router/index.js` 中移除：

```js
import clientBaseRouter, { validateClientAccess } from './clientBaseRouter'
const routes = [...systemRouter, ...whiteListRouters, ...clientBaseRouter, ...routes_404]
```

改成：

```js
const routes = [...systemRouter, ...whiteListRouters, ...routes_404]
```

并删除 `validateClientAccess` 分支。

- [ ] **Step 2: 清理静态路由和菜单注入**

从 `systemRouter.js`、`route.js` 中移除对以下域的引用：

```text
biz
client
gen
mobile
exm
front
other
```

- [ ] **Step 3: 删除页面和 API 目录**

删除：

```text
src/views/biz
src/views/client
src/views/gen
src/views/mobile
src/views/exm
src/views/front
src/views/other
src/api/biz
src/api/client
src/api/gen
src/api/mobile
```

- [ ] **Step 4: 调整菜单与用户缓存逻辑**

检查 `menu.js`、`user.js`，确保菜单刷新、默认首页、登录回跳都只落在保留主干页面。

- [ ] **Step 5: 构建前端**

Run: `npm run build`

Expected: `vite build --mode production` 成功，无被删除目录的残留引用。

- [ ] **Step 6: Commit**

```bash
git add snowy-admin-web/src/router snowy-admin-web/src/config snowy-admin-web/src/store snowy-admin-web/src/api snowy-admin-web/src/views
git commit -m "refactor: slim snowy admin web to match backend backbone"
```

## Task 5: 文档收尾、验证和 Git 重置

**Files:**
- Modify: `E:\ai-workspace\snowy\README.md`
- Modify: `E:\ai-workspace\snowy\docs\superpowers\specs\2026-04-03-snowy-vibecoding-design.md`
- Create or Modify: `E:\ai-workspace\snowy\AGENTS.md`
- Delete: `E:\ai-workspace\snowy\.git`

- [ ] **Step 1: 验证后端主干**

Run: `mvn -f E:\ai-workspace\snowy\pom.xml -pl snowy-common,snowy-auth,snowy-system,snowy-dev,snowy-boot -DskipTests compile`

Expected: BUILD SUCCESS

- [ ] **Step 2: 验证前端构建**

Run: `npm run build`

Expected: build 成功，输出 `dist/`

- [ ] **Step 3: 手工 smoke test**

验证：

```text
登录页可打开
登录成功后进入首页
用户/角色/菜单/组织页面可访问
字典/配置/日志/任务至少各打开一个页面
已删除能力在菜单和路由中不可见
```

- [ ] **Step 4: 更新 README**

README 至少写清：

```text
项目定位：Snowy Vibecoding 基座
新的后端模块结构
前端保留与移出的页面域
默认支持的后台能力
官方 snowy 作为参考源，你的仓库作为主仓库
```

- [ ] **Step 5: 新增或更新 AGENTS.md**

AGENTS.md 至少写清：

```text
模块边界：common / auth / system / dev / boot
新增业务模块默认放置规则
不要恢复 client/mobile/gen/biz 到默认主干
官方更新采用选择性移植，不直接 merge 上游
用户可见文案默认中文
```

- [ ] **Step 6: 删除原 Git 历史并重新初始化**

Run:

```bash
Remove-Item -Recurse -Force .git
git init
git add .
git commit -m "chore: initialize snowy vibecoding base"
```

- [ ] **Step 7: 推送到你的 GitHub 仓库**

Run:

```bash
git remote add origin <你的 GitHub 仓库地址>
git branch -M main
git push -u origin main
```

- [ ] **Step 8: 记录维护策略**

在 README 或维护文档中明确：

```text
官方 snowy 仍为参考上游
你的 GitHub 仓库是主仓库
后续按“上游参考 + 选择性移植”维护
```

## 自检

- 已覆盖 spec 中的后端重组、前端同步瘦身、Git 重置、README/AGENTS.md 收尾和维护模式
- 未使用 `TODO/TBD` 占位词
- 任务顺序符合“骨架 -> 迁移 -> 删减 -> 前端 -> 验证收尾”
