# 前端 CRUD 真代码样板

这套样板用于给 Snowy 新页面提供一个可直接复制改名的 Vue 管理页起点。

## 使用方式

1. 复制 `example-view` 到 `snowy-admin-web/src/views/<domain>/<module>`
2. 复制 `example-api/exampleApi.js` 到 `snowy-admin-web/src/api/<domain>/<module>Api.js`
3. 将 `Example`、`example`、权限点、接口路径替换成真实业务名
4. 在后端菜单中把 `component` 配成对应页面路径
5. 按需继续补授权弹窗、详情抽屉、树筛选和二级 tab

## 样板覆盖内容

- 列表页 `index.vue`
- 新增/编辑弹窗 `form.vue`
- API 封装 `exampleApi.js`
- `hasPerm(...)` 权限接入
- `s-table` 与 `xn-form-container` 常见用法

## 刻意没有放进去的内容

- 复杂树筛选
- 多 tab 页面
- 授权弹窗
- 上传、导入导出

这些能力建议在复制样板后，参考 `sys/role`、`sys/resource/module`、`dev/dict` 等真实模块继续扩展。
