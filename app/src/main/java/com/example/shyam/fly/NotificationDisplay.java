package com.example.shyam.fly;

public class NotificationDisplay {

    public int id;
    public String notification;

    public NotificationDisplay(int id, String notification) {
        this.id = id;
        this.notification = notification;
    }

    public int getId() {
        return id;
    }

    public String getNotification() {
        return notification;
    }
}
