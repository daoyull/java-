package com.zxz.arraylist;

public class MyArrayList<AnyType> implements Iterable<AnyType>
{
    /**
     * 构造一个空的 ArrayList。
     */
    public MyArrayList( )
    {
        doClear( );
    }

    /**
     * 返回此集合中的项数。
     * @return 此集合中的项数。
     */
    public int size( )
    {
        return theSize;
    }

    /**
     * 如果此集合为空，则返回 true。
     * @return 如果此集合为空，则为 true。
     */
    public boolean isEmpty( )
    {
        return size( ) == 0;
    }

    /**
     * 返回位置 idx 处的项。
     * @param idx 要搜索的索引。
     * @throws ArrayIndexOutOfBoundsException 如果索引超出范围。
     */
    public AnyType get( int idx )
    {
        if( idx < 0 || idx >= size( ) )
            throw new ArrayIndexOutOfBoundsException( "Index " + idx + "; size " + size( ) );
        return theItems[ idx ];
    }

    /**
     * 更改位置 idx 处的项。
     * @param idx 要更改的索引。
     * @param newVal 新值。
     * @return 旧值。
     * @throws ArrayIndexOutOfBoundsException 如果索引超出范围。
     */
    public AnyType set( int idx, AnyType newVal )
    {
        if( idx < 0 || idx >= size( ) )
            throw new ArrayIndexOutOfBoundsException( "Index " + idx + "; size " + size( ) );
        AnyType old = theItems[ idx ];
        theItems[ idx ] = newVal;

        return old;
    }

    @SuppressWarnings("unchecked")
    public void ensureCapacity( int newCapacity )
    {
        if( newCapacity < theSize )
            return;

        AnyType [ ] old = theItems;
        theItems = (AnyType []) new Object[ newCapacity ];
        for( int i = 0; i < size( ); i++ )
            theItems[ i ] = old[ i ];
    }

    /**
     * 最后向该集合添加一个项。
     * @param x 任何对象.
     * @return true.
     */
    public boolean add( AnyType x )
    {
        add( size( ), x );
        return true;
    }

    /**
     * 在指定的索引处向此集合添加一个项。
     * @param x 任何对象。
     * @return true.
     */
    public void add( int idx, AnyType x )
    {
        if( theItems.length == size( ) )
            ensureCapacity( size( ) * 2 + 1 );

        for( int i = theSize; i > idx; i-- )
            theItems[ i ] = theItems[ i - 1 ];

        theItems[ idx ] = x;
        theSize++;
    }

    /**
     * 从此集合中删除一个项。
     * @param idx 对象的索引。
     * @return 该索引的值。
     */
    public AnyType remove( int idx )
    {
        AnyType removedItem = theItems[ idx ];

        for( int i = idx; i < size( ) - 1; i++ )
            theItems[ i ] = theItems[ i + 1 ];
        theSize--;

        return removedItem;
    }

    /**
     * 将此集合的大小更改为零。
     */
    public void clear( )
    {
        doClear( );
    }

    private void doClear( )
    {
        theSize = 0;
        ensureCapacity( DEFAULT_CAPACITY );
    }

    /**
     * 获取用于遍历集合的 Iterator 对象。
     * @return 定位在第一个元素之前的迭代器。
     */
    public java.util.Iterator<AnyType> iterator( )
    {
        return new ArrayListIterator( );
    }

    /**
     * 返回此集合的字符串表示形式。
     */
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( "[ " );

        for( AnyType x : this )
            sb.append( x + " " );
        sb.append( "]" );

        return new String( sb );
    }

    /**
     * 这是 ArrayListIterator 的实现。
     * 它维护当前位置的概念和
     * 对 MyArrayList 的隐式引用。
     */
    private class ArrayListIterator implements java.util.Iterator<AnyType>
    {
        private int current = 0;
        private boolean okToRemove = false;

        public boolean hasNext( )
        {
            return current < size( );
        }


        public AnyType next( )
        {
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( );

            okToRemove = true;
            return theItems[ current++ ];
        }

        public void remove( )
        {
            if( !okToRemove )
                throw new IllegalStateException( );

            MyArrayList.this.remove( --current );
            okToRemove = false;
        }
    }

    private static final int DEFAULT_CAPACITY = 10;

    private AnyType [ ] theItems;
    private int theSize;
}

