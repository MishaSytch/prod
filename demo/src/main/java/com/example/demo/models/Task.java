package com.example.demo.models;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public final class Task {
    private LocalDateTime dateTime; 
    private String task, author, name; 
    private Priority priority;

    public Task(LocalDateTime dateTime, String task, String author, Priority priority, String name) {
        this.dateTime = dateTime;
        this.task = task;
        this.author = author;
        this.priority = priority;
        this.name = name;
    }
}
