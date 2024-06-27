package com.koh.apidocs.configurable;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.labels.LinkListener;
import com.koh.apidocs.constants.IdConstants;
import com.koh.apidocs.constants.LabelConstants;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MyConfigConfigurable implements ExtensionConfigurable<Object> {

    private JPanel myConfigPanel;

    private static MyConfigConfigurable instance;

    // 公有静态方法，获取唯一实例
    public static synchronized MyConfigConfigurable getInstance() {
        if (instance == null) {
            instance = new MyConfigConfigurable();
        }
        return instance;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        System.out.println("MyConfigConfigurable createComponent");
        myConfigPanel = new JPanel();
        myConfigPanel.setLayout(new BoxLayout(myConfigPanel, BoxLayout.Y_AXIS));

        // 添加说明标签
        JLabel description = new JLabel("MyConfig 配置说明。");
        myConfigPanel.add(description);
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

    @Nls
    @Override
    public String getDisplayName() {
        return LabelConstants.MyConfig;
    }

    @Override
    public String getId() {
        return IdConstants.MyConfig;
    }
}
