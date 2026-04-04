# 模板文档索引

这份索引只回答一个问题：AI 或协作者遇到某类问题时，应该先查哪份文档。

## 先看哪份

| 你的问题 | 先看 |
| --- | --- |
| 这个仓库是什么、怎么启动、主干模块有哪些 | `README.md` |
| 这个仓库真实怎么工作、有哪些联动点和护栏 | `AGENTS.md` |
| 我刚复制完模板，第一小时应该先做什么 | `template-onboarding.md` |
| 我想做模板级最小验收，确认基础盘没坏 | `template-smoke-checklist.md` |
| 我准备新增一个 Java 后端 CRUD 模块 | `backend-crud-example.md` |
| 我准备新增一个 Vue 后台页面或 CRUD 视图 | `frontend-crud-example.md` |
| 我想系统检查一个 CRUD 模块有没有改漏联动点 | `crud-module-checklist.md` |

## 推荐阅读顺序

如果目标是让 AI 快速接手这个仓库，建议顺序：

1. `AGENTS.md`
2. `README.md`
3. `docs/template/template-onboarding.md`
4. `templates/backend-crud/README.md`
5. `templates/frontend-crud/README.md`

## 一个简单原则

- `README.md` 讲项目全貌和入口
- `AGENTS.md` 讲真实联动点、路径规则和改动护栏
- `docs/template/*.md` 讲具体操作方式
- `templates/**` 讲“直接复制什么文件开始干活”

如果几份文档出现冲突，以代码真实结构为准，并优先更新：

1. `AGENTS.md`
2. 对应模板文档
3. `README.md`
