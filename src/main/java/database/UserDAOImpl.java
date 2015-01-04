package database;

import java.sql.Connection;
import java.sql.ResultSet;

public class UserDAOImpl implements UserDAO {

    private Connection db_connection;

    public UserDAOImpl(Connection db_connection) {
        this.db_connection = db_connection;
    }

    @Override
    public void createTable() throws Exception {
        String createTableSQL = "CREATE TABLE `kubanzskDB`.`user` ("
                + "`id` INT NOT NULL,"
                + "`login` VARCHAR(45) NULL,"
                + "`password` VARCHAR(45) NULL,"
                + " `email` VARCHAR(45) NULL,"
                + "PRIMARY KEY (`id`));";
        DBExecutor.execUpdate(db_connection, createTableSQL);
    }

    @Override
    public void add(UserDataSet user) throws Exception {
        String sqlStatement;
        if(user.getEmail().equals("admin@admin.ru"))
            sqlStatement = "INSERT INTO user (id,login,email,password) VALUES (\"" + user.getId() + "\",\"" + user.getLogin() + "\",\"" + user.getEmail() + "\",\"" + user.getPassword() + "\"," + ");";
        else
            sqlStatement = "INSERT INTO user (login,email,password) VALUES (\"" + user.getLogin() + "\",\"" + user.getEmail() + "\",\"" + user.getPassword() + "\"," + ");";
        DBExecutor.execUpdate(db_connection, sqlStatement);
    }


    @Override
    public Boolean isExistsByEmail(String findEmail) throws Exception {
        String sqlStatement = "SELECT COUNT(*) as count FROM user WHERE email = \"" + findEmail + "\";";
        return DBExecutor.execQuery(db_connection, sqlStatement, new ResultHandler<Boolean>() {
            @Override
            public Boolean handle(ResultSet result) throws Exception{
                int count = 0;
                if (result.first()) {
                    count = result.getInt("count");
                }
                return count == 1;
            }
        });
    }

    @Override
    public UserDataSet getByEmail(String findEmail) throws Exception {
        String sqlStatement = "SELECT * FROM user WHERE email = \"" + findEmail + "\";";
        return DBExecutor.execQuery(db_connection, sqlStatement, new ResultHandler<UserDataSet>() {
            @Override
            public UserDataSet handle(ResultSet result) throws Exception {
                if (result.first()) {
                    Integer id = result.getInt("id");
                    String login = result.getString("login");
                    String email = result.getString("email");
                    String password = result.getString("password");
                    return new UserDataSet(id,login,email,password);
                }
                else {
                    return null;
                }
            }
        });
    }

    @Override
    public UserDataSet getBySessionId(String findSession_id) throws Exception {
        String sqlStatement = "SELECT user.id, user.login, user.email, user.password, user.score FROM user " +
                "JOIN session ON user.id = session.user_id " +
                "WHERE session.id = \"" + findSession_id + "\";";
        return DBExecutor.execQuery(db_connection, sqlStatement, new ResultHandler<UserDataSet>() {
            @Override
            public UserDataSet handle(ResultSet result) throws Exception {
                if (result.first()) {
                    Integer id = result.getInt("id");
                    String login = result.getString("login");
                    String email = result.getString("email");
                    String password = result.getString("password");
                    return new UserDataSet(id,login,email,password);
                }
                else {
                    return null;
                }
            }
        });
    }
}
