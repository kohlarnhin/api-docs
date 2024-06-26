package com.koh.apidocs.configurable;

import com.intellij.ide.DataManager;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.options.ex.ConfigurableExtensionPointUtil;
import com.intellij.openapi.options.ex.ConfigurableWrapper;
import com.intellij.openapi.options.ex.Settings;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.labels.LinkLabel;
import com.koh.apidocs.constants.IdConstants;
import com.koh.apidocs.enums.LinkLabelAndIdEnum;
import com.koh.apidocs.utils.ConfigurableUtils;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

public class ApiDocsConfigurable implements Configurable {

    private Project project;

    private JPanel mainPanel;

    public ApiDocsConfigurable(Project project) {
        this.project = project;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "";
    }

    /**
     * 创建ApiDocs的配置项
     * 同时包含一些初始化操作
     *
     * @return
     */
    @Nullable
    @Override
    public JComponent createComponent() {
        //TODO 测试期间保证每次进入方法 便于调试
//        if (mainPanel != null) {
//            return mainPanel;
//        }
        ConfigurableUtils.initConfigurablesByProjectAndParentId(project);
        List<Configurable> configurables = ConfigurableUtils.getConfigurablesByParentId(IdConstants.ApiDocs);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JLabel description = new JLabel("APIDocs 插件配置。");
        mainPanel.add(description);
        // 遍历配置项枚举，动态创建每个配置项的 LinkLabel
        for (LinkLabelAndIdEnum value : LinkLabelAndIdEnum.values()) {
            LinkLabel<Object> linkLabel = value.getLinkLabel();
            ApplicationManager.getApplication().executeOnPooledThread(() -> {
                linkLabel.setListener((aSource, aLinkData) -> {
                    DataManager.getInstance().getDataContextFromFocusAsync().onSuccess(dataContext -> {
                        Settings settings = Settings.KEY.getData(dataContext);
                        if (settings != null) {
                            Configurable configurable = settings.find(value.getConfigId());
                            if (configurable != null) {
                                settings.select(configurable);
                            }
                        }
                    });
                }, null);
                mainPanel.add(linkLabel);
            });
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


}
