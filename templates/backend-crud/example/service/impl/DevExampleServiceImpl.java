package vip.xiaonuo.dev.modular.example.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.xiaonuo.common.enums.CommonSortOrderEnum;
import vip.xiaonuo.common.exception.CommonException;
import vip.xiaonuo.common.page.CommonPageRequest;
import vip.xiaonuo.dev.modular.example.entity.DevExample;
import vip.xiaonuo.dev.modular.example.mapper.DevExampleMapper;
import vip.xiaonuo.dev.modular.example.param.DevExampleAddParam;
import vip.xiaonuo.dev.modular.example.param.DevExampleEditParam;
import vip.xiaonuo.dev.modular.example.param.DevExampleIdParam;
import vip.xiaonuo.dev.modular.example.param.DevExamplePageParam;
import vip.xiaonuo.dev.modular.example.service.DevExampleService;

import java.util.List;

@Service
public class DevExampleServiceImpl extends ServiceImpl<DevExampleMapper, DevExample> implements DevExampleService {

    @Override
    public Page<DevExample> page(DevExamplePageParam devExamplePageParam) {
        QueryWrapper<DevExample> queryWrapper = new QueryWrapper<DevExample>().checkSqlInjection();
        if (ObjectUtil.isNotEmpty(devExamplePageParam.getSearchKey())) {
            queryWrapper.lambda().and(q -> q.like(DevExample::getName, devExamplePageParam.getSearchKey())
                    .or().like(DevExample::getCode, devExamplePageParam.getSearchKey()));
        }
        if (ObjectUtil.isAllNotEmpty(devExamplePageParam.getSortField(), devExamplePageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(devExamplePageParam.getSortOrder());
            queryWrapper.orderBy(
                    true,
                    devExamplePageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(devExamplePageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(DevExample::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Override
    public DevExample detail(DevExampleIdParam devExampleIdParam) {
        return queryEntity(devExampleIdParam.getId());
    }

    @Override
    public void add(DevExampleAddParam devExampleAddParam) {
        checkNameUnique(devExampleAddParam.getName(), null);
        DevExample devExample = BeanUtil.toBean(devExampleAddParam, DevExample.class);
        this.save(devExample);
    }

    @Override
    public void edit(DevExampleEditParam devExampleEditParam) {
        DevExample devExample = queryEntity(devExampleEditParam.getId());
        checkNameUnique(devExampleEditParam.getName(), devExampleEditParam.getId());
        BeanUtil.copyProperties(devExampleEditParam, devExample);
        this.updateById(devExample);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<DevExampleIdParam> devExampleIdParamList) {
        List<String> ids = CollStreamUtil.toList(devExampleIdParamList, DevExampleIdParam::getId);
        if (ObjectUtil.isNotEmpty(ids)) {
            this.removeByIds(ids);
        }
    }

    private DevExample queryEntity(String id) {
        DevExample devExample = this.getById(id);
        if (ObjectUtil.isEmpty(devExample)) {
            throw new CommonException("示例数据不存在，id值为：{}", id);
        }
        return devExample;
    }

    private void checkNameUnique(String name, String currentId) {
        QueryWrapper<DevExample> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(DevExample::getName, name);
        if (ObjectUtil.isNotEmpty(currentId)) {
            queryWrapper.lambda().ne(DevExample::getId, currentId);
        }
        if (this.count(queryWrapper) > 0) {
            throw new CommonException("存在重复的示例名称，名称为：{}", name);
        }
    }
}
