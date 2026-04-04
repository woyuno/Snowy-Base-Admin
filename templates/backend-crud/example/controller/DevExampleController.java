package vip.xiaonuo.dev.modular.example.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xingfudeshi.knife4j.annotations.ApiOperationSupport;
import com.github.xingfudeshi.knife4j.annotations.ApiSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vip.xiaonuo.common.annotation.CommonLog;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.dev.modular.example.entity.DevExample;
import vip.xiaonuo.dev.modular.example.param.DevExampleAddParam;
import vip.xiaonuo.dev.modular.example.param.DevExampleEditParam;
import vip.xiaonuo.dev.modular.example.param.DevExampleIdParam;
import vip.xiaonuo.dev.modular.example.param.DevExamplePageParam;
import vip.xiaonuo.dev.modular.example.service.DevExampleService;

import java.util.List;

@Tag(name = "示例业务控制器")
@ApiSupport(author = "SNOWY_TEAM", order = 10)
@RestController
@Validated
public class DevExampleController {

    @Resource
    private DevExampleService devExampleService;

    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取示例分页")
    @GetMapping("/dev/example/page")
    public CommonResult<Page<DevExample>> page(DevExamplePageParam devExamplePageParam) {
        return CommonResult.data(devExampleService.page(devExamplePageParam));
    }

    @ApiOperationSupport(order = 2)
    @Operation(summary = "获取示例详情")
    @GetMapping("/dev/example/detail")
    public CommonResult<DevExample> detail(@Valid DevExampleIdParam devExampleIdParam) {
        return CommonResult.data(devExampleService.detail(devExampleIdParam));
    }

    @ApiOperationSupport(order = 3)
    @Operation(summary = "新增示例")
    @CommonLog("新增示例")
    @PostMapping("/dev/example/add")
    public CommonResult<String> add(@RequestBody @Valid DevExampleAddParam devExampleAddParam) {
        devExampleService.add(devExampleAddParam);
        return CommonResult.ok();
    }

    @ApiOperationSupport(order = 4)
    @Operation(summary = "编辑示例")
    @CommonLog("编辑示例")
    @PostMapping("/dev/example/edit")
    public CommonResult<String> edit(@RequestBody @Valid DevExampleEditParam devExampleEditParam) {
        devExampleService.edit(devExampleEditParam);
        return CommonResult.ok();
    }

    @ApiOperationSupport(order = 5)
    @Operation(summary = "删除示例")
    @CommonLog("删除示例")
    @PostMapping("/dev/example/delete")
    public CommonResult<String> delete(
            @RequestBody @Valid @NotEmpty(message = "集合不能为空") List<DevExampleIdParam> devExampleIdParamList) {
        devExampleService.delete(devExampleIdParamList);
        return CommonResult.ok();
    }
}
