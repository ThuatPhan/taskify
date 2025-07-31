package org.example.taskify.service;

import java.util.HashSet;
import java.util.Set;

import org.example.taskify.dto.request.TodoCreationRequest;
import org.example.taskify.dto.request.TodoUpdateRequest;
import org.example.taskify.dto.response.PageResponse;
import org.example.taskify.dto.response.TodoResponse;
import org.example.taskify.entity.Todo;
import org.example.taskify.entity.User;
import org.example.taskify.exception.AppException;
import org.example.taskify.exception.ErrorCode;
import org.example.taskify.mapper.TodoMapper;
import org.example.taskify.repository.TagRepository;
import org.example.taskify.repository.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TodoServiceImpl implements TodoService {
    UserService userService;
    TodoRepository todoRepository;
    TagRepository tagRepository;
    TodoMapper todoMapper;

    @PreAuthorize("isAuthenticated()")
    @Override
    public TodoResponse createTodo(TodoCreationRequest request) {
        Todo newTodo = todoMapper.toEntity(request);

        resolveTags(newTodo, request.getTags());

        User user = userService.getCurrentUser();
        newTodo.setCreatedBy(user);

        return todoMapper.toResponse(todoRepository.save(newTodo));
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public TodoResponse getTodo(String todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new AppException(ErrorCode.TODO_NOT_FOUND));

        validateTodoOwner(todo);

        return todoMapper.toResponse(todo);
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public PageResponse<TodoResponse> getTodos(int page, int size) {
        User user = userService.getCurrentUser();
        Page<Todo> todosPage = todoRepository.findAllByCreatedBy(user, PageRequest.of(page, size));

        return PageResponse.of(todosPage.hasNext(), todoMapper.toResponseList(todosPage.getContent()));
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public TodoResponse updateTodo(String todoId, TodoUpdateRequest request) {
        Todo todoToUpdate =
                todoRepository.findById(todoId).orElseThrow(() -> new AppException(ErrorCode.TODO_NOT_FOUND));

        validateTodoOwner(todoToUpdate);
        resolveTags(todoToUpdate, request.getTags());

        todoMapper.partialUpdate(todoToUpdate, request);
        return todoMapper.toResponse(todoRepository.save(todoToUpdate));
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public void deleteTodo(String todoId) {
        Todo todoToDelete =
                todoRepository.findById(todoId).orElseThrow(() -> new AppException(ErrorCode.TODO_NOT_FOUND));

        validateTodoOwner(todoToDelete);

        todoRepository.deleteById(todoId);
    }

    private void validateTodoOwner(Todo todo) {
        User user = userService.getCurrentUser();
        if (!todo.getCreatedBy().equals(user)) {
            throw new AppException(ErrorCode.ACCESS_DENIED);
        }
    }

    private void resolveTags(Todo todo, Set<String> tags) {
        todo.setTags(new HashSet<>(tagRepository.findAllByNameInIgnoreCase(tags)));
    }
}
