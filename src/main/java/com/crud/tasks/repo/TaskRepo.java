package com.crud.tasks.repo;

import com.crud.tasks.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepo extends CrudRepository<Task, Long> {
    List<Task> findAll();
}
