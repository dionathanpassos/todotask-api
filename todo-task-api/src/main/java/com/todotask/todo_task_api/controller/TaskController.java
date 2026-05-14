package com.todotask.todo_task_api.controller;

import com.todotask.todo_task_api.dto.task.TaskRequestDTO;
import com.todotask.todo_task_api.dto.task.TaskResponseDTO;
import com.todotask.todo_task_api.dto.task.TaskUpdateRequestDTO;
import com.todotask.todo_task_api.model.Task;
import com.todotask.todo_task_api.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> create(@RequestBody @Valid TaskRequestDTO requestDTO) {
        TaskResponseDTO created = taskService.create(requestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public ResponseEntity<Page<TaskResponseDTO>> findAll(@PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<TaskResponseDTO> tasks = taskService.findAll(pageable);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> findById(@PathVariable  Long id) {
        TaskResponseDTO task = taskService.findById(id);

        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> update(@PathVariable Long id, @RequestBody @Valid TaskUpdateRequestDTO requestDTO) {
        TaskResponseDTO updated = taskService.update(id, requestDTO);

        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<TaskResponseDTO> completeTask(@PathVariable Long id) {
        TaskResponseDTO completed = taskService.completeTask(id);
        return ResponseEntity.ok(completed);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
