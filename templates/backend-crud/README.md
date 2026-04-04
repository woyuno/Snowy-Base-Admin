# 后端 CRUD 真代码样板

这套样板用于给 Snowy 新模块提供一个可直接复制改名的 Java 后端起点。

## 使用方式

1. 复制 `example` 目录到目标领域模块下，例如 `snowy-dev/src/main/java/vip/xiaonuo/dev/modular/example`
2. 将文件名中的 `Example`、类名中的 `Example`、包名中的 `example` 替换成你的业务名
3. 按真实表结构修改 `entity`、`param`、`mapper` 和 `service`
4. 根据需要补菜单、权限和前端页面
5. 最后对照 `docs/template/crud-module-checklist.md` 做一遍检查

## 样板覆盖内容

- `controller`
- `entity`
- `mapper`
- `mapper/mapping`
- `param`
- `service`
- `service/impl`

## 刻意没有放进去的内容

- 复杂联表
- 字典翻译缓存
- 导入导出
- 树结构查询
- 跨域 `*-contract` 暴露

这些能力建议在复制样板后，参考仓库内同类真实模块继续补。
