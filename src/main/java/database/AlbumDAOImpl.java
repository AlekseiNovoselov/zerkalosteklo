package database;

import resourse.AlbumQueries;
import resourse.ResourceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AlbumDAOImpl implements AlbumDAO {

    private Connection db_connection;
    private AlbumQueries albumQueries;

    public AlbumDAOImpl(Connection db_connection) {
        this.db_connection = db_connection;
        this.albumQueries = (AlbumQueries) ResourceFactory.instance().get("./data/DataBaseQueries/AlbumQueries.xml");
    }

    @Override
    public void createTable() throws Exception {
        DBExecutor.execUpdate(db_connection, albumQueries.getDropAlbum());
        DBExecutor.execUpdate(db_connection, albumQueries.getCreateAlbum());
    }

    @Override
    public void add (String name, Integer quantity_of_images) throws Exception {
        try (PreparedStatement preparedStatement = db_connection.prepareStatement(albumQueries.getAddAlbum())) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, quantity_of_images);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Boolean isExistsByName(String albumName) throws Exception {
        try (PreparedStatement preparedStatement = db_connection.prepareStatement(albumQueries.getIsAlbumExistsByName())) {
            preparedStatement.setString(1, albumName);
            preparedStatement.executeQuery();
            return DBExecutor.execPreparedQuery(preparedStatement, new ResultHandler<Boolean>() {
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
    }
}
