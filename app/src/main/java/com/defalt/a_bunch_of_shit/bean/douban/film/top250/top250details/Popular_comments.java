/**
  * Copyright 2019 bejson.com 
  */
package com.defalt.a_bunch_of_shit.bean.douban.film.top250.top250details;
import java.util.Date;

/**
 * Auto-generated: 2019-09-10 23:3:11
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Popular_comments {

    private Rating rating;
    private int useful_count;
    private Author author;
    private String subject_id;
    private String content;
    private String created_at;
    private String id;
    public void setRating(Rating rating) {
         this.rating = rating;
     }
     public Rating getRating() {
         return rating;
     }

    public void setUseful_count(int useful_count) {
         this.useful_count = useful_count;
     }
     public int getUseful_count() {
         return useful_count;
     }

    public void setAuthor(Author author) {
         this.author = author;
     }
     public Author getAuthor() {
         return author;
     }

    public void setSubject_id(String subject_id) {
         this.subject_id = subject_id;
     }
     public String getSubject_id() {
         return subject_id;
     }

    public void setContent(String content) {
         this.content = content;
     }
     public String getContent() {
         return content;
     }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

}