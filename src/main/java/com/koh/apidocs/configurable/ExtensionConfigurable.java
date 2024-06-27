package com.koh.apidocs.configurable;

import com.intellij.ide.DataManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.options.ex.ConfigurableExtensionPointUtil;
import com.intellij.openapi.options.ex.Settings;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.labels.LinkLabel;
import com.intellij.ui.components.labels.LinkListener;

import javax.swing.*;
import java.util.List;

/**
 * 扩展配置接口
 * 这里提供自定义的方法可供其他地方获取使用
 * 原先的Configurable 接口提供的方法有限，这里提供更多的方法
 */
public interface ExtensionConfigurable<T> extends Configurable {

    /**
     * 获取id
     * 这个是每个configurable的Id
     *
     * @return
     */
    String getId();

    /**
     * 获取label的text
     * 默认是displayName
     *
     * @return
     */
    default String getLabelText() {
        return this.getDisplayName();
    }

    /**
     * 获取icon
     * 默认是null
     * 如果需要icon，可以重写这个方法
     *
     * @return
     */
    default Icon getIcon() {
        return null;
    }


    /**
     * 获取LinkListener
     * 如果需要特殊处理，可以重写这个方法
     *
     * @return
     */
    default LinkListener<T> getLinkListener() {
        return (aSource, aLinkData) -> DataManager.getInstance().getDataContextFromFocusAsync().onSuccess(dataContext -> {
            Settings settings = Settings.KEY.getData(dataContext);
            if (settings != null) {
                settings.select(this);
            }
        });
    }

    /**
     * 获取LinkData
     * 默认是null
     * 如果需要传递参数，可以重写这个方法
     *
     * @return
     */
    default T getLinkData() {
        return null;
    }

    /**
     * 例如我在A页面里面需要一个可用于跳转到B页面的label
     * 这里就是用来指定这个label的
     * 建议所有的configurable都实现这个接口并实现这个方法提供一个LinkLabel
     * 这样其他页面就可以通过这个方法获取到这个label用于跳转
     * <p>
     * 这里将提供默认方法
     * 如果不需要特殊处理，可以直接使用默认方法
     * 无论是否使用默认方法，都需要实现上面的获取参数的方法
     *
     * @return
     */
    default LinkLabel<T> getLinkLabel() {
        return new LinkLabel<>(getLabelText(), getIcon(), getLinkListener(), getLinkData());
    }
}
