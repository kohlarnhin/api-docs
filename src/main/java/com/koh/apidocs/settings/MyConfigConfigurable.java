package com.koh.apidocs.settings;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MyConfigConfigurable implements Configurable {
    private JPanel myConfigPanel;

    @Nls
    @Override
    public String getDisplayName() {
        return "MyConfig";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        if (myConfigPanel == null) {
            myConfigPanel = new JPanel();
            myConfigPanel.setLayout(new BoxLayout(myConfigPanel, BoxLayout.Y_AXIS));

            // 添加说明标签
            JLabel description = new JLabel("MyConfig 配置说明。");
            myConfigPanel.add(description);

            // 这里添加 MyConfig 的实际配置项
        }
        return myConfigPanel;
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
