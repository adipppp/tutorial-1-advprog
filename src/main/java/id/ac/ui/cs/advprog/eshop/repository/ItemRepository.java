package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;

public interface ItemRepository<T> {
    public T create(T item);
    public Iterator<T> findAll();
    public T findById(String id);
    public T update(T updatedItem);
    public T deleteById(String id);
}
