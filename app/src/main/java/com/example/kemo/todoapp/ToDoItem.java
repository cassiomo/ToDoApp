package com.example.kemo.todoapp;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kemo on 8/23/14.
 */
public class ToDoItem implements Serializable, Comparable {

    private int priority;
    private Date dueDate;
    private String description;

    public ToDoItem() {}

    @Override
    public int compareTo(Object another) {
        int comparePriority = ((ToDoItem) another).getPriority();

        //ascending order
        return this.priority - comparePriority;
    }

    public ToDoItem(int priority, Date dueDate, String description) {
        this.priority = priority;
        this.dueDate = dueDate;
        this.description = description;
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
        todoItem.append(" ");
        todoItem.append(description);
        todoItem.append(" ");
        todoItem.append(priority);
        return todoItem.toString();
    }
}

