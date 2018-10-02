package com.quicktutorialz.jax.services;

import com.quicktutorialz.jax.entities.Todo;

public class TodoServiceImpl implements TodoService{

    private static TodoService instance = null;
    public synchronized static TodoService getInstance(){
        if(instance == null){
            instance = new TodoServiceImpl();
        }
        return instance;
    }

    @Override
    public Todo create(Todo todo) {
        return todo;
    }
}
