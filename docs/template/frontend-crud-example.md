# 前端 CRUD 页面样板

这份样板用于在当前仓库中新增一个 Vue 后台页面时，保持与现有 `views + api + 动态菜单 + hasPerm` 模式一致。

## 推荐目录结构

一个典型页面通常包含：

```text
snowy-admin-web/src/views/<domain>/<module>/
├─ index.vue
└─ form.vue

snowy-admin-web/src/api/<domain>/
└─ <module>Api.js
```

复杂页面可以继续拆：

- 授权弹窗
- 详情弹窗
- 树筛选面板
- 二级 tab

但建议先沿用已有模块的页面组织方式，不要自造新分层。

## 页面主体建议结构

`index.vue` 通常负责：

- 搜索表单
- 列表表格
- 主操作按钮
- 删除与批量删除
- 打开 `form.vue`

`form.vue` 通常负责：

- 新增/编辑弹窗
- 表单校验
- 保存调用

## API 层建议

前端接口统一放在：

```text
snowy-admin-web/src/api/<domain>/<module>Api.js
```

常见模式：

```js
const request = (url, ...arg) => baseRequest(`/dev/example/` + url, ...arg)
```

然后暴露：

- `page`
- `detail`
- `submitForm`
- `delete`

## 权限接入约定

当前前端按钮权限统一通过：

```js
import { hasPerm } from '@/utils/permission'
```

常见写法：

```vue
<a-button v-if="hasPerm('dev:example:add')">新增</a-button>
```

不要把权限判断散成多套实现。

## 动态菜单组件路径约定

这个仓库没有 `page-registry.ts`，而是依赖后端菜单里的 `component` 字段，通过：

- `snowy-admin-web/src/store/menu.js`

动态加载页面组件。

规则是：

1. `component` 含 `/` 时，映射到 `src/views/<component>.vue`
2. `component` 不含 `/` 时，映射到 `src/views/<component>/index.vue`

例如：

- `dev/example` 对应 `src/views/dev/example/index.vue`
- `sys/resource/menu/form` 对应 `src/views/sys/resource/menu/form.vue`

这是 Snowy 前端最容易改漏的联动点。

## 标准 CRUD 页的最小组成

一个典型页面通常至少包含：

- 搜索条件
- 查询 / 重置
- 新增按钮
- 列表表格
- 编辑
- 删除
- 表单弹窗

如果复杂度较低，也建议保留同样骨架，只减少字段，不要换另一套页面模式。
