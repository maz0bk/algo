import java.util.Arrays;
import java.util.Objects;

public class ArrayImpl<E extends Object & Comparable<? super E>> implements Array<E> {
    private static final int DEFAULT_CAPACITY = 16;
    protected static final int INVALID_INDEX = -1;
    protected E [] data;
    protected int currentSize;

    public ArrayImpl(int initialCapacity) {
        this.data = (E[]) new Object[initialCapacity];
    }

    public ArrayImpl() {
        this(DEFAULT_CAPACITY);

    }

    @Override
    public E get(int index) {
        return data[index];
    }

    @Override
    public void sortBubble() {
        for (int i = 0; i < currentSize - 1; i++) {
            for (int j = 0; j < currentSize - 1 - i; j++) {
                if (data[j].compareTo(data[j+1]) > 0){
                    exchange(j,j+1);
                }
            }
        }
    }

    private void exchange(int index1, int index2) {
        E temp = data[index1];
        data[index1]= data[index2];
        data[index2]= temp;
    }

    @Override
    public void sortSelect() {
        for (int i = 0; i < currentSize; i++) {
            int minIndex= i;
            for (int j = i+1; j < currentSize; j++) {
                if (data[j].compareTo(data[i]) < 0){
                    minIndex = j;
                }
            }
            exchange(minIndex,i);

        }

    }

    @Override
    public void sortInsert() {
        for (int i = 1; i < currentSize; i++) {
            E temp = data[i];
            int in = i;
            while (in > 0 && data[in-1].compareTo(temp) >= 0){
                data[in] = data[in -1];
                in--;
            }
            data[in]=temp;
        }
    }

    @Override
    public void add(E value) {
        checkGrow();
        this.data[currentSize++] = value;
    }

    protected void checkGrow() {
        if(currentSize < data.length)
            return;
        data = Arrays.copyOf(data,data.length*2);
    }

    @Override
    public boolean remove(E value) {
        int index = indexOf(value);
        if (index == INVALID_INDEX) return false;
        remove(index);
        return true;
    }

    @Override
    public void remove(int index) {
        Objects.checkIndex(index,currentSize);
        currentSize--;
        for (int i = index; i <currentSize ; i++) {
            data[i]=data[i+1];
        }
        data[currentSize] = null;
    }

    @Override
    public boolean contains(E value) {
        return indexOf(value) != INVALID_INDEX;
    }

    @Override
    public int indexOf(E value) {
        for (int i = 0; i < currentSize; i++) {
            if (data[i].equals( value))
                return i;

        }
        return INVALID_INDEX;
    }

    @Override
    public int getSize() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize==0;
    }
}
