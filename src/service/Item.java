package service;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/16 15:30
 */
public class Item {
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Item(String id, String name, String img, String description, String video, int hot, String year) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.description = description;
        this.video = video;
        this.hot = hot;
        this.year = year;
    }

    public Item(){}

    public Item(String id, String name, int hot, String year) {
        this.id = id;
        this.name = name;
        this.hot = hot;
        this.year = year;
    }

    private String id;
    private String name;
    private String img;
    private String description;
    private String video;
    private int hot;
    private String year;
}
