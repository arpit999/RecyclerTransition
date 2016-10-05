package com.production.home.recyclertransition;

/**
 * Created by Arpit on 05-Oct-16.
 */
public class Movie {

    String Name,rating,image,release_year;

    public Movie(String name, String rating, String image, String release_year) {
        Name = name;
        this.rating = rating;
        this.image = image;
        this.release_year = release_year;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRelease_year() {
        return release_year;
    }

    public void setRelease_year(String release_year) {
        this.release_year = release_year;
    }
}
