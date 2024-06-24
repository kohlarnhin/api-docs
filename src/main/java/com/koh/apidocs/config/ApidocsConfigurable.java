package com.koh.apidocs.config;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurableEP;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import org.jetbrains.annotations.*;

import javax.swing.*;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ApidocsConfigurable implements Configurable {


    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "apidocs配置";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        // 返回配置面板的组件
        return new JPanel();
    }

    @Override
    public boolean isModified() {
        // 返回配置是否已修改
        return false;
    }

    @Override
    public void apply() {
        // 保存配置
    }

    @Override
    public void focusOn(@NotNull @Nls String label) {
        Configurable.super.focusOn(label);
    }
}


