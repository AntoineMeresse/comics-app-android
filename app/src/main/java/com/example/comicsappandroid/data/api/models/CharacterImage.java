package com.example.comicsappandroid.data.api.models;

import com.google.gson.annotations.SerializedName;

public class CharacterImage {

    @SerializedName("icon_url")
    private String iconUrl;

    @SerializedName("thumb_url")
    private String thumbUrl;

    @SerializedName("medium_url")
    private String mediumUrl;

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getMediumUrl() {
        return mediumUrl;
    }

    public void setMediumUrl(String mediumUrl) {
        this.mediumUrl = mediumUrl;
    }
}
