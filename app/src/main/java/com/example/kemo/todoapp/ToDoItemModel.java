package com.example.kemo.todoapp;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
* Created by kemo on 8/23/14.
*/
@Table(name = "ToDoItem")
public class ToDoItemModel extends Model {

    //@Column(name = "id", index = true, unique = true)
    @Column(name = "remote_id")
    public int remoteId;
    @Column(name = "priority")
    public int priority;
    @Column(name = "dueDate")
    public  Date dueDate;
    @Column(name = "description")
    public String description;

    Random random = new Random();

    public ToDoItemModel() {
        super();
    }

    public ToDoItemModel(int priority, Date dueDate, String description) {
        super();
        this.remoteId = random.nextInt();
        this.priority = priority;
        this.dueDate = dueDate;
        this.description = description;
    }

    @Override
    public String toString() {
        return "ToDoItemModel{" +
                "random=" + random +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", priority=" + priority +
                ", remoteId=" + remoteId +
                '}';
    }

    public static List<ToDoItemModel> getAll() {
        return new Select()
                .from(ToDoItemModel.class)
                .execute();
    }
}

