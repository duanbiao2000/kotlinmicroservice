package io.kang.chapter01


class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class Solution {
    // 合并两棵二叉树
    fun mergeTrees(t1: TreeNode?, t2: TreeNode?): TreeNode? {
        // 如果两棵树都为空，则返回空
        if(t1 == null && t2 == null)
            return null

        // 创建一个新的节点，值为两棵树对应节点的值之和
        val result = TreeNode(0)
        result.`val` = (t1?.`val`?: 0) + (t2?.`val`?: 0)
        // 递归合并左子树
        result.left = mergeTrees(t1?.left, t2?.left)
        // 递归合并右子树
        result.right = mergeTrees(t1?.right, t2?.right)
        // 返回合并后的树
        return result
    }
}