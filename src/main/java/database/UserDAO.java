package database;

/**
 * Created by aleksei on 03.01.15.
 */
public interface UserDAO {
    void createTable() throws Exception;
    void add(UserDataSet user) throws Exception;
    Boolean isExistsByEmail(String findEmail) throws Exception;
    UserDataSet getByEmail(String findEmail) throws Exception;
    UserDataSet getBySessionId(String findSession_id) throws Exception;
}
