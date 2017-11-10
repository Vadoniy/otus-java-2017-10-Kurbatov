import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class MyArrayList<E> implements List<E> {

    private int size;
    private E[] array;

    public MyArrayList(E[] elements){
        this.size = elements.length;
        this.array = elements;
    }

    public MyArrayList(){}

    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contains(Object o) {
        throw new NotImplementedException();
    }

    public Iterator<E> iterator() {
        return listIterator();
    }

    public Object[] toArray() {
        return Arrays.copyOf(array, array.length);
    }

    public <T1> T1[] toArray(T1[] a) {
        throw new NotImplementedException();
    }

    public boolean add(E e) {
        if (!(array.length < size)){
            Object[] biggerArray = new Object[array.length*2];
            int i = 0;
            for (Object obj : array) {
                biggerArray[i] = obj;
                i++;
            }
            array = (E[]) biggerArray;
        }

        array[size] = e;
        size++;
        return true;
    }

    public boolean remove(Object o) {
        throw new NotImplementedException();
    }

    public boolean containsAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    public boolean addAll(Collection<? extends E> c) {
        throw new NotImplementedException();
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        throw new NotImplementedException();
    }

    public boolean addAll(Collection<? super E> c, E... elements) {
        boolean result = false;

        for (E e : elements) {
            result = add(e);
        }

        return result;
    }

    public boolean removeAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    public boolean retainAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    public void clear() {
        throw new NotImplementedException();
    }

    public E get(int index) {
        return array[index];
    }

    public E set(int index, E element) {
        if (index>size || index < 0){
            throw new IndexOutOfBoundsException("Wrong index of argument.");
        }
        array[index] = element;
        E localElement = array[index];
        return localElement;
    }

    public void add(int index, E element) {
        throw new NotImplementedException();
    }

    public E remove(int index) {
        throw new NotImplementedException();
    }

    public int indexOf(Object o) {
        throw new NotImplementedException();
    }

    public int lastIndexOf(Object o) {
        throw new NotImplementedException();
    }

    public MyListIterator<E> listIterator() {
        return new MyListIterator<E>(this);
    }

    public ListIterator<E> listIterator(int index) {
        throw new NotImplementedException();
    }

    public List<E> subList(int fromIndex, int toIndex) {
        throw new NotImplementedException();
    }
}
