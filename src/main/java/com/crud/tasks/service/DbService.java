package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.exceptions.NoSuchTaskException;
import com.crud.tasks.repo.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {

    private final TaskRepo taskRepo;

    public List<Task> getTasks(){
        return taskRepo.findAll();
    }

    public Optional<Task> getTask(final long id){
        return taskRepo.findById(id);
    }

    public void createTask(final Task task){
        taskRepo.save(task);
    }

    @Transactional
    public Task updateTask(final Task task,final long id) throws NoSuchTaskException {
        Task fromDb = taskRepo.findById(id)
                .orElseThrow(() -> new NoSuchTaskException("There is no task with given id: " + id));
        fromDb.setTitle(task.getTitle());
        fromDb.setContent(task.getContent());
        return fromDb;
    }

    public void deleteTask(final long id){
        taskRepo.deleteById(id);
    }
}
