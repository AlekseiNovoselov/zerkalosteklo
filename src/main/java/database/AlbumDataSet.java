package database;

/**
 * Created by aleksei on 07.02.15.
 */
public class AlbumDataSet {
    private Long id;
    private String name;
    private String date_of_create;
    private Integer quantity_of_images;

    public AlbumDataSet(long id, String name, String date_of_create, Integer quantity_of_images) {
        this.id = id;
        this.name = name;
        this.date_of_create = date_of_create;
        this.quantity_of_images = quantity_of_images;
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

    public Integer getQuantityOfImages() {
        return quantity_of_images;
    }

    public void setQuantityOfImages(Integer quantity_of_images) {
        this.quantity_of_images = quantity_of_images;
    }

}
