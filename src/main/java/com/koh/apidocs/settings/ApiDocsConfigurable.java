package com.koh.apidocs.settings;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.Configurable.Composite;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.ui.HyperlinkLabel;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ApiDocsConfigurable implements SearchableConfigurable, Composite {

    private JPanel mainPanel;

    @Nls
    @Override
    public String getDisplayName() {
        return "APIDocs";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        if (mainPanel == null) {
            mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            JLabel description = new JLabel("APIDocs 插件配置。");
            mainPanel.add(description);

            // 添加子配置项的超链接
            HyperlinkLabel myConfigLink = new HyperlinkLabel("MyConfig");
            myConfigLink.addHyperlinkListener(e -> {
                ShowSettingsUtil.getInstance().showSettingsDialog(null, "MyConfig");
            });
            mainPanel.add(myConfigLink);
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

    @NotNull
    @Override
    public String getId() {
        return "APIDocs";
    }

    @NotNull
    @Override
    public Configurable[] getConfigurables() {
        List<Configurable> configurables = new ArrayList<>();
        configurables.add(new MyConfigConfigurable());
        // 添加其他子配置项
        return configurables.toArray(new Configurable[0]);
    }
}
