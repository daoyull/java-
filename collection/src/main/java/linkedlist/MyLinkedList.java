package linkedlist;

/**
 * LinkedList 类实现了双向链表.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {
    /**
     * 构造一个空的 LinkedList.
     */
    public MyLinkedList() {
        doClear();
    }

    private void clear() {
        doClear();
    }

    /**
     * 将此集合的大小更改为零.
     */
    public void doClear() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    /**
     * 返回此集合中的项数.
     */
    public int size() {
        return theSize;
    }

    /**
     * 返回此集合是否为空.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 在该集合的最后添加一个项.
     *
     * @param x 可以实任何对象.
     * @return true.
     */
    public boolean add(AnyType x) {
        add(size(), x);
        return true;
    }

    /**
     * 在指定位置向该集合添加一个项.
     * 位于该位置或之后的项向后移动一个位置.
     *
     * @param x   任何对象.
     * @param idx 添加位置.
     * @throws IndexOutOfBoundsException 如果 idx 不在 0 和 size() 之间.
     */
    public void add(int idx, AnyType x) {
        addBefore(getNode(idx, 0, size()), x);
    }

    /**
     * 在指定位置 p 向该集合添加一个项.
     * 位于该位置或之后的项向后移动一个位置.
     *
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException 如果 idx 不在 0 和 size() ，之间包括 0 和 size( ) - 1.
     */
    private void addBefore(Node<AnyType> p, AnyType x) {
        Node<AnyType> newNode = new Node<>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }


    /**
     * 返回位置 idx 处的项目。
     *
     * @param idx 要搜索的索引。
     * @throws IndexOutOfBoundsException 如果索引超出范围。
     */
    public AnyType get(int idx) {
        return getNode(idx).data;
    }

    /**
     * 更改位置 idx 处的项目。
     *
     * @param idx    要更改的索引。
     * @param newVal 新值。
     * @return 旧值。
     * @throws IndexOutOfBoundsException 如果索引超出范围。
     */
    public AnyType set(int idx, AnyType newVal) {
        Node<AnyType> p = getNode(idx);
        AnyType oldVal = p.data;

        p.data = newVal;
        return oldVal;
    }

    /**
     * 获取位置 idx 处的节点，其范围必须从 0 到 size( ) - 1。
     *
     * @param idx 要搜索的索引。
     * @return idx对应的内部节点。
     * @throws IndexOutOfBoundsException 如果 idx 不在 0 和 size( ) - 1 之间，包括 0 和 size( ) - 1。
     */
    private Node<AnyType> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    /**
     * 获取位置 idx 处的节点，其范围必须从低到高。
     *
     * @param idx   要搜索的索引。
     * @param lower 最低有效索引。
     * @param upper 最高有效指数。
     * @return idx对应的内部节点。
     * @throws IndexOutOfBoundsException 如果 idx 不在下限和上限之间，包括两者。
     */
    private Node<AnyType> getNode(int idx, int lower, int upper) {
        Node<AnyType> p;

        if (idx < lower || idx > upper)
            throw new IndexOutOfBoundsException("getNode index: " + idx + "; size: " + size());

        if (idx < size() / 2) {
            p = beginMarker.next;
            for (int i = 0; i < idx; i++)
                p = p.next;
        } else {
            p = endMarker;
            for (int i = size(); i > idx; i--)
                p = p.prev;
        }

        return p;
    }

    /**
     * 从此集合中删除一个项目。
     *
     * @param idx 对象的索引。
     * @return 该项目已从集合中删除。
     */
    public AnyType remove(int idx) {
        return remove(getNode(idx));
    }

    /**
     * 删除节点 p 中包含的对象。
     *
     * @param p 包含对象的节点。
     * @return 该项目已从集合中删除。
     */
    private AnyType remove(Node<AnyType> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;

        return p.data;
    }

    /**
     * 返回此集合的字符串表示形式。
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");

        for (AnyType x : this)
            sb.append(x + " ");
        sb.append("]");

        return new String(sb);
    }

    /**
     * 获取用于遍历集合的 Iterator 对象。
     *
     * @return 定位在第一个元素之前的迭代器。
     */
    public java.util.Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }


    /**
     * 这是 LinkedListIterator 的实现。
     * 它维护当前位置的概念，当然还有对 MyLinkedList 的隐式引用。
     */

    private class LinkedListIterator implements java.util.Iterator<AnyType> {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        public boolean hasNext() {
            return current != endMarker;
        }

        public AnyType next() {
            if (modCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if (!hasNext())
                throw new java.util.NoSuchElementException();

            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove() {
            if (modCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if (!okToRemove)
                throw new IllegalStateException();

            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }
    }

    /**
     * 这是双向链表节点。
     */
    private static class Node<AnyType> {
        public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
            data = d;
            prev = p;
            next = n;
        }

        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;
    }

    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
}



