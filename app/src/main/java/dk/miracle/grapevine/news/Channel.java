package dk.miracle.grapevine.news;

public class Channel {

    private final int channelId;
    private final String name;

    public Channel() {
        channelId = News.getSmallRandomInteger();
        name = News.getRandomString(News.getRandomInteger(128));
    }

    public int getChannelId() {
        return channelId;
    }

    public String getName() {
        return name;
    }

}
