package org.example.taskify.controller;

import jakarta.validation.Valid;

import org.example.taskify.dto.request.TodoCreationRequest;
import org.example.taskify.dto.request.TodoUpdateRequest;
import org.example.taskify.dto.response.ApiResponse;
import org.example.taskify.dto.response.PageResponse;
import org.example.taskify.dto.response.TodoResponse;
import org.example.taskify.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TodoController {
    TodoService todoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<TodoResponse> createTodo(@RequestBody @Valid TodoCreationRequest request) {
        return ApiResponse.success(201, todoService.createTodo(request));
    }

    @GetMapping("/{todoId}")
    public ApiResponse<TodoResponse> getTodo(@PathVariable String todoId) {
        return ApiResponse.success(200, todoService.getTodo(todoId));
    }

    @GetMapping
    public ApiResponse<PageResponse<TodoResponse>> getTodos(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return ApiResponse.success(200, todoService.getTodos(page, size));
    }

    @PutMapping("/{todoId}")
    public ApiResponse<TodoResponse> updateTodo(
            @PathVariable String todoId, @RequestBody @Valid TodoUpdateRequest request) {
        return ApiResponse.success(200, todoService.updateTodo(todoId, request));
    }

    @DeleteMapping("/{todoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable String todoId) {
        todoService.deleteTodo(todoId);
    }
}
