package br.net.twome.fipe.service;

public interface ServiceCallback<T> {
    void onSuccess(T obj);
}