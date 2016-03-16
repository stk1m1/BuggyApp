package dk.miracle.grapevine.news;

public class MediaContent {

    private int id;
    private String contentUrl;
    private String thumbnailUrl;
    private String mimeType;
    private int width;
    private int height;

    public MediaContent() {
        id = News.getSmallRandomInteger();
        contentUrl = News.getRandomString(News.getRandomInteger(128));
        thumbnailUrl = News.getRandomString(News.getRandomInteger(128));
        mimeType = "text/html";
        width = News.getRandomInteger(1024);
        height = News.getRandomInteger(1024);
    }

    public int getId() {
        return id;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getMimeType() {
        return mimeType;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
