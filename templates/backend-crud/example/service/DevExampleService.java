package vip.xiaonuo.dev.modular.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.dev.modular.example.entity.DevExample;
import vip.xiaonuo.dev.modular.example.param.DevExampleAddParam;
import vip.xiaonuo.dev.modular.example.param.DevExampleEditParam;
import vip.xiaonuo.dev.modular.example.param.DevExampleIdParam;
import vip.xiaonuo.dev.modular.example.param.DevExamplePageParam;

import java.util.List;

public interface DevExampleService extends IService<DevExample> {

    Page<DevExample> page(DevExamplePageParam devExamplePageParam);

    DevExample detail(DevExampleIdParam devExampleIdParam);

    void add(DevExampleAddParam devExampleAddParam);

    void edit(DevExampleEditParam devExampleEditParam);

    void delete(List<DevExampleIdParam> devExampleIdParamList);
}
