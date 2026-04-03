package vip.xiaonuo.client;

import cn.hutool.json.JSONObject;

import java.util.List;

/**
 * C端用户接口，当前极简基座阶段仅保留兼容定义。
 */
public interface ClientUserApi {

    JSONObject getUserByIdWithoutException(String userId);

    List<JSONObject> getUserListByIdListWithoutException(List<String> userIdList);

    JSONObject getUserByIdWithException(String userId);

    List<JSONObject> getUserListByIdWithException(List<String> userIdList);

    List<JSONObject> listUserWithoutCurrent();

    JSONObject getOrCreateClientUserExt(String userId);
}
