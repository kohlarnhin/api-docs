package com.koh.apidocs.configurable;

import com.intellij.ConfigurableFactory;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.SearchableConfigurable;
import e.C.O;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class ApiDocsConfigurable implements SearchableConfigurable.Parent {

    private JPanel mainPanel;

    @Nls
    @Override
    public String getDisplayName() {
        return "";
    }

    /**
     * 创建ApiDocs的配置项
     * 同时加载子类配置项
     * 此时子类还没有创建Panel，只有当点击子类配置项的时候才会创建Panel
     * @return
     */
    @Nullable
    @Override
    public JComponent createComponent() {
        if (mainPanel != null) {
            return mainPanel;
        }
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JLabel description = new JLabel("APIDocs 插件配置。");
        mainPanel.add(description);
        // 遍历配置项枚举，动态创建每个配置项的 LinkLabel
        for (Configurable configurable : this.getConfigurables()) {
            if (configurable instanceof ExtensionConfigurable) {
                mainPanel.add(((ExtensionConfigurable<?>) configurable).getLinkLabel());
            }
        }
        return mainPanel;
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public void apply() {
    }

    @Override
    public void reset() {
    }


    public Configurable @NotNull [] getConfigurables() {
        return new Configurable[]{MyConfigConfigurable.getInstance()};
    }

    @Override
    public @NotNull @NonNls String getId() {
        return "ApiDocs";
    }
}
