package dk.miracle.grapevine.realm_db.model;

import com.google.gson.annotations.Expose;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by mak on 14-12-2015.
 */
public class EmployeeEntity extends RealmObject {

    @PrimaryKey
    @Expose
    private int id;
    @Expose
    private String name;
    @Expose
    private String email;
    @Expose
    private String phone;
    @Expose
    private String bio;
    @Expose
    private String homeOfficeName;
    @Expose
    private String CurrentOfficeName;
    @Expose
    private String pictureUrl;

    @Index
    @Expose
    private int currentOfficeId;
    @Expose
    private int homeOfficeId;
    @Expose
    private boolean isTrackingLocation;

    public EmployeeEntity() {
    }

    public boolean isTrackingLocation() {
        return isTrackingLocation;
    }

    public void setIsTrackingLocation(boolean isTrackingLocation) {
        this.isTrackingLocation = isTrackingLocation;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getHomeOfficeName() {
        return homeOfficeName;
    }

    public void setHomeOfficeName(String homeOfficeName) {
        this.homeOfficeName = homeOfficeName;
    }

    public String getCurrentOfficeName() {
        return CurrentOfficeName;
    }

    public void setCurrentOfficeName(String currentOfficeName) {
        CurrentOfficeName = currentOfficeName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public int getCurrentOfficeId() {
        return currentOfficeId;
    }

    public void setCurrentOfficeId(int currentOfficeId) {
        this.currentOfficeId = currentOfficeId;
    }

    public int getHomeOfficeId() {
        return homeOfficeId;
    }

    public void setHomeOfficeId(int homeOfficeId) {
        this.homeOfficeId = homeOfficeId;
    }
}
