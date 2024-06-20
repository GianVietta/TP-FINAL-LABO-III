package com.TpFinal.Interdafaces;

public interface IRepository <T>{
    public void add(T t);
    public void remove(T t);
    public void update(T t);
    public T find (T t);
}
