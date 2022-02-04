package com.example.tinderv1.Status;


public class StatusObject {
    private String userId;
    private String name;
    private String profileImageUrl;

    private String status;


    public StatusObject(String userId, String name, String profileImageUrl,String status) {

        this.userId = userId;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        //this.yeps = yeps;
        //this.nope = nope;
        this.status = status;

    }

    public String getUserId(){
        return userId;
    }

    public void setUserID(String userID) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

   public String getStatus() { return status;
  }
  public void setAccepted(String value) {
      this.status= status;
  }



}
