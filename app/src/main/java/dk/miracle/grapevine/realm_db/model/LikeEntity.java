package dk.miracle.grapevine.realm_db.model;

import io.realm.RealmObject;

/**
 * Created by mak on 13-01-2016.
 */
public class LikeEntity extends RealmObject {

    private int likedById;

    public LikeEntity() {
    }

    public LikeEntity(int likedById) {
        this.likedById = likedById;
    }

    public int getLikedById() {
        return likedById;
    }

    public void setLikedById(int likedById) {
        this.likedById = likedById;
    }

}
