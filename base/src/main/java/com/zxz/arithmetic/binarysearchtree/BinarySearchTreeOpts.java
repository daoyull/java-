package com.zxz.arithmetic.binarysearchtree;

public class BinarySearchTreeOpts {

    private static BinarySearchTree root;


    public static void insert(int o) {
        if (root == null) {
            root = new BinarySearchTree(o);
            return;
        }
        BinarySearchTree pt = root;
        while (pt != null) {
            //如果当前节点的数值大于要插入的数值走左节点
            if (pt.getData() > o) {
                if (pt.getLeft() == null) {
                    pt.setLeft(new BinarySearchTree(o));
                    return;
                }
                pt = pt.getLeft();
            } else {
                if (pt.getRight() == null) {
                    pt.setRight(new BinarySearchTree(o));
                    return;
                }
                pt = pt.getRight();
            }
        }

    }


    public static void mid(BinarySearchTree binarySearchTree) {
        if (binarySearchTree == null) return;
        mid(binarySearchTree.getLeft());
        System.out.print(binarySearchTree.getData() + " ");
        mid(binarySearchTree.getRight());
    }

    public static BinarySearchTree find(int data) {
        BinarySearchTree pt = root;
        while (pt != null) {
            if (pt.getData() > data) {
                pt = pt.getLeft();
            } else if (pt.getData() < data) {
                pt = pt.getRight();
            } else {
                System.out.println("找到了");
                break;
            }
        }
        return pt;
    }

    public static BinarySearchTree findParent(int data) {
        BinarySearchTree pt = root;
        BinarySearchTree rea = null;
        while (pt != null) {
            if (pt.getData() > data) {
                rea = pt;
                pt = pt.getLeft();
            } else if (pt.getData() < data) {
                rea = pt;
                pt = pt.getRight();
            } else {
                break;
            }
        }
        return rea;
    }

    public static BinarySearchTree findMax(BinarySearchTree root) {
        while (root != null) {
            if (root.getRight() == null) {
                break;
            }
            root = root.getRight();
        }
        return root;
    }

    public static BinarySearchTree findMin(BinarySearchTree root) {
        while (root != null) {
            if (root.getLeft() == null) {
                break;
            }
            root = root.getLeft();
        }
        return root;
    }

    public static void remove(int ptOne) {
        BinarySearchTree pt = find(ptOne);
        if (pt == null) {
            System.out.println("您输入的数不存在");
            return;
        }
        BinarySearchTree one = root;
        if (pt.getLeft() == null && pt.getRight() == null) {
            BinarySearchTree parent = findParent(pt.getData());
            if (parent.getRight() == pt) {
                parent.setRight(null);
            } else {
                parent.setLeft(null);
            }
        } else if (pt.getLeft() == null && pt.getRight() != null) {
            BinarySearchTree parent = findParent(pt.getData());
            parent.setRight(pt.getRight());

        } else if (pt.getLeft() != null && pt.getRight() == null) {
            BinarySearchTree parent = findParent(pt.getData());
            parent.setLeft(pt.getLeft());
        } else {
            //removeToMin(pt);
            removeToMax(pt);
        }
    }

    protected static void removeToMax(BinarySearchTree pt) {
        BinarySearchTree parent = findParent(pt.getData());
        BinarySearchTree max = findMax(pt);
        BinarySearchTree maxParent = findParent(max.getData());
        if (parent.getLeft() == pt) {
            parent.setLeft(max);
            max.setLeft(pt.getLeft());
            if (pt.getRight() != max)
                max.setRight(pt.getRight());
            maxParent.setRight(null);
        } else {
            parent.setRight(max);
            max.setLeft(pt.getLeft());
            if (pt.getRight() != max)
                max.setRight(pt.getRight());
            maxParent.setRight(null);
        }
    }

    private static void removeToMin(BinarySearchTree pt) {

    }

    public static void main(String[] args) {
        int[] x = {100, 80, 200, 54, 90, 150, 210, 49};
        for (int i = 0; i < x.length; i++) {
            insert(x[i]);
        }
        mid(root);


        remove(49);

        mid(root);

    }


}
