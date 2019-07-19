package bean;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/16 15:30
 */
public class Item {
    private String IMG_BASE_SRC="templates/img/art_img/";
    private String VIDEO_BASE_SRC="templates/videos/";
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String year) {
        this.time = year;
    }

    public Item(int id, String name, String img, String description, String video, int hot, String time, String location,String genre) {
        this.id = id;
        this.name = name;
        this.img = IMG_BASE_SRC + img;
        this.description = description;
        this.video = VIDEO_BASE_SRC + video;
        this.hot = hot;
        this.time = time;
        this.location = location;
        this.genre = genre;
    }

    public Item(){}

    public Item(int id, String name, int hot, String time) {
        this.id = id;
        this.name = name;
        this.hot = hot;
        this.time = time;
    }

    private int id;
    private String name;
    private String img;
    private String description;
    private String video;
    private int hot;
    private String time;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    private String genre;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String location;
}
