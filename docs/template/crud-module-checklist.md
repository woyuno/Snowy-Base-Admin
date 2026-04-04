# CRUD 模块接入检查清单

这份清单用于新增一个业务模块时，快速确认当前改动是否已经走完 Snowy 模板中的关键联动点。

## 后端

1. 是否已经选对 Maven 模块：`snowy-auth`、`snowy-system` 或 `snowy-dev`
2. 是否已建立 `controller + service + service/impl + param + entity + mapper`
3. 写接口是否已补 `@CommonLog`
4. 分页接口是否统一返回 `CommonResult<Page<...>>`
5. 详情接口是否统一返回 `CommonResult<...>`
6. 删除接口是否使用 `List<XxxIdParam>`
7. 自定义 SQL 是否已经放进 `mapper/mapping/*.xml`
8. 是否避免把业务逻辑塞进 `snowy-common`

## 前端

1. 是否已创建 `src/views/<domain>/<module>/index.vue`
2. 是否已补 `form.vue` 或其他必要弹窗组件
3. 是否已创建 `src/api/<domain>/<module>Api.js`
4. 页面文案、按钮、表单标签是否已改成真实中文业务语义
5. 按钮权限是否统一使用 `hasPerm(...)`
6. 列表页面是否沿用现有 `s-table`、`xn-panel`、`xn-form-container` 等模式

## 菜单与路由联动

1. 后端菜单数据是否存在
2. 后端菜单 path 是否正确
3. 后端菜单 component 是否和前端真实文件路径一致
4. `component` 是否符合 `menu.js` 动态加载规则
5. 目标菜单是否能在侧边栏正常显示
6. 页面点击后是否能正常加载，不出现空白或 404

## 最小验证

1. `mvn -pl snowy-boot -am -DskipTests package`
2. `cd snowy-admin-web && npm run build`
3. 能正常登录
4. `getLoginUser` 正常
5. `loginMenu` 正常
6. 目标菜单可见
7. 目标页面可打开
8. 新增、编辑、删除至少人工验证一条链路
