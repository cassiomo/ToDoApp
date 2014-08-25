package com.example.kemo.todoapp;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by kemo on 8/23/14.
 */
public class ToDoItem implements Serializable, Comparable {

    public int remoteId;

    private int priority;

    private Date dueDate;

    private String description;

    private Random random = new Random();

    public ToDoItem() {
        super();
    }

    @Override
    public int compareTo(Object another) {
        int comparePriority = ((ToDoItem) another).getPriority();

        //ascending order
        return this.priority - comparePriority;
    }

    public ToDoItem(int remoteId, int priority, Date dueDate, String description) {
        super();
        this.remoteId = remoteId;
        this.priority = priority;
        this.dueDate = dueDate;
        this.description = description;
    }

    public ToDoItem(int priority, Date dueDate, String description) {
        super();
        this.remoteId = random.nextInt();
        this.priority = priority;
        this.dueDate = dueDate;
        this.description = description;
    }

    public int getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(int remoteId) {
        this.remoteId = remoteId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        StringBuilder todoItem = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
        String date = sdf.format(dueDate);
        todoItem.append(date);
        todoItem.append(",");
        todoItem.append(description);
        todoItem.append(",");
        todoItem.append(priority);
        return todoItem.toString();
    }
}

