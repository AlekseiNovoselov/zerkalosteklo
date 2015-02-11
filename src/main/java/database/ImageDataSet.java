package database;

public class ImageDataSet {
    private Long id;
    private String name;
    private String date_of_create;
    private String album;

    public ImageDataSet(long id, String name, String date_of_create, String album) {
        this.id = id;
        this.name = name;
        this.date_of_create = date_of_create;
        this.album = album;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String email) {
        this.name = name;
    }

    public String getDateOfCreate() {
        return date_of_create;
    }

    public void setDateOfCreate(String date_of_create) {
        this.date_of_create = date_of_create;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(Integer quantity_of_images) {
        this.album = album;
    }

}
