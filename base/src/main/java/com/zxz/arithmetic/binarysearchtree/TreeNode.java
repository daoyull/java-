package com.zxz.arithmetic.binarysearchtree;

import javax.jws.Oneway;

public class TreeNode {
    private Object data;

    private TreeNode left;

    private TreeNode right;

    public TreeNode(Object data) {
        this.data = data;
    }

    public TreeNode(Object data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
