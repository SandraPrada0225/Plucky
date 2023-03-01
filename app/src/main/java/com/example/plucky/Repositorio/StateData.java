package com.example.plucky.Repositorio;

public class StateData <T>{

    private DataStatus status;


    private T data;


    private Throwable error;

    public StateData() {
        this.status = DataStatus.CREATED;
        this.data = null;
        this.error = null;
    }

    public StateData<T> loading() {
        this.status = DataStatus.LOADING;
        this.data = null;
        this.error = null;
        return this;
    }

    public StateData<T> success( T data) {
        this.status = DataStatus.SUCCESS;
        this.data = data;
        this.error = null;
        return this;
    }

    public StateData<T> error( Throwable error) {
        this.status = DataStatus.ERROR;
        this.data = null;
        this.error = error;
        return this;
    }

    public StateData<T> complete() {
        this.status = DataStatus.COMPLETE;
        return this;
    }


    public DataStatus getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }


    public Throwable getError() {
        return error;
    }

    public enum DataStatus {
        CREATED,
        SUCCESS,
        ERROR,
        LOADING,
        COMPLETE
    }
}