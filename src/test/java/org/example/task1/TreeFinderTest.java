package org.example.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.example.task1.TreeFinder.findGreater;

public class TreeFinderTest {

    @Test
    public void givenTreeNode_whenFindGreater_thenResult() {
        List<TreeNode> lowerThan5List = List.of(
                new TreeNode(4, null),
                new TreeNode(3, null),
                new TreeNode(2, null),
                new TreeNode(1, null)
        );
        List<TreeNode> between5And15List = List.of(
                new TreeNode(7, null),
                new TreeNode(8, null),
                new TreeNode(10, null)
        );
        List<TreeNode> greaterThan15List = List.of(
                new TreeNode(17, null),
                new TreeNode(20, null),
                new TreeNode(30, null),
                new TreeNode(50, null)
        );
        TreeNode root = createTestTree(lowerThan5List, between5And15List, greaterThan15List);

        var firstList = findGreater(root, 15);
        Assertions.assertIterableEquals(greaterThan15List, firstList);

        List<TreeNode> greaterThan5List = new ArrayList<>();
        greaterThan5List.addAll(between5And15List);
        greaterThan5List.addAll(greaterThan15List);

        var secondList = findGreater(root, 5);
        Assertions.assertEquals(greaterThan5List.size(), secondList.size());
        Assertions.assertTrue(secondList.containsAll(greaterThan5List));

        var thirdList = findGreater(root, 100);
        Assertions.assertIterableEquals(Collections.emptyList(), thirdList);
    }

    private static TreeNode createTestTree(List<TreeNode>... lists) {
        TreeNode root = new TreeNode(2);
        List<TreeNode> nodeList = new ArrayList<>();
        int maxNodes = Arrays.stream(lists).max((x, y) -> {
            if (x.size() == y.size()) {
                return 0;
            }
            return x.size() > y.size() ? 1 : 0;
        }).get().size();
        // Равномерно распределяем по дереву ноды из списков
        for (int i = 0; i < maxNodes; i++) {
            List<TreeNode> childList = new ArrayList<>();
            for (List<TreeNode> list : lists) {
                if (list.size() <= i) {
                    continue;
                }
                childList.add(list.get(i));
            }
            nodeList.add(new TreeNode(1, childList));
        }
        root.setChildren(nodeList);

        return root;
    }
}
