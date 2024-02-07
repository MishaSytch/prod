package com.example.demo.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Editing;
import com.example.demo.models.Priority;
import com.example.demo.models.Task;
import com.example.demo.services.TaskService;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/home")
public class HomeController {
    private TaskService ts;

    // http://localhost:8080/home/newAuthor?author=Misha
    @GetMapping("newAuthor")
    public void createInfo(@RequestParam String author) {
        ts = new TaskService(author);
    }

    // http://localhost:8080/home/receiveTasks
    @GetMapping("receiveTasks")
    public List<Task> getTasks() {
        return ts.getTasks();
    }

    // http://localhost:8080/home/addTask?dateTime=2024-01-31 15:30&name=Food&task=Buy apples&receivePriority=HIGH
    @PostMapping("addTask")
    public boolean postMethodName(
        @RequestParam String dateTime, 
        @RequestParam String name, 
        @RequestParam String task, 
        @RequestParam String receivePriority
    ) {
        Priority priority = switch(receivePriority.toUpperCase()) {
            case "HIGH" -> Priority.HIGH;
            case "MID" -> Priority.MID;
            case "LOW" -> Priority.LOW;
            case "VERYHIGHT" -> Priority.VERYHIGHT;

            default -> throw new IllegalArgumentException("Unexpected value: " + receivePriority);
        };
        return ts.createTask(
            LocalDateTime.parse(
                dateTime, 
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            ), 
            name, 
            task, 
            priority
        );
    }

    // http://localhost:8080/home/deleteTask?name=Food
    @DeleteMapping("deleteTask")
    public void deleteTask(@RequestParam String name) {
        ts.deleteTask(name);
    }

    // http://localhost:8080/home/editTask?name=Food&receiveType=AUTHOR&smth=Vova
    @PatchMapping("editTask")
    public boolean editTask(
        @RequestParam String name, 
        @RequestParam String receiveType, 
        @RequestParam String smth
    ) {
        Editing type = switch(receiveType.toUpperCase()) {
            case "AUTHOR" -> Editing.AUTHOR;
            case "DATETIME" -> Editing.DATETIME;
            case "NAME" -> Editing.NAME;
            case "PRIORITY" -> Editing.PRIORITY;
            case "TASK" -> Editing.TASK;

            default -> throw new IllegalArgumentException("Unexpected value: " + receiveType.toUpperCase());
        };

        try {
            ts.editTask(name, type, smth);
        } catch (IllegalArgumentException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }catch (Exception e) {
            return false;
        }

        return true;
    }
}