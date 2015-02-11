package database;

/**
 * Created by aleksei on 07.02.15.
 */
public interface AlbumDAO {

    void createTable() throws Exception;

    void add (String name, Integer quantity_of_images) throws Exception;

    Boolean isExistsByName(String albumName) throws Exception;
}
