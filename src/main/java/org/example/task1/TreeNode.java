package org.example.task1;

import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TreeNode {
    @NonNull
    private int value;
    private List<TreeNode> children;
}
