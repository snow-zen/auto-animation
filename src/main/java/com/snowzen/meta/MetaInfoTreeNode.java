package com.snowzen.meta;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * 元数据树节点。
 *
 * @author snow-zen
 */
@Getter
public class MetaInfoTreeNode {

    private final MetaInfo data;

    private final List<MetaInfoTreeNode> children = new ArrayList<>();

    public MetaInfoTreeNode(MetaInfo data) {
        this.data = data;
    }

    public void addChild(MetaInfoTreeNode child) {
        children.add(child);
    }
}
