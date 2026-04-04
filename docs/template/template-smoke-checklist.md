# 模板复制后的 Smoke 验收清单

这份清单面向“刚复制完模板，准备确认基础盘是否还能稳定工作”的场景。

## 先跑命令

后端构建：

```powershell
mvn -pl snowy-boot -am -DskipTests package
```

前端构建：

```powershell
cd snowy-admin-web
npm run build
```

## 再做最小人工验证

建议至少手工确认：

1. 登录成功
2. `getLoginUser` 正常返回
3. `loginMenu` 正常返回
4. 侧边栏菜单正常显示
5. 用户管理页面可打开
6. 角色管理页面可打开
7. 字典页面可打开
8. `doc.html` 可访问

## 适用场景

- 刚复制模板准备作为新项目起点
- 调整了 Maven 聚合或公共配置后
- 调整了菜单、权限、路由、页面组件路径后
- 新增模板文档和真代码样板后
- 准备把当前版本交给其他 agent 使用之前
