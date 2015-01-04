package database;

import base.DBService;
import editor.Image;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBServiceImpl implements DBService {

    private UserDAO userDAO;
    private ImageDAO imageDAO;

    public DBServiceImpl(String db_host, String db_port, String db_name, String db_user, String db_password) {
        StringBuilder url = new StringBuilder();
        url.
                append("jdbc:mysql://").
                append(db_host).append(":").
                append(db_port).append("/").
                append(db_name).
                append("?user=").append(db_user).
                append("&password=").append(db_password);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection db_connection = DriverManager.getConnection(url.toString());
            UserDAO  userDAO= new UserDAOImpl(db_connection);
            ImageDAO imageDAO = new ImageDAOImpl(db_connection);
            userDAO.createTable();
            imageDAO.createTable();
        } catch (Exception e) {
            System.out.println("Exception in DBServiceImpl.DBServiceImpl: " + e.getMessage());
        }
    }

    public void addImage(Image image) {

    }
}
