package org.example.taskify.service;

import org.example.taskify.dto.request.TodoCreationRequest;
import org.example.taskify.dto.request.TodoUpdateRequest;
import org.example.taskify.dto.response.PageResponse;
import org.example.taskify.dto.response.TodoResponse;

public interface TodoService {
    TodoResponse createTodo(TodoCreationRequest request);

    TodoResponse getTodo(String todoId);

    PageResponse<TodoResponse> getTodos(int page, int size);

    TodoResponse updateTodo(String todoId, TodoUpdateRequest request);

    void deleteTodo(String todoId);
}
