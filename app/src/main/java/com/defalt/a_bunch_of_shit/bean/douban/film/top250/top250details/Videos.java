/**
  * Copyright 2019 bejson.com 
  */
package com.defalt.a_bunch_of_shit.bean.douban.film.top250.top250details;

/**
 * Auto-generated: 2019-09-10 23:3:11
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Videos {

    private Source source;
    private String sample_link;
    private String video_id;
    private boolean need_pay;
    public void setSource(Source source) {
         this.source = source;
     }
     public Source getSource() {
         return source;
     }

    public void setSample_link(String sample_link) {
         this.sample_link = sample_link;
     }
     public String getSample_link() {
         return sample_link;
     }

    public void setVideo_id(String video_id) {
         this.video_id = video_id;
     }
     public String getVideo_id() {
         return video_id;
     }

    public void setNeed_pay(boolean need_pay) {
         this.need_pay = need_pay;
     }
     public boolean getNeed_pay() {
         return need_pay;
     }

}