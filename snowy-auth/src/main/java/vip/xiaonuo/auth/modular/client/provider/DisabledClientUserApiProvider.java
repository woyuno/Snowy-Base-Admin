package vip.xiaonuo.auth.modular.client.provider;

import cn.hutool.json.JSONObject;
import org.springframework.stereotype.Service;
import vip.xiaonuo.client.ClientUserApi;
import vip.xiaonuo.common.exception.CommonException;

import java.util.Collections;
import java.util.List;

/**
 * 极简基座阶段默认关闭 C 端用户能力，仅保留兼容 Bean。
 */
@Service
public class DisabledClientUserApiProvider implements ClientUserApi {

    private static final String MESSAGE = "当前基座版本未启用 client 模块";

    @Override
    public JSONObject getUserByIdWithoutException(String userId) {
        return null;
    }

    @Override
    public List<JSONObject> getUserListByIdListWithoutException(List<String> userIdList) {
        return Collections.emptyList();
    }

    @Override
    public JSONObject getUserByIdWithException(String userId) {
        throw new CommonException(MESSAGE);
    }

    @Override
    public List<JSONObject> getUserListByIdWithException(List<String> userIdList) {
        throw new CommonException(MESSAGE);
    }

    @Override
    public List<JSONObject> listUserWithoutCurrent() {
        return Collections.emptyList();
    }

    @Override
    public JSONObject getOrCreateClientUserExt(String userId) {
        throw new CommonException(MESSAGE);
    }
}
