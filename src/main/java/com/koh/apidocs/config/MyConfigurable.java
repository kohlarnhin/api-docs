package com.koh.apidocs.config;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MyConfigurable implements Configurable {

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "myconfig配置";
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

}


