package com.tp.Interfaces;

public interface IRepository<T> {
    void add(T t);
    T consult(Integer id);
    void remove(T t);
    void update(T t);

}
