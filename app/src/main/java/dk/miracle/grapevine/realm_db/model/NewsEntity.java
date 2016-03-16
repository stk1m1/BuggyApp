package dk.miracle.grapevine.realm_db.model;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mak on 13-01-2016.
 */
public class NewsEntity extends RealmObject {

    @PrimaryKey
    private int id;
    private int channelId;
    private String title;
    private String body;
    private int authorId;
    private String authorName;
    private String authorPictureUrl;
    private Date created;
    private RealmList<CommentEntity> commentsList;
    private RealmList<LikeEntity> likedByUserIds;
    private Date serviceAnnouncementEnd;
    private Date serviceAnnouncementStart;
    private boolean likedByMe;
    private boolean deleteMediaContent;
    private boolean commentedByMe;
    private MediaContentEntity mediaContentEntity;

    @Index
    private boolean isSynchronized;

    public boolean isDeleteMediaContent() {
        return deleteMediaContent;
    }

    public void setDeleteMediaContent(boolean deleteMediaContent) {
        this.deleteMediaContent = deleteMediaContent;
    }

    public RealmList<CommentEntity> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(RealmList<CommentEntity> commentsList) {
        this.commentsList = commentsList;
    }

    public MediaContentEntity getMediaContentEntity() {
        return mediaContentEntity;
    }

    public void setMediaContentEntity(MediaContentEntity mediaContentEntity) {
        this.mediaContentEntity = mediaContentEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorPictureUrl() {
        return authorPictureUrl;
    }

    public void setAuthorPictureUrl(String authorPictureUrl) {
        this.authorPictureUrl = authorPictureUrl;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public RealmList<LikeEntity> getLikedByUserIds() {
        return likedByUserIds;
    }

    public void setLikedByUserIds(RealmList<LikeEntity> likedByUserIds) {
        this.likedByUserIds = likedByUserIds;
    }

    public Date getServiceAnnouncementEnd() {
        return serviceAnnouncementEnd;
    }

    public void setServiceAnnouncementEnd(Date serviceAnnouncementEnd) {
        this.serviceAnnouncementEnd = serviceAnnouncementEnd;
    }

    public Date getServiceAnnouncementStart() {
        return serviceAnnouncementStart;
    }

    public void setServiceAnnouncementStart(Date serviceAnnouncementStart) {
        this.serviceAnnouncementStart = serviceAnnouncementStart;
    }

    public boolean isLikedByMe() {
        return likedByMe;
    }

    public void setLikedByMe(boolean likedByMe) {
        this.likedByMe = likedByMe;
    }

    public boolean isCommentedByMe() {
        return commentedByMe;
    }

    public void setCommentedByMe(boolean commentedByMe) {
        this.commentedByMe = commentedByMe;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public boolean isSynchronized() {
        return isSynchronized;
    }

    public void setIsSynchronized(boolean isSynchronized) {
        this.isSynchronized = isSynchronized;
    }
}
