public interface Array<E extends Comparable<? super E>> {
    void add(E value);

    boolean remove(E value);
    void remove(int index);

    boolean contains(E value);
    int indexOf(E value);

    int getSize();
    boolean isEmpty();

    E get(int index);

    void sortBubble();
    void sortSelect();
    void sortInsert();

}
