package resourse;

public class AlbumQueries implements Resource{

    private String db_dropAlbum;
    private String db_createAlbum;
    private String db_addAlbum;
    private String db_isAlbumExistsByName;
    private String db_getAllAlbums;
    private String db_getAlbumByName;
    private String db_deleteAlbumByName;

    public AlbumQueries() {};

    public  AlbumQueries(String db_dropAlbum, String db_createAlbum,
                         String db_addAlbum, String db_isAlbumExistsByName,
                         String db_getAllAlbums, String db_getAlbumByName,
                         String db_deleteAlbumByName) {
        this.setDropAlbum(db_dropAlbum);
        this.setCreateAlbum(db_createAlbum);
        this.setAddAlbum(db_addAlbum);
        this.setIsAlbumExistsByName(db_isAlbumExistsByName);
        this.setAddAlbum(db_addAlbum);
        this.setGetAlbumByName(db_getAlbumByName);
        this.setDeleteAlbumByName(db_deleteAlbumByName);
    }

    public String getDropAlbum() { return db_dropAlbum; }
    public String getCreateAlbum () { return db_createAlbum; }
    public String getAddAlbum () {return db_addAlbum; }
    public String getIsAlbumExistsByName () {return db_isAlbumExistsByName; }
    public String getAllAlbums() { return db_getAllAlbums;}
    public String getAlbumByName() { return db_getAlbumByName;}
    public String getDeleteAlbumByName() { return db_deleteAlbumByName;}

    public void setDropAlbum(String db_dropAlbum) { this.db_dropAlbum = db_dropAlbum; }

    public void setCreateAlbum(String db_createAlbum) { this.db_createAlbum = db_createAlbum; }

    public void setAddAlbum (String db_addAlbum) { this.db_addAlbum = db_addAlbum; }

    public void setIsAlbumExistsByName (String db_isAlbumExistsByName) {this.db_isAlbumExistsByName = db_isAlbumExistsByName; }

    public void setAllAlbums (String db_getAllAlbums) {this.db_getAllAlbums = db_getAllAlbums; }

    public void setGetAlbumByName (String db_getAlbumByName) {this.db_getAlbumByName = db_getAlbumByName;}

    public void setDeleteAlbumByName (String db_deleteAlbumByName) { this.db_deleteAlbumByName = db_deleteAlbumByName;}
}
