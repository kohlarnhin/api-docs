package com.koh.apidocs.enums;

import com.intellij.ui.components.labels.LinkLabel;
import com.koh.apidocs.constants.IdConstants;
import com.koh.apidocs.constants.LabelConstants;
import lombok.Getter;

/**
 * 枚举
 * LinkLabelEntity里面的类型用Object
 * 用户存放LinkLabel的id的对应关系
 */
@Getter
public enum LinkLabelAndIdEnum {

    /**
     * MyConfig
     */
    MY_CONFIG(IdConstants.MyConfig, new LinkLabel<>(LabelConstants.MyConfig, null, null, null))
    ;

    private final String configId;
    private final LinkLabel<Object> linkLabel;

    LinkLabelAndIdEnum(String configId, LinkLabel<Object> linkLabelEntity) {
        this.configId = configId;
        this.linkLabel = linkLabelEntity;
    }
}
