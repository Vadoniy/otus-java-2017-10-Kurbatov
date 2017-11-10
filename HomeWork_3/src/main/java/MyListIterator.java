import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ListIterator;

public class MyListIterator<E> implements ListIterator<E> {

    private MyArrayList<E> myArrayList;
    private int current = 0;

    public MyListIterator(MyArrayList<E> myArrayList) {
        this.myArrayList = myArrayList;
    }

    @Override
    public boolean hasNext() {
        return current == myArrayList.size() ? false : true;
    }

    @Override
    public E next() {
        return myArrayList.get(current++);
    }

    @Override
    public boolean hasPrevious() {  throw new NotImplementedException();    }

    @Override
    public E previous() {  throw new NotImplementedException();    }

    @Override
    public int nextIndex() {    throw new NotImplementedException();    }

    @Override
    public int previousIndex() {    throw new NotImplementedException();    }

    @Override
    public void remove() {
        throw new NotImplementedException();
    }

    @Override
    public void set(E e) {
        /*myArrayList.set(--current, e); //<<<-------I was surprised, how different this 2 variants.
        I knew, that -- reduce field itself, but I didn't think about it)) */
        myArrayList.set(current-1, e);
    }

    public void add(Object o) {
        throw new NotImplementedException();
    }
}
