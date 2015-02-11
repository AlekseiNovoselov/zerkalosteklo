package resourse;

public class ImageQueries implements Resource{

    private String db_dropImage;
    private String db_createImage;
    private String db_addImage;
    private String db_isImageExistsByName;
    private String db_getImageByName;
    private String db_getAllImageByAlbum;
    private String db_deleteImageByName;
    private String db_deleteImageByAlbum;
    private String db_searchImageByNameLike;

    public ImageQueries() {};

    public  ImageQueries(String db_dropImage, String db_createImage,
                         String db_addImage, String db_isImageExistsByName,
                         String db_getImageByName, String db_getAllImageByAlbum,
                         String db_deleteImageByName, String db_deleteImageByAlbum,
                         String db_searchImageByNameLike) {
        this.setDropImage(db_dropImage);
        this.setCreateImage(db_createImage);
        this.setAddImage(db_addImage);
        this.setIsImageExistsByName(db_isImageExistsByName);
        this.setGetImageByName(db_getImageByName);
        this.setGetAllImageByAlbum(db_getAllImageByAlbum);
        this.setDeleteImageByName(db_deleteImageByName);
        this.setDeleteImageByAlbum(db_deleteImageByAlbum);
        this.setSearchImageByNameLike(db_searchImageByNameLike);
    }

    public String getDropImage() { return db_dropImage; }
    public String getCreateImage () { return db_createImage; }
    public String getAddImage () {return db_addImage; }
    public String getIsImageExistsByName () {return db_isImageExistsByName; }
    public String getGetImageByName () { return db_getImageByName;}
    public String getGetAllImagesByAlbum() { return db_getAllImageByAlbum;}
    public String getDeleteImageByName() { return db_deleteImageByName;}
    public String getDeleteImageByAlbum() { return db_deleteImageByAlbum;}
    public String getSearchImageByNameLike() { return db_searchImageByNameLike;}

    public void setDropImage(String db_dropImage) { this.db_dropImage = db_dropImage; }
    public void setCreateImage(String db_createImage) { this.db_createImage = db_createImage; }
    public void setAddImage (String db_addImage) { this.db_addImage = db_addImage; }
    public void setIsImageExistsByName (String db_isImageExistsByName) {this.db_isImageExistsByName = db_isImageExistsByName; }
    public void setGetImageByName (String db_getImageByName) {this.db_getImageByName = db_getImageByName; }
    public void setGetAllImageByAlbum (String db_getAllImageByAlbum) {this.db_getAllImageByAlbum = db_getAllImageByAlbum;}
    public void setDeleteImageByName (String db_deleteImageByName) { this.db_deleteImageByName = db_deleteImageByName;}
    public void setDeleteImageByAlbum (String db_deleteImageByAlbum) { this.db_deleteImageByAlbum = db_deleteImageByAlbum;}
    public void setSearchImageByNameLike (String db_searchImageByNameLike) {this.db_searchImageByNameLike = db_searchImageByNameLike;}
}
