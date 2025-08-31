package com.example.todo;

public class Todo {
    private int id; // 唯一标识
    private String content; // 事项内容
    private boolean isCompleted; // 是否完成

    // 构造方法
    public Todo(int id, String content, boolean isCompleted) {
        this.id = id;
        this.content = content;
        this.isCompleted = isCompleted;
    }

    // Getter和Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }
}