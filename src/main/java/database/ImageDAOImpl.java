package database;

import java.sql.Connection;

public class ImageDAOImpl implements ImageDAO {

    private Connection db_connection;

    public ImageDAOImpl(Connection db_connection) {
        this.db_connection = db_connection;
    }

    @Override
    public void createTable() throws Exception {
        String createTableSQL = "CREATE TABLE `kubanzskDB`.`image` ("
                + "`id` INT NOT NULL,"
                + "`name` VARCHAR(200) NULL,"
                + "`originPath` VARCHAR(200) NULL,"
                + "`id_user` INT NULL,"
                + "`datetime` DATETIME NULL,"
                + "`x` INT NULL,"
                + "`y` INT NULL,"
                + "`width` INT NULL,"
                + "`height` INT NULL,"
                + "PRIMARY KEY (`id`),"
                + "INDEX `fk_image_1_idx` (`id_user` ASC),"
                + "CONSTRAINT `fk_image_1`"
                + "FOREIGN KEY (`id_user`)"
                + "REFERENCES `kubanzskDB`.`user` (`id`)"
                + "ON DELETE CASCADE"
                + "ON UPDATE CASCADE);";
        DBExecutor.execUpdate(db_connection, createTableSQL);
    }
}