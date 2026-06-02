package com.todotask.todo_task_api.mapper;

import com.todotask.todo_task_api.dto.task.TaskRequestDTO;
import com.todotask.todo_task_api.dto.task.TaskResponseDTO;
import com.todotask.todo_task_api.dto.task.TaskUpdateRequestDTO;
import com.todotask.todo_task_api.model.Task;
import com.todotask.todo_task_api.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskMapper {

    public Task toEntity(TaskRequestDTO requestDTO, User user) {
        Task task = new Task();

        task.setTitle(requestDTO.title());
        task.setDescription(requestDTO.description());
        task.setUser(user);

        return task;
    }

    public TaskResponseDTO fromEntity(Task task) {
       return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted(),
                task.getCreatedAt(),
                task.isActive()

        );

    }
    public List<TaskResponseDTO> fromEntity(List<Task> tasks) {
        return tasks.stream().
                map(this::fromEntity).
                toList();
    }

    public Task updateToEntity(TaskUpdateRequestDTO requestDTO, Task task) {

        if(requestDTO.title() != null) {
            task.setTitle(requestDTO.title());
        }

        if(requestDTO.description() != null) {
            task.setDescription(requestDTO.description());
        }

        return task;
    }
}
