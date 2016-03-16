package dk.miracle.grapevine.realm_db.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mak on 25-02-2016.
 */
public class NotificationEntity extends RealmObject {
    @PrimaryKey
    private int id;
    private int postId;
    private int firstUserId;
    private int userCount;
    private boolean isNewOrUpdated;
    private Date created;
    private Date updated;
    private Date deleted;
    private int activityNotificationType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getFirstUserId() {
        return firstUserId;
    }

    public void setFirstUserId(int firstUserId) {
        this.firstUserId = firstUserId;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public boolean isNewOrUpdated() {
        return isNewOrUpdated;
    }

    public void setIsNewOrUpdated(boolean isNewOrUpdated) {
        this.isNewOrUpdated = isNewOrUpdated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }

    public int getActivityNotificationType() {
        return activityNotificationType;
    }

    public void setActivityNotificationType(int activityNotificationType) {
        this.activityNotificationType = activityNotificationType;
    }
}
