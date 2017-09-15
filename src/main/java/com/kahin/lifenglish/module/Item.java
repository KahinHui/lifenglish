// (c)2016 Flipboard Inc, All Rights Reserved.

package com.kahin.lifenglish.module;

public class Item {
    public String description;
    public String imageUrl;

    public Item() {}


    public Item(String description, String imageUrl) {
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
