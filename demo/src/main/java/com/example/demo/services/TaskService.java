package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.demo.models.Editing;
import com.example.demo.models.Priority;
import com.example.demo.models.Task;

public final class TaskService {

    private String author;
    private HashMap<String, Task> tasks = new HashMap<>();

    public TaskService(String author) {
        this.author = author;
    }

    public List<Task> getTasks() {
        List<Task> tList = new ArrayList<>();

        for (Task task : tasks.values()) {
            tList.add(task);
        }
        return tList;
    }

    public boolean createTask(LocalDateTime dateTime, String name, String task, Priority priority) {
        if (tasks.keySet().contains(name)) return false; 

        tasks.put(name, new Task(dateTime, task, author, priority, name));
        
        return true;
    }

    public void editTask(String name, Editing type, Object smth) {
        Task task = tasks.get(name);
        
        switch (type) {
            case AUTHOR:
                author = (String) smth;
                task.setAuthor(author);
                break;
            case TASK:
                task.setTask((String) smth);
                break;
            case PRIORITY:
            task.setPriority((Priority) smth);
            break;
            case DATETIME:
                task.setDateTime((LocalDateTime) smth);
                break;
            case NAME:
                task.setName((String) smth);
                break;

            default:
                break;
        }
    }

    public void deleteTask(String name) {
        tasks.remove(name);
    }
}
