//package com.example.kemo.todoapp;
//
//import com.activeandroid.Model;
//import com.activeandroid.annotation.Column;
//import com.activeandroid.annotation.Table;
//import com.activeandroid.query.Select;
//
//import java.io.Serializable;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
///**
// * Created by kemo on 8/23/14.
// */
//@Table(name = "ToDoItem")
//public class ToDoItemModel extends Model {
//
//    //@Column(name = "id", index = true, unique = true)
//    @Column(name = "id")
//    public String id;
//    @Column(name = "priority")
//    public int priority;
//    @Column(name = "dueDate")
//    public  Date dueDate;
//    @Column(name = "description")
//    public String description;
//
//    public ToDoItemModel() {
//        super();
//    }
//
//    public ToDoItemModel(int priority, Date dueDate, String description) {
//        super();
//        this.priority = priority;
//        this.dueDate = dueDate;
//        this.description = description;
//    }
//
//    public static List<ToDoItemModel> getAll(ToDoItemModel toDoItemModel) {
//        return new Select()
//                .from(ToDoItemModel.class)
//                .execute();
//    }
//}
//
