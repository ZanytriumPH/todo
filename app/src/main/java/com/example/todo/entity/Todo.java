package com.example.todo.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo") // 数据库表名
public class Todo implements Serializable {
    @PrimaryKey(autoGenerate = true) // 自动生成主键
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