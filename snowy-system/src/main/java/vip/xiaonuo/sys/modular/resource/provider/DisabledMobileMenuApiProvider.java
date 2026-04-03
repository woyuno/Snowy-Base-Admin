package vip.xiaonuo.sys.modular.resource.provider;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.json.JSONObject;
import org.springframework.stereotype.Service;
import vip.xiaonuo.mobile.api.MobileMenuApi;

import java.util.Collections;
import java.util.List;

/**
 * 极简基座阶段默认关闭 mobile 模块，仅保留兼容 Bean。
 */
@Service
public class DisabledMobileMenuApiProvider implements MobileMenuApi {

    @Override
    public List<JSONObject> mobileMenuTreeSelector() {
        return Collections.emptyList();
    }

    @Override
    public List<JSONObject> mobileMenuTreeSelector(List<JSONObject> originDataList) {
        return Collections.emptyList();
    }

    @Override
    public List<Tree<String>> loginMobileMenuTree(List<String> menuIdList) {
        return Collections.emptyList();
    }
}
