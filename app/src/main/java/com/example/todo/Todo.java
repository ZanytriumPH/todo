package com.example.todo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Todo implements Serializable {
    private long id;
    private String content;
    private boolean isCompleted;
    private String createTime;  // 改为创建时间
    private String endTime;
    private String remindTime;

    // 新构造方法（包含创建时间）
    public Todo(long id, String content, boolean isCompleted,
                String createTime, String endTime, String remindTime) {
        this.id = id;
        this.content = content;
        this.isCompleted = isCompleted;
        this.createTime = createTime;  // 改为创建时间
        this.endTime = endTime;
        this.remindTime = remindTime;
    }

    // 兼容旧数据的构造方法（自动生成创建时间）
    public Todo(int id, String content, boolean isCompleted) {
        this.id = id;
        this.content = content;
        this.isCompleted = isCompleted;
        // 自动生成创建时间
        this.createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());
        this.endTime = "";
        this.remindTime = "";
    }

    // Getter和Setter方法（更新为createTime）
    public String getCreateTime() { return createTime; }
    public void setCreateTime(String createTime) { this.createTime = createTime; }

    // 其他原有方法保持不变...
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public String getRemindTime() { return remindTime; }
    public void setRemindTime(String remindTime) { this.remindTime = remindTime; }
}