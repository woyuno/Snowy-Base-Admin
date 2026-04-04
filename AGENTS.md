# 仓库协作规则

## 3 分钟摘要

如果你的目标是让 AI 尽快接手这个仓库，先记住下面几件事：

- 这是一个面向 vibecoding 的 Java 后台基座，不是单纯追上游的 Snowy 副本
- 后端模块默认只看 `snowy-auth`、`snowy-system`、`snowy-dev`、`snowy-boot`
- Java 新模块优先沿用 `controller + entity + mapper + param + service + service/impl`
- 前端新增页面默认沿用 `src/views/<domain>/<module> + src/api/<domain>/<module>Api.js`
- 写接口默认要补 `@CommonLog`
- 前端按钮权限统一走 `hasPerm(...)`
- 前端动态菜单组件路径由 `snowy-admin-web/src/store/menu.js` 解析，不存在 `page-registry.ts`
- 菜单 `component` 路径和 `src/views/**` 文件路径不一致时，页面会直接打不开

## AI 优先入口

如果当前任务是“继续扩展这个基座”，建议 AI 优先读：

1. `AGENTS.md`
2. `docs/template/doc-map.md`
3. `docs/template/crud-module-checklist.md`
4. `templates/backend-crud`
5. `templates/frontend-crud`

## 项目目标

本仓库是一套更适合 vibecoding 的 Java 后台基座。

后续所有改动都应优先服务以下目标：

- 结构清晰
- 模块边界稳定
- 默认主干轻量
- 适合 AI / agent 持续续写

后续开发时，优先保证：

- 新接手的人能快速定位代码
- agent 能稳定判断改动应该落在哪个模块
- 菜单、权限、路由、接口的联动关系可预测

当前仓库中和模板复制最相关的资产有：

- `docs/template/*.md`
- `templates/backend-crud`
- `templates/frontend-crud`

## 当前主干模块

后端主干：

- `snowy-common`
- `snowy-auth-contract`
- `snowy-system-contract`
- `snowy-dev-contract`
- `snowy-auth`
- `snowy-system`
- `snowy-dev`
- `snowy-boot`

前端主干：

- `snowy-admin-web/src/views/auth`
- `snowy-admin-web/src/views/sys`
- `snowy-admin-web/src/views/dev`
- `snowy-admin-web/src/views/index`
- `snowy-admin-web/src/views/other`

## 模块边界

### `snowy-common`

只放真正公共的基础设施：

- 配置
- 异常
- 缓存
- 通用工具
- 与业务域无关的底层能力

不要把普通业务逻辑塞进 `common`。

### `*-contract`

`snowy-auth-contract`、`snowy-system-contract`、`snowy-dev-contract` 用于承载跨域共享的契约。

适合放入：

- 跨域调用接口
- 需要被其他领域依赖的模型或声明

不适合放入：

- 具体业务实现
- 只在单一领域内部使用的类

### 领域模块

- `snowy-auth`：认证、会话、Token、登录流程
- `snowy-system`：用户、角色、菜单、组织、岗位、权限
- `snowy-dev`：字典、配置、文件、消息、日志、任务

新增功能时，优先落到最贴近业务语义的领域模块中。

普通业务代码不要默认继续堆进 `snowy-system` 或 `snowy-dev`。如果需求属于独立业务域，应新建业务模块，命名规范为：

- `snowy-biz-<domain>`

例如：

- `snowy-biz-order`
- `snowy-biz-crm`
- `snowy-biz-member`

业务模块只承载对应领域本身的业务逻辑，不要再恢复 `plugin` 或 `plugin-api` 命名。

### `snowy-boot`

只负责：

- 启动
- 配置聚合
- Spring 装配
- 应用入口

不要把具体业务实现放进 `boot`。

## 新接手时的理解顺序

建议按下面顺序建立上下文：

1. `snowy-boot`
2. `snowy-auth`
3. `snowy-system`
4. `snowy-dev`
5. `*-contract`
6. `snowy-admin-web`

如果目标是快速新增模块，而不是全量理解仓库，建议改成这个顺序：

