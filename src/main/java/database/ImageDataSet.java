package database;

import java.time.format.DateTimeFormatter;

public class ImageDataSet {
    private int id;
    private String name;
    private String originPath;
    private int id_user;
    private DateTimeFormatter datetime;
    private int x;
    private int y;
    private int width;
    private int height;

    public ImageDataSet (int _id, String _name, String _originPath, int _id_user,
                         DateTimeFormatter _datetime, int _x, int _y, int _width, int _height) {
        this.id = _id;
        this.name = _name;
        this.originPath = _originPath;
        this.id_user = _id_user;
        this.datetime = _datetime;
        this.x = _x;
        this.y = _y;
        this.width = _width;
        this.height = _height;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOriginPath () {
        return originPath;
    }
    public int getId_user() {
        return id_user;
    }

    public DateTimeFormatter getDatetime() {
        return datetime;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOriginPath(String originPath) {
        this.originPath = originPath;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setDatetime(DateTimeFormatter datetime) {
        this.datetime = datetime;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
