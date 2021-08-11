package com.zxz.rbtree;


public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;

    private static final boolean BLACK = false;

    private RBNode root;

    public RBNode getRoot() {
        return root;
    }

    // 获取当前节点的父节点
    private RBNode parentOf(RBNode node) {
        if (node != null) {
            return node.parent;
        }
        return null;
    }

    //判断节点是否为红色
    private boolean isRed(RBNode node) {
        if (node != null) {
            return node.color == RED;
        }
        return false;
    }

    //判断是否为黑色
    private boolean isBlack(RBNode node) {
        if (node != null) {
            return node.color == BLACK;
        }
        return false;
    }

    //设置节点为红色
    private void setRed(RBNode node) {
        if (node != null) {
            node.color = RED;
        }
    }

    //设置节点为黑色
    private void setBlack(RBNode node) {
        if (node != null) {
            node.color = BLACK;
        }
    }

    //中序打印二叉树
    public void inOrderPrint() {
        inOrderPrint(this.root);
    }

    private void inOrderPrint(RBNode node) {
        if (node != null) {
            inOrderPrint(node.left);
            System.out.println("key" + node.key + " value" + node.value);
            inOrderPrint(node.right);
        }
    }

    //左旋
    private void leftRotate(RBNode x) {
        //将y的右子节点指向x的右子节点，并且更新x的左子节点的父节点为y
        RBNode y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        //当x的父节点不为空时，更新y的节点为x的父节点，更新y的父节点的指定子节点为x
        if (x.parent != null) {
            y.parent = x.parent;

            if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        } else {
            this.root = y;
        }
        //更新x的父节点为y，更新y的右节点为x
        x.parent = y;
        y.left = x;
    }


    //右旋
    private void rightRotate(RBNode x) {
        //将y的左子节点指向为x的右子节点，并且更新x的右子节点的父类为y
        RBNode y = x.left;
        x.left = y.right;

        if (y.right != null) {
            y.right.parent = x;
        }
        //当y的父节点不为空时，更新x的父节点为y的父节点，更新y的父节点指定子节点为x
        if (x.parent != null) {
            y.parent = x.parent;

            if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        } else {
            this.root = y;
        }
        //更新y的父节点为x，更新x的右子节点为y
        x.parent = y;
        y.right = x;


    }

    //插入
    public void insert(K key, V value) {
        RBNode<K, V> node = new RBNode<K, V>();
        node.setColor(RED);
        node.setKey(key);
        node.setValue(value);
        insert(node);
    }

    private void insert(RBNode node) {


        //查找父节点
        RBNode parent = null;
        RBNode x = this.root;
        if (x == null) {
            node.color = BLACK;
            this.root = node;
            return;

        }
        while (x != null) {
            parent = x;
            int cmp = node.key.compareTo(x.key);
            if (cmp > 0) {
                x = x.right;
            } else if (cmp == 0) {
                x.setValue(node.getValue());
                return;
            } else {
                x = x.left;
            }
        }


        node.parent = parent;

        int cmp = node.key.compareTo(parent.key);
        if (cmp > 0) {
            parent.right = node;
        } else {
            parent.left = node;
        }

        //修复红黑树平衡
        insertFixup(node);

    }

    /*
     * 插入后不需要修复的情况
     * 1.插入的key以存在
     * 2.插入节点的父节点为黑色
     *
     * 需要修复的情况
     * 1.空树
     * 2.插入节点的父节点为红色
     *   2.1 叔叔节点存在，并且为红色，将父叔节点都染色为黑色，将爷爷节点染色为红色，并以爷爷为当前节点进行下一轮处理
     *   2.2 叔叔节点不存在或者为黑色，父亲节点为爷爷节点的左子树
     *       2.2.1 插入节点为其父节点的左子节点（LL情况）将爸爸染色为黑色，将爷爷染色为红色，以爷爷右旋完成处理
     *       2.2.2 插入节点为其父节点的右子节点（LR情况）以爸爸节点进行一次左旋，得到LL情况，然后路进行2.2.1处理
     *   2.3 叔叔节点不存在或者为黑色，父亲节点为爷爷节点的右子树
     *       2.3.1 插入节点为其父节点的左子节点（RR情况）将爸爸染色为黑色，将爷爷染色为红色，以爷爷左旋完成处理
     *       2.3.2 插入节点为其父节点的右子节点（RL情况）以爸爸节点进行一次左旋，得到RR情况，然后路进行2.3.1处理
     *
     *
     * */
    private void insertFixup(RBNode node) {

        this.root.setColor(BLACK);

        RBNode parent = parentOf(node);
        RBNode gparent = parentOf(parent);


        if (parent != null && isRed(parent)) {
            RBNode uncle = null;

            if (parent == gparent.left) {
                if (gparent.right != null) {
                    uncle = gparent.right;

                }



                //2.1 叔叔节点存在且都为红色
                if (uncle != null && isRed(uncle)) {
                    //将爸爸和叔叔染色为黑色，爷爷为红色，以爷爷节点进行下一轮处理
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    insertFixup(gparent);
                    return;
                }

                //2.2 叔叔节点不存在或者为黑色
                if (uncle == null || isBlack(uncle)) {

                    if (node == parent.left) {
                        setBlack(parent);
                        setRed(gparent);
                        rightRotate(gparent);
                        return;
                    }

                    if (node == parent.right) {
                        leftRotate(parent);
                        insertFixup(parent);
                        return;
                    }
                }


            } else {

                if(gparent.left!=null){
                    uncle = gparent.left;
                }



                //2.1 叔叔节点存在且都为红色
                if (uncle != null && isRed(uncle)) {
                    //将爸爸和叔叔染色为黑色，爷爷为红色，以爷爷节点进行下一轮处理
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    insertFixup(gparent);
                    return;
                }

                //
                if (uncle == null || isBlack(uncle)) {
                    if (node == parent.right) {
                        setBlack(parent);
                        setBlack(gparent);
                        leftRotate(gparent);
                        return;
                    }
                }

                //
                if (node == parent.left) {
                    rightRotate(parent);
                    insertFixup(parent);
                    return;
                }


            }


        }

    }


    static class RBNode<K extends Comparable<K>, V> {
        private RBNode parent;
        private RBNode left;
        private RBNode right;
        private boolean color;
        private K key;
        private V value;

        public RBNode() {
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public RBNode(RBNode parent, RBNode left, RBNode right, boolean color, K key, V value) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
            this.key = key;
            this.value = value;
        }
    }
}
