package vip.xiaonuo.mobile.api;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.json.JSONObject;

import java.util.List;

/**
 * 移动端菜单接口，当前极简基座阶段仅保留兼容定义。
 */
public interface MobileMenuApi {

    List<JSONObject> mobileMenuTreeSelector();

    List<JSONObject> mobileMenuTreeSelector(List<JSONObject> originDataList);

    List<Tree<String>> loginMobileMenuTree(List<String> menuIdList);
}
