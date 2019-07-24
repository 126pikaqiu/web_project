package bean;

/**
 * 艺术品的JavaBean类
 * IMG_BASE_SRC：展品图片的根目录
 * VIDEO_BASE_SRC：展品视频的根目录
 * id：展品的id
 * name：展品名称
 * img：展品图片目录
 * description：展品详细描述
 * video：展品视频目录
 * hot：展品热度
 * time：展品出土时间
 * location：展品馆址
 * genre：展品类型
 * timeReleasedL展品发布时间
 *
 */
public class Item {
    private String IMG_BASE_SRC = "templates/img/art_img/";
    private String VIDEO_BASE_SRC = "templates/videos/";
    private int id;
    private String name;
    private String img;
    private String description;
    private String video;
    private int hot;
    private int time;
    private String location;
    private String genre;
    private String timeReleased;

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

    public int getTime() {
        return time;
    }

    public void setTime(int year) {
        this.time = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimeReleased() {
        return timeReleased;
    }

    public void setTimeReleased(String timeReleased) {
        this.timeReleased = timeReleased;
    }

    public Item(int id, String name, String img, String description, String video, int hot, int time, String location, String genre, String timeReleased) {
        this.id = id;
        this.name = name;
        if (img.contains(IMG_BASE_SRC))
            this.img = img;
        else this.img = IMG_BASE_SRC + img;
        this.description = description;
        if (video.contains(VIDEO_BASE_SRC))
            this.video = video;
        else this.video = VIDEO_BASE_SRC + video;
        this.hot = hot;
        this.time = time;
        this.location = location;
        this.genre = genre;
        this.timeReleased = timeReleased;
    }

    public Item(int id, String name, String img, String description, String video, int hot, int time, String location, String genre) {
        this.id = id;
        this.name = name;
        if (img.contains(IMG_BASE_SRC))
            this.img = img;
        else this.img = IMG_BASE_SRC + img;
        this.description = description;
        if (video.contains(VIDEO_BASE_SRC))
            this.video = video;
        else this.video = VIDEO_BASE_SRC + video;
        this.hot = hot;
        this.time = time;
        this.location = location;
        this.genre = genre;
    }

    public Item(String name, String img, String description, String video, int hot, int time, String location, String genre) {
        this.name = name;
        if (img.contains(IMG_BASE_SRC))
            this.img = img;
        else this.img = IMG_BASE_SRC + img;
        this.description = description;
        if (video.contains(VIDEO_BASE_SRC))
            this.video = video;
        else this.video = VIDEO_BASE_SRC + video;
        this.hot = hot;
        this.time = time;
        this.location = location;
        this.genre = genre;
    }

    public Item(int id, String name, int hot, int time) {
        this.id = id;
        this.name = name;
        this.hot = hot;
        this.time = time;
    }

    public Item() {
    }


}

