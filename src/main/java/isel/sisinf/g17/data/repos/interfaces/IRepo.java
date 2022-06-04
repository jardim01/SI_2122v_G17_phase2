package isel.sisinf.g17.data.repos.interfaces;

import java.util.List;

public interface IRepo<K, T> {
    T findByKey(K key);

    void add(T entity);

    void remove(T entity);

    <R> List<R> find(String jpql, Object... params);
}
