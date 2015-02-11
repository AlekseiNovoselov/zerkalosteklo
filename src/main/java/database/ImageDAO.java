package database;

import java.util.ArrayList;

public interface ImageDAO {

    void createTable() throws Exception;

    void add (String name, String album) throws Exception;

    Boolean isExistsByName(String imageName) throws Exception;

    ArrayList<ImageDataSet> searchImageByNameLike(String nameLike) throws Exception;
}
