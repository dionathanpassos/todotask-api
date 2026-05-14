package com.todotask.todo_task_api.service;

import com.todotask.todo_task_api.dto.task.TaskRequestDTO;
import com.todotask.todo_task_api.dto.task.TaskResponseDTO;
import com.todotask.todo_task_api.dto.task.TaskUpdateRequestDTO;
import com.todotask.todo_task_api.exception.BusinessException;
import com.todotask.todo_task_api.exception.ResourceNotFoundException;
import com.todotask.todo_task_api.mapper.TaskMapper;
import com.todotask.todo_task_api.model.Task;
import com.todotask.todo_task_api.model.User;
import com.todotask.todo_task_api.repository.TaskRepository;
import com.todotask.todo_task_api.security.AuthenticatedUserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final AuthenticatedUserService authenticatedUserService;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(AuthenticatedUserService authenticatedUserService, TaskRepository taskRepository, TaskMapper taskMapper) {
        this.authenticatedUserService = authenticatedUserService;
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Transactional
    public TaskResponseDTO create(TaskRequestDTO requestDTO) {
        User user = authenticatedUserService.getAuthenticatedUser();

        if(taskRepository.existsByTitleIgnoreCaseAndCompletedAndUser(requestDTO.title(), false, user)) {
            throw new BusinessException("Existe uma mesma tarefa pendente com o mesmo título já cadastrada");
        }

        Task task = taskMapper.toEntity(requestDTO, user);
        Task saved = taskRepository.save(task);

        return taskMapper.fromEntity(saved);
    }

    public Page<TaskResponseDTO> findAll(Pageable pageable) {
        User user = authenticatedUserService.getAuthenticatedUser();

        return taskRepository.findAllByUser(user, pageable).map(taskMapper::fromEntity);
    }

    public TaskResponseDTO findById(Long id) {
        User user = authenticatedUserService.getAuthenticatedUser();

        Task task = taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));

        return taskMapper.fromEntity(task);
    }

    @Transactional
    public TaskResponseDTO update(Long id, TaskUpdateRequestDTO requestDTO) {
        User user = authenticatedUserService.getAuthenticatedUser();

        Task task = taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));

        if(task.isCompleted()) {
            throw new BusinessException("Tarefa já finalizada, não é possível alterar");
        }

        task = taskMapper.updateToEntity(requestDTO, task);
        Task saved = taskRepository.save(task);

        return taskMapper.fromEntity(saved);
    }

    @Transactional
    public TaskResponseDTO completeTask(Long id) {
        User user = authenticatedUserService.getAuthenticatedUser();

        Task task = taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));

        if(task.isCompleted()) {
            throw new BusinessException("Tarefa já finalizada");
        }
        task.setCompleted(true);

        Task saved = taskRepository.save(task);
        return taskMapper.fromEntity(saved);
    }

    @Transactional
    public void delete(Long id) {
        User user = authenticatedUserService.getAuthenticatedUser();

        Task task = taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));

        if(task.isCompleted()) {
            throw new BusinessException("Tarefa já finalizada, não é possível excluir");
        }
        if(!task.isActive()) {
            throw new BusinessException("Tarefa já excluída");
        }

        task.setActive(false);

        Task saved = taskRepository.save(task);

    }
}
