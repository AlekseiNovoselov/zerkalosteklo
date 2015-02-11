package database;

import resourse.ImageQueries;
import resourse.ResourceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ImageDAOImpl implements ImageDAO {

    private Connection db_connection;
    private ImageQueries imageQueries;

    public ImageDAOImpl(Connection db_connection) {
        this.db_connection = db_connection;
        this.imageQueries = (ImageQueries) ResourceFactory.instance().get("./data/DataBaseQueries/ImageQueries.xml");
    }

    @Override
    public void createTable() throws Exception {
        DBExecutor.execUpdate(db_connection, imageQueries.getDropImage());
        DBExecutor.execUpdate(db_connection, imageQueries.getCreateImage());
    }

    @Override
    public Boolean isExistsByName(String imageName) throws Exception {
        try (PreparedStatement preparedStatement = db_connection.prepareStatement(imageQueries.getIsImageExistsByName())) {
            preparedStatement.setString(1, imageName);
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

    @Override
    public void add (String name, String album) throws Exception {
        try (PreparedStatement preparedStatement = db_connection.prepareStatement(imageQueries.getAddImage())) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, album);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public ArrayList<ImageDataSet> searchImageByNameLike(String nameLike) throws Exception {
        System.out.println("inImageDAOImpl");

        try (PreparedStatement preparedStatement = db_connection.prepareStatement(imageQueries.getSearchImageByNameLike())) {
            System.out.println("connection");
            System.out.println(imageQueries.getSearchImageByNameLike());
            System.out.println(nameLike);
            System.out.println(preparedStatement);
            preparedStatement.setString(1, "%"+nameLike+"%");
            System.out.println("setString");
            preparedStatement.executeQuery();
            System.out.println("exec");
            return DBExecutor.execPreparedQuery(preparedStatement, new ResultHandler<ArrayList<ImageDataSet>>() {
                @Override
                public ArrayList<ImageDataSet> handle(ResultSet result) throws Exception{
                    ArrayList<ImageDataSet> imageCollection = new ArrayList<ImageDataSet>();
                    while(result.next()) {
                        Long id = result.getLong("id");
                        System.out.println(id);
                        String name = result.getString("name");
                        String date_of_create = result.getString("date_of_create");
                        String album = result.getString("album");
                        ImageDataSet imageDataSet = new ImageDataSet(id, name, date_of_create, album);
                        imageCollection.add(imageDataSet);
                    }
                    return imageCollection;
                }
            });
        }
    }
}