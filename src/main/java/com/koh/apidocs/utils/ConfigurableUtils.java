package com.koh.apidocs.utils;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ex.ConfigurableExtensionPointUtil;
import com.intellij.openapi.options.ex.ConfigurableWrapper;
import com.intellij.openapi.project.Project;

import java.util.*;

/**
 * 配置项工具类
 */
public class ConfigurableUtils {

    // configures缓存
    private static final Map<String, List<Configurable>> initConfigurablesMap = new HashMap<>();

    /**
     * 初始化操作
     * 包含获取所有的配置项并对该插件下所有的配置项进行分组处理并进行缓存
     * 因为各个配置项的id都是唯一的，所以不会出现重复的情况
     * 将使用递归方法不断的进行分组处理直到没有子配置项为止
     * 没有parentId的都将归并到other下
     * 每一级只保存自己的子配置项 自己的id作为key value则是自己的字配置项list
     */
    public static void initConfigurablesByProjectAndParentId(Project project) {
        // 获取所有的配置项
        List<Configurable> configurables = ConfigurableExtensionPointUtil.getConfigurables(project, false, true);

        // 临时存储没有parentId的配置项
        List<Configurable> otherConfigurables = new ArrayList<>();

        // 遍历所有的配置项
        for (Configurable configurable : configurables) {
            ConfigurableWrapper configurableWrapper = (ConfigurableWrapper) configurable;
            String pid = configurableWrapper.getParentId();
            if (pid == null || pid.isEmpty()) {
                // 没有parentId的配置项
                otherConfigurables.add(configurable);
            } else {
                // 有parentId的配置项
                initConfigurablesMap.computeIfAbsent(pid, k -> new ArrayList<>()).add(configurable);
            }
        }
        // 将没有parentId的配置项存入"other"分组
        initConfigurablesMap.put("other", otherConfigurables);
    }

    public static List<Configurable> getConfigurablesByParentId(String parentId) {
        return initConfigurablesMap.get(parentId);
    }
}
