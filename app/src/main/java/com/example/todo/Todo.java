package com.example.todo;

import java.io.Serializable;

public class Todo implements Serializable { // 实现Serializable接口用于Intent传递
    private long id; // 改为long类型，方便使用时间戳
    private String content;
    private boolean isCompleted;
    private String startTime; // 新增字段
    private String endTime;   // 新增字段
    private String remindTime; // 新增字段

    // 构造方法
    public Todo(long id, String content, boolean isCompleted,
                String startTime, String endTime, String remindTime) {
        this.id = id;
        this.content = content;
        this.isCompleted = isCompleted;
        this.startTime = startTime;
        this.endTime = endTime;
        this.remindTime = remindTime;
    }

    // 原有构造方法保留，兼容旧数据
    public Todo(int id, String content, boolean isCompleted) {
        this.id = id;
        this.content = content;
        this.isCompleted = isCompleted;
        this.startTime = "";
        this.endTime = "";
        this.remindTime = "";
    }

    // Getter和Setter方法
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public String getRemindTime() { return remindTime; }
    public void setRemindTime(String remindTime) { this.remindTime = remindTime; }
}