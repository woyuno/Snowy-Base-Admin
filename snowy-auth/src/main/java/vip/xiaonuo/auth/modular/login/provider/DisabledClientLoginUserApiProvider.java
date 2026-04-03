package vip.xiaonuo.auth.modular.login.provider;

import cn.hutool.json.JSONObject;
import org.springframework.stereotype.Service;
import vip.xiaonuo.auth.api.SaBaseLoginUserApi;
import vip.xiaonuo.auth.core.pojo.SaBaseClientLoginUser;
import vip.xiaonuo.auth.core.pojo.SaBaseLoginUser;
import vip.xiaonuo.common.exception.CommonException;

import java.util.Collections;
import java.util.List;

/**
 * 极简基座阶段默认关闭 C 端登录用户域，仅保留兼容 Bean。
 */
@Service("clientLoginUserApi")
public class DisabledClientLoginUserApiProvider implements SaBaseLoginUserApi {

    private static final String MESSAGE = "当前基座版本未启用 client 模块";

    @Override
    public SaBaseLoginUser getUserById(String id) {
        return null;
    }

    @Override
    public SaBaseClientLoginUser getClientUserById(String id) {
        return null;
    }

    @Override
    public SaBaseLoginUser getUserByAccount(String account) {
        return null;
    }

    @Override
    public SaBaseClientLoginUser getClientUserByAccount(String account) {
        return null;
    }

    @Override
    public SaBaseLoginUser getUserByPhone(String phone) {
        return null;
    }

    @Override
    public SaBaseLoginUser getUserByEmail(String email) {
        return null;
    }

    @Override
    public SaBaseClientLoginUser getClientUserByPhone(String phone) {
        return null;
    }

    @Override
    public SaBaseClientLoginUser getClientUserByEmail(String email) {
        return null;
    }

    @Override
    public List<JSONObject> listUserByUserIdList(List<String> userIdList) {
        return Collections.emptyList();
    }

    @Override
    public List<JSONObject> getRoleListByUserId(String userId) {
        return Collections.emptyList();
    }

    @Override
    public List<String> getButtonCodeListListByUserAndRoleIdList(List<String> userAndRoleIdList) {
        return Collections.emptyList();
    }

    @Override
    public List<String> getMobileButtonCodeListListByUserIdAndRoleIdList(List<String> userAndRoleIdList) {
        return Collections.emptyList();
    }

    @Override
    public List<JSONObject> getPermissionListByUserIdAndRoleIdList(List<String> userAndRoleIdList, String orgId) {
        return Collections.emptyList();
    }

    @Override
    public void updateUserLoginInfo(String userId, String device) {
        // client 模块已关闭，登录态更新降级为空操作
    }

    @Override
    public SaBaseLoginUser createUserWithPhone(String phone) {
        return null;
    }

    @Override
    public SaBaseClientLoginUser createClientUserWithPhone(String phone) {
        throw new CommonException(MESSAGE);
    }

    @Override
    public SaBaseLoginUser createUserWithEmail(String email) {
        return null;
    }

    @Override
    public SaBaseClientLoginUser createClientUserWithEmail(String email) {
        throw new CommonException(MESSAGE);
    }

    @Override
    public void doRegister(String account, String password) {
        throw new CommonException(MESSAGE);
    }

    @Override
    public void refreshUserDataScope(String userId, List<SaBaseLoginUser.DataScope> dataScopeList) {
        // client 模块已关闭，无需刷新
    }

    @Override
    public void refreshOnlineUserPermission(String userId) {
        // client 模块已关闭，无需刷新
    }
}
