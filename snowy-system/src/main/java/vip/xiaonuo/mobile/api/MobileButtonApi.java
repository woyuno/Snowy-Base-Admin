package vip.xiaonuo.mobile.api;

import java.util.List;

/**
 * 移动端按钮接口，当前极简基座阶段仅保留兼容定义。
 */
public interface MobileButtonApi {

    List<String> listButtonCodeListByIdList(List<String> idList);
}
