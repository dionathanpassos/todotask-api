package com.todotask.todo_task_api.repository;

import com.todotask.todo_task_api.model.Task;
import com.todotask.todo_task_api.model.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findAllByUser(User user, Pageable pageable);

    boolean existsByTitleIgnoreCaseAndCompletedAndUser(String title, boolean b, User user);

    Optional<Task> findByIdAndUser(Long id, User user);
}
