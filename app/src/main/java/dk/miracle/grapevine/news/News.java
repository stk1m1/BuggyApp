package dk.miracle.grapevine.news;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class News {
    private static final Random RANDOM_GEN = new Random();

    @NonNull
    static String getRandomString(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Random string length must be greater than 0.");
        }
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            // space, 0~9, a-z, A-Z !"#%
            tmp.append( Character.toChars( Math.min( Math.max( RANDOM_GEN.nextInt(79), 32), 126)));
        }
        return tmp.toString();
    }

    static int getSmallRandomInteger() {
        final int RANDOM_MAX = 1000;
        return Math.max(RANDOM_GEN.nextInt((int) (RANDOM_MAX * 0.5)) % RANDOM_MAX, 1);
    }

    static int getRandomInteger(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("Random number maximum limit must be greater than 0.");
        }
        return Math.max(RANDOM_GEN.nextInt((int) (max * 0.5)) % max, 1);
    }

    public static News getNews() {
        News aNews = new News(false);
        aNews.fillCommentsList();
        return aNews;
    }

    private final int id;
    private final int parentId;
    private final String title;
    private final String body;
    private final int authorId;
    private final String authorName;
    private final String authorPictureUrl;
    private final Date created;
    private final List<News> commentsList;
    private final ArrayList<Integer> likedByUserIds;
    private final Date serviceAnnouncementEnd;
    private final Date serviceAnnouncementStart;
    private final Channel channel;
    private final MediaContent mediaContent;
    private final boolean deleteMediaContent;
    private final boolean isComment;

    private News(boolean commentNews) {
        id = getSmallRandomInteger();
        parentId = getSmallRandomInteger();
        title = getRandomString(getRandomInteger(128));
        body = getRandomString(getRandomInteger(512));
        authorId = getSmallRandomInteger();
        authorName = getRandomString(getRandomInteger(64));
        authorPictureUrl = getRandomString(getRandomInteger(128));
        // 2000-01-01 ~ 2016-12-31
        created = new Date(946684800 + getRandomInteger(536457600));
        commentsList = new ArrayList<>();
        likedByUserIds = new ArrayList<>();
        int likeCount = getRandomInteger(100);
        for (int i = 0; i < likeCount; ++i) {
            likedByUserIds.add(Integer.valueOf(getSmallRandomInteger()));
        }
        // 2000-01-01 ~ 2016-12-31
        serviceAnnouncementEnd = new Date(946684800 + getRandomInteger(536457600));
        // 2000-01-01 ~ 2016-12-31
        serviceAnnouncementStart = new Date(946684800 + getRandomInteger(536457600));
        if (RANDOM_GEN.nextBoolean()) {
            channel = new Channel();
        } else {
            channel = null;
        }
        if (RANDOM_GEN.nextBoolean()) {
            mediaContent = new MediaContent();
        } else {
            mediaContent = null;
        }
        // This app is to detect crash while inserting, we do not delete anything.
        deleteMediaContent = false;
        isComment = commentNews;
    }

    public int getId() {
        return id;
    }

    public int getParentId() {
        return parentId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorPictureUrl() {
        return authorPictureUrl;
    }

    public Date getCreated() {
        return created;
    }

    public List<News> getCommentsList() {
        return commentsList;
    }

    public ArrayList<Integer> getLikedByUserIds() {
        return likedByUserIds;
    }

    public Date getServiceAnnouncementEnd() {
        return serviceAnnouncementEnd;
    }

    public Date getServiceAnnouncementStart() {
        return serviceAnnouncementStart;
    }

    public Channel getChannel() {
        return channel;
    }

    public MediaContent getMediaContent() {
        return mediaContent;
    }

    public boolean isDeleteMediaContent() {
        return deleteMediaContent;
    }

    public boolean isAComment() {
        return isComment;
    }

    public boolean hasMediaContent() {
        return (mediaContent != null);
    }

    public boolean hasChannel() {
        return (channel != null);
    }

    private void fillCommentsList() {
        int commentCount = getRandomInteger(100);
        for (int i = 0; i < commentCount; ++i) {
            commentsList.add(new News(true));
        }
    }

    @Override
    public String toString() {
        return authorName + "|" + authorPictureUrl;
    }


}
