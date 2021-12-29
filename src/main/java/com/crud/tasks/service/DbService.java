package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.exceptions.TaskNotFoundException;
import com.crud.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Optional<Task> getTask(final long id){
        return taskRepository.findById(id);
    }

    public Task createTask(final Task task){
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(final Task task,final long id) throws TaskNotFoundException {
        Task fromDb = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("There is no task with given id: " + id));
        fromDb.setTitle(task.getTitle());
        fromDb.setContent(task.getContent());
        return fromDb;
    }

    public void deleteTask(final long id){
        taskRepository.deleteById(id);
    }
}
