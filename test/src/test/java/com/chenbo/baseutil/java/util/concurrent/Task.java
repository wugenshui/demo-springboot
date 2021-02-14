package com.chenbo.baseutil.java.util.concurrent;

/**
 * @author : chenbo
 * @date : 2021-02-14
 */
public class Task implements Comparable<Task> {
    private int id;
    private String name;

    public Task(int id,String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Task o) {
        return id > o.id ? 1 : (id == o.id ? 0 : -1);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
