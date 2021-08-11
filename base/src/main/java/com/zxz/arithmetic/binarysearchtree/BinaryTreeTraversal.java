package com.zxz.arithmetic.binarysearchtree;

import com.sun.corba.se.impl.orbutil.ObjectStreamClassUtil_1_3;

public class BinaryTreeTraversal {

    private static void pre(TreeNode treeNode) {
        if (treeNode == null) return;

        System.out.print(treeNode.getData() + " ");
        pre(treeNode.getLeft());
        pre(treeNode.getRight());

    }


    private static void mid(TreeNode treeNode) {
        if (treeNode == null) return;
        mid(treeNode.getLeft());
        System.out.print(treeNode.getData() + " ");
        mid(treeNode.getRight());

    }

    private static void later(TreeNode treeNode) {

        if (treeNode == null) return;
        later(treeNode.getLeft());
        later(treeNode.getRight());
        System.out.print(treeNode.getData() + " ");

    }


    public static void main(String[] args) {
        TreeNode a = new TreeNode("A");
        TreeNode b = new TreeNode("B");
        TreeNode c = new TreeNode("C");
        TreeNode d = new TreeNode("D");
        TreeNode e = new TreeNode("E");
        TreeNode f = new TreeNode("F");
        TreeNode g = new TreeNode("G");
        TreeNode h = new TreeNode("H");

        a.setLeft(b);
        a.setRight(c);
        b.setRight(e);
        b.setLeft(d);
        d.setLeft(h);
        c.setLeft(f);
        c.setRight(g);

        pre(a);
        System.out.println();
        mid(a);
        System.out.println();
        later(a);


    }


}
