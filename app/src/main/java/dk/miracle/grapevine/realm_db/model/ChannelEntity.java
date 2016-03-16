package dk.miracle.grapevine.realm_db.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mak on 13-01-2016.
 */
public class ChannelEntity extends RealmObject {


    @PrimaryKey
    private int channelId;
    private String name;

    public ChannelEntity() {
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


