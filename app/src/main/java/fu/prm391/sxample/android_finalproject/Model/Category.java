package fu.prm391.sxample.android_finalproject.Model;

import java.io.Serializable;

public class Category  implements Serializable {
    private String Id;
    private String Name;
    private String Image;
    private String Description;
    private String Price;
    public Category() {

    }

    public Category(String id, String name, String image, String description, String price) {
        Id = id;
        Name = name;
        Image = image;
        Description = description;
        Price = price;
    }
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
