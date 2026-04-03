package vip.xiaonuo.sys.modular.resource.provider;

import org.springframework.stereotype.Service;
import vip.xiaonuo.mobile.api.MobileButtonApi;

import java.util.Collections;
import java.util.List;

/**
 * 极简基座阶段默认关闭 mobile 模块，仅保留兼容 Bean。
 */
@Service
public class DisabledMobileButtonApiProvider implements MobileButtonApi {

    @Override
    public List<String> listButtonCodeListByIdList(List<String> idList) {
        return Collections.emptyList();
    }
}
