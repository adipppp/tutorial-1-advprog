package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;

public interface ItemService<T> {
    public T create(T object);
    public List<T> findAll();
    public T findById(String id);
    public T deleteById(String id);
    public T update(T updatedItem);
}
