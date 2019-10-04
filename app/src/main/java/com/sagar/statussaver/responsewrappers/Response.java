package com.sagar.statussaver.responsewrappers;

import androidx.annotation.NonNull;

import static com.sagar.statussaver.responsewrappers.Status.ERROR;
import static com.sagar.statussaver.responsewrappers.Status.LOADING;
import static com.sagar.statussaver.responsewrappers.Status.SUCCESS;

/**
 * Response holder provided to the UI
 */
public class Response<T> {

    private final Status status;

    private final T data;

    private final Throwable error;

    private Response(Status status, T data, Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public T getData() {
        return data;
    }

    @NonNull
    public Status getStatus() {
        return status;
    }

    public Throwable getError() {
        return error;
    }

    @NonNull
    public static <T> Response loading() {
        return new Response<T>(LOADING, null, null);
    }

    @NonNull
    public static <T> Response success(@NonNull T data) {
        return new Response<T>(SUCCESS, data, null);
    }

    @NonNull
    public static <T> Response error(@NonNull Throwable error) {
        return new Response<T>(ERROR, null, error);
    }

}
