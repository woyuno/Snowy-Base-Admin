# 后端 CRUD 模块样板

这份样板用于在当前仓库中新增一个标准 Java 后端模块时，沿用现有 Snowy 主干结构，而不是临时拼一套新的写法。

## 推荐目录结构

以 `snowy-dev` 下的字典模块为参考，建议保持：

```text
snowy-<domain>/src/main/java/vip/xiaonuo/<domain>/modular/<module>/
├─ controller/
│  └─ XxxController.java
├─ entity/
│  └─ Xxx.java
├─ mapper/
│  ├─ XxxMapper.java
│  └─ mapping/
│     └─ XxxMapper.xml
├─ param/
│  ├─ XxxAddParam.java
│  ├─ XxxEditParam.java
│  ├─ XxxIdParam.java
│  └─ XxxPageParam.java
├─ service/
│  ├─ XxxService.java
│  └─ impl/
│     └─ XxxServiceImpl.java
└─ enums/
```

如果模块属于：

- 认证域：落到 `snowy-auth`
- 系统域：落到 `snowy-system`
- 开发支撑域：落到 `snowy-dev`

## 最小职责拆分

- `controller`
  负责路由、参数接收、`CommonResult` 包装、`@CommonLog`
- `entity`
  负责数据库实体
- `param`
  负责新增、编辑、详情、分页查询参数对象
- `mapper`
  负责 MyBatis-Plus Mapper 与 XML 查询
- `service`
  负责业务接口
- `service/impl`
  负责分页、校验、增删改查实现

## 标准接口集合

一个典型 CRUD 模块至少应考虑：

- `GET /xxx/page`
- `GET /xxx/detail`
- `POST /xxx/add`
- `POST /xxx/edit`
- `POST /xxx/delete`

如果模块还需要树选择器、下拉列表、授权、状态切换，再按业务单独补接口。

## 结果包装与分页约定

控制器返回值统一沿用：

```java
CommonResult<Page<Xxx>>
CommonResult<Xxx>
CommonResult<String>
```

分页实现优先沿用：

```java
return this.page(CommonPageRequest.defaultPage(), queryWrapper);
```

## 写接口日志约定

所有有副作用的接口默认补：

```java
@CommonLog("新增示例")
@CommonLog("编辑示例")
@CommonLog("删除示例")
```

纯分页、列表、详情接口默认不加。

## 参数对象约定

`param` 下至少建议有：

- `XxxAddParam`
- `XxxEditParam`
- `XxxIdParam`
- `XxxPageParam`

并使用：

- `@Schema`
- `jakarta.validation` 约束
- `@Getter`
- `@Setter`

## Mapper 约定

如果只靠 MyBatis-Plus 通用能力能完成，就保持 Mapper 很薄。

如果有联表、自定义分页、复杂树查询，再把 SQL 放进：

- `mapper/mapping/XxxMapper.xml`

不要把复杂 SQL 写死在 service 里。

## 新增模块建议顺序

1. 先确定模块属于哪个领域 Maven 模块
2. 先补 `entity + param + service`
3. 再补 `service/impl + controller`
4. 如需复杂查询，再补 `mapper.xml`
5. 再补菜单、权限和前端页面
6. 最后按 `crud-module-checklist.md` 走一遍检查
