# 模板 Onboarding

这份单页指引面向“刚复制仓库，准备让 AI 继续接手开发”的场景。

目标不是解释全部细节，而是尽快建立正确上下文。

## 第 0 步：先读这 4 个文件

1. `AGENTS.md`
2. `README.md`
3. `docs/template/crud-module-checklist.md`
4. `docs/template/template-smoke-checklist.md`

分工建议：

- `AGENTS.md`：看真实约束、路径规则、前后端联动点
- `README.md`：看模块结构、启动方式、模板资产入口
- `crud-module-checklist.md`：看新增模块要改哪些地方
- `template-smoke-checklist.md`：看最小验证动作

## 第 1 步：准备本地环境

本地至少要有：

- JDK 17
- Maven 3.9+
- Node.js 18+
- MySQL
- Redis

默认开发环境关键值：

- 后端：`http://127.0.0.1:82`
- 前端：Vite 默认本地开发地址
- 数据库：`snowy`
- 用户名：`root`
- 密码：`root`

初始化 SQL：

- `snowy-boot/src/main/resources/_sql/snowy_mysql.sql`

## 第 2 步：跑最小静态验证

后端：

```powershell
mvn -pl snowy-boot -am -DskipTests package
```

前端：

```powershell
cd snowy-admin-web
npm install
npm run build
```

如果这两步都通过，说明当前模板的后端聚合、前端构建和基本依赖没有坏掉。

## 第 3 步：启动前后端

后端：

```powershell
mvn -pl snowy-boot -am spring-boot:run
```

前端：

```powershell
cd snowy-admin-web
npm run dev
```

默认验证地址：

- 后端：`http://127.0.0.1:82`
- 文档：`http://127.0.0.1:82/doc.html`

## 第 4 步：确认 5 条最关键事实

1. 后端接口没有额外 `/api` 前缀，直接挂在根路径
2. Java 新模块不要乱放，优先进入 `snowy-auth`、`snowy-system`、`snowy-dev`
3. 前端动态菜单组件路径由 `snowy-admin-web/src/store/menu.js` 解析
4. 前端 API 一律从 `snowy-admin-web/src/api/**` 走 `baseRequest`
5. 写接口默认应补 `@CommonLog`

## 第 5 步：开始新增模块

后端新增 CRUD，优先参考：

- `docs/template/backend-crud-example.md`
- `templates/backend-crud`

前端新增页面，优先参考：

- `docs/template/frontend-crud-example.md`
- `templates/frontend-crud`

## 最容易踩坑的 6 件事

1. 在 `snowy-common` 里塞业务逻辑
2. 菜单 `component` 路径和前端文件路径对不上
3. 只改接口不改前端 API 封装
4. 只改前端页面不补按钮权限
5. 写接口忘了 `@CommonLog`
6. 模块能编译，但没验证登录、菜单和页面跳转