1. `AGENTS.md`
2. `docs/template/backend-crud-example.md`
3. `docs/template/frontend-crud-example.md`
4. `docs/template/crud-module-checklist.md`
5. `templates/backend-crud`
6. `templates/frontend-crud`

如果是处理登录、菜单、权限类问题，优先联动检查：

- `snowy-auth`
- `snowy-system`
- `snowy-admin-web/src/router`
- `snowy-admin-web/src/api`
- `snowy-admin-web/src/views/auth`
- `snowy-admin-web/src/views/sys`

## 后端标准模块结构

后端新增模块时，默认沿用：

- `controller`
- `entity`
- `mapper`
- `mapper/mapping`
- `param`
- `service`
- `service/impl`

除非有明确理由，不要自行切成 `dto/vo/repository/facade` 等另一套分层。

典型路径示例：

- `snowy-dev/src/main/java/vip/xiaonuo/dev/modular/dict`
- `snowy-system/src/main/java/vip/xiaonuo/sys/modular/resource/module`

写接口默认补：

- `@CommonLog`

返回值默认沿用：

- `CommonResult<Page<...>>`
- `CommonResult<...>`
- `CommonResult<String>`

## 前端协作规则

前端默认只维护后台基座主干页面。

新增页面时：

- 系统域页面优先进入 `src/views/sys`
- 开发支撑页面优先进入 `src/views/dev`
- 认证相关页面优先进入 `src/views/auth`
- 新业务域页面应放入新的明确目录，不要乱放到首页或公共组件

如果新增的是普通业务域，前端建议同步按业务域落位：

- 页面：`snowy-admin-web/src/views/<domain>`
- 接口：`snowy-admin-web/src/api/<domain>`

前端最关键的真实规则是动态菜单组件映射：

- 实现在 `snowy-admin-web/src/store/menu.js`
- 菜单 `component` 含 `/` 时，映射到 `src/views/<component>.vue`
- 菜单 `component` 不含 `/` 时，映射到 `src/views/<component>/index.vue`

例如：

- `dev/example` -> `src/views/dev/example/index.vue`
- `sys/resource/menu/form` -> `src/views/sys/resource/menu/form.vue`

如果页面新增后菜单能看到但点不开，先检查这里。

按钮权限统一使用：

- `snowy-admin-web/src/utils/permission/index.js`
- `hasPerm(...)`

不要另起一套权限判断方式。

如果后端菜单、权限、接口有变化，前端的菜单、路由、页面、API 调用要同步检查。

## 非主干域

以下内容默认视为非主干：

- `biz`
- `gen`
- `mobile`
- `client`
- `exm`
- `front`

除非用户明确要求恢复，否则不要主动重新引入这些模块、菜单、页面或 API。

## 修改原则

1. 优先沿用当前模块边界，不轻易重新分层。
2. 能落到领域模块的代码，不要先放 `common`。
3. 只有真正跨域复用时，才考虑进入 `contract`。
4. 不要恢复插件化命名或插件式分层。
5. 用户可见文案默认使用中文。
6. 菜单、权限、路由、接口四者要一起思考，不要只改单边。

对 Snowy 来说，这 4 个联动点通常具体是：

1. 后端 controller 接口
2. 后端菜单数据里的 `path`、`component`、权限编码
3. 前端 `src/api/**`
4. 前端 `src/views/**` 与 `hasPerm(...)`

## 最低验证清单

任何声称“完成”的改动，至少应尽量覆盖这些验证：

- 后端编译
- 后端打包
- 后端启动
- 登录接口
- `getLoginUser`
- `loginMenu`
- 前端构建

如果改动影响菜单、权限、认证、路由、系统管理，这几条链路优先验证。

## 文档维护

当以下内容发生变化时，同步更新文档：

- 模块结构
- 启动方式
- 默认主干能力
- 协作约束

需要同步的文件：

- `README.md`
- `AGENTS.md`
- `docs/template/*.md`

## 维护策略

如果需要吸收外部更新或修复，优先按当前仓库结构落位，不要为了追更新而打乱现有模块边界。
