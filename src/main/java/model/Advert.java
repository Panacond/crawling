package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Advert {
    String id;
    String href;
    String title;
    String price;
    String description;
    String geolocation_x;
    String geolocation_y;
    List<File> photo = new ArrayList<>();

    public Advert(String href){
        setHref(href);
        setId(regExId(href));
    }
    private static String regExId(String href){
        Pattern pattern = Pattern.compile("item/(\\d*)");
        Matcher matcher = pattern.matcher(href);
        matcher.find();
        return matcher.group(1);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGeolocation(String geolocation_x, String geolocation_y) {
        this.geolocation_x = geolocation_x;
        this.geolocation_y = geolocation_y;
    }

    public String getId(){
        return id;
    }

    public String getHref(){
        return href;
    }

    public void addPhoto(File photo){
        this.photo.add(photo);
    }

    @Override
    public String toString() {
        return "Advert{" +
                "id='" + id + '\'' +
//                ", href='" + href + '\'' +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
//                ", description='" + description + '\'' +
                ", geolocation_x='" + geolocation_x + '\'' +
                ", geolocation_y='" + geolocation_y + '\'' +
                ", photo=" + photo +
                '}';
    }
}
