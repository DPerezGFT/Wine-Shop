package com.example.wineshop;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Wine {

    private @Id @GeneratedValue Long id;
    private String name;
    private String year;
    private double rating;
    private int num_reviews;
    private double price;
    private String body;
    private String acidity;
    private int type;
    private int winery;
    private int region;

    Wine() {
    }

    Wine(String name, String year, double rating, int num_reviews, double price, String body, String acidity, int winery, int type, int region) {
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.num_reviews = num_reviews;
        this.price = price;
        this.body = body;
        this.acidity = acidity;
        this.winery = winery;
        this.type = type;
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNum_reviews() {
        return num_reviews;
    }

    public void setNum_reviews(int num_reviews) {
        this.num_reviews = num_reviews;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAcidity() {
        return acidity;
    }

    public void setAcidity(String acidity) {
        this.acidity = acidity;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getWinery() {
        return winery;
    }

    public void setWinery(int winery) {
        this.winery = winery;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Wine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", num_reviews=" + num_reviews +
                ", price=" + price +
                ", body=" + body +
                ", acidity=" + acidity +
                ", type=" + type +
                ", winery=" + winery +
                ", region=" + region +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wine wine = (Wine) o;
        return year == wine.year && Double.compare(wine.rating, rating) == 0 && num_reviews == wine.num_reviews && Double.compare(wine.price, price) == 0 && body == wine.body && acidity == wine.acidity && type == wine.type && winery == wine.winery && region == wine.region && id.equals(wine.id) && name.equals(wine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, year, rating, num_reviews, price, body, acidity, type, winery, region);
    }
}
