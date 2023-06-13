package br.com.todoApp.todoApp.services;

import br.com.todoApp.todoApp.models.TaskModel;
import br.com.todoApp.todoApp.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

   final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Transactional
    public TaskModel save(TaskModel taskModel) {
        return taskRepository.save(taskModel);
    }

    public List<TaskModel> findAll() {
        return taskRepository.findAll();
    }

    public Optional<TaskModel> findById(UUID id) {
        return taskRepository.findById(id);
    }

    @Transactional
    public void delete(TaskModel taskModel) {
         taskRepository.delete(taskModel);
    }
    
    public List<TaskModel> search(String keyword) {
        List<TaskModel> allTasks = taskRepository.findAll();
        
        List<TaskModel> searchResults = new ArrayList<>();
        for (TaskModel task : allTasks) {
            if (task.getDescription().contains(keyword)) {
                searchResults.add(task);
            }
        }
        
        return searchResults;
    }
    
}
