package org.example.task1;

import java.util.ArrayList;
import java.util.List;

public class TreeFinder {
    public static List<TreeNode> findGreater(TreeNode node, int n) {
        List<TreeNode> nodeList = new ArrayList<>();
        if (node.getValue() > n) {
            nodeList.add(node);
        }
        if (node.getChildren() != null) {
            node.getChildren().forEach(childNode -> nodeList.addAll(findGreater(childNode, n)));
        }
        return nodeList;
    }
}
