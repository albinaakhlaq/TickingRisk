package com.alb.service;

public interface UpdateHandler<T> {
    void on_update(T data);
}
