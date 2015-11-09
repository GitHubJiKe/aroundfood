package com.ypf.aroundfood.entity;

/**
 * Created by Administrator on 2015/11/9.
 */

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
public class AroundFood implements Serializable {
    public String reason  ;
    public int error_code  ;
    public String resultcode  ;
    public List<Result> result;

    public List<Result> getResult() {
        return result;
    }

    public class Result implements Serializable  {
        public String area  ;
        public String bad_remarks  ;
        public String good_remarks  ;
        public String address  ;
        public String city  ;
        public String latitude  ;
        public String avg_price  ;
        public String all_remarks  ;
        public String common_remarks  ;
        public String stars  ;
        public String service_rating  ;
        public String photos  ;
        public String tags  ;
        public String very_good_remarks  ;
        public String navigation  ;
        public String very_bad_remarks  ;
        public String phone  ;
        public String environment_rating  ;
        public String name  ;
        public String recommended_products  ;
        public String product_rating  ;
        public String recommended_dishes  ;
        public String nearby_shops  ;
        public String longitude  ;
        public void  setArea (String area){this.area=area;}
        public String getArea (){return this.area;}
        public void  setBad_remarks (String bad_remarks){this.bad_remarks=bad_remarks;}
        public String getBad_remarks (){return this.bad_remarks;}
        public void  setGood_remarks (String good_remarks){this.good_remarks=good_remarks;}
        public String getGood_remarks (){return this.good_remarks;}
        public void  setAddress (String address){this.address=address;}
        public String getAddress (){return this.address;}
        public void  setCity (String city){this.city=city;}
        public String getCity (){return this.city;}
        public void  setLatitude (String latitude){this.latitude=latitude;}
        public String getLatitude (){return this.latitude;}
        public void  setAvg_price (String avg_price){this.avg_price=avg_price;}
        public String getAvg_price (){return this.avg_price;}
        public void  setAll_remarks (String all_remarks){this.all_remarks=all_remarks;}
        public String getAll_remarks (){return this.all_remarks;}
        public void  setCommon_remarks (String common_remarks){this.common_remarks=common_remarks;}
        public String getCommon_remarks (){return this.common_remarks;}
        public void  setStars (String stars){this.stars=stars;}
        public String getStars (){return this.stars;}
        public void  setService_rating (String service_rating){this.service_rating=service_rating;}
        public String getService_rating (){return this.service_rating;}
        public void  setPhotos (String photos){this.photos=photos;}
        public String getPhotos (){return this.photos;}
        public void  setTags (String tags){this.tags=tags;}
        public String getTags (){return this.tags;}
        public void  setVery_good_remarks (String very_good_remarks){this.very_good_remarks=very_good_remarks;}
        public String getVery_good_remarks (){return this.very_good_remarks;}
        public void  setNavigation (String navigation){this.navigation=navigation;}
        public String getNavigation (){return this.navigation;}
        public void  setVery_bad_remarks (String very_bad_remarks){this.very_bad_remarks=very_bad_remarks;}
        public String getVery_bad_remarks (){return this.very_bad_remarks;}
        public void  setPhone (String phone){this.phone=phone;}
        public String getPhone (){return this.phone;}
        public void  setEnvironment_rating (String environment_rating){this.environment_rating=environment_rating;}
        public String getEnvironment_rating (){return this.environment_rating;}
        public void  setName (String name){this.name=name;}
        public String getName (){return this.name;}
        public void  setRecommended_products (String recommended_products){this.recommended_products=recommended_products;}
        public String getRecommended_products (){return this.recommended_products;}
        public void  setProduct_rating (String product_rating){this.product_rating=product_rating;}
        public String getProduct_rating (){return this.product_rating;}
        public void  setRecommended_dishes (String recommended_dishes){this.recommended_dishes=recommended_dishes;}
        public String getRecommended_dishes (){return this.recommended_dishes;}
        public void  setNearby_shops (String nearby_shops){this.nearby_shops=nearby_shops;}
        public String getNearby_shops (){return this.nearby_shops;}
        public void  setLongitude (String longitude){this.longitude=longitude;}
        public String getLongitude (){return this.longitude;}

        public String toString() {String s = "";Field[] arr = this.getClass().getFields();for (Field f : getClass().getFields()) {try {s += f.getName() + "=" + f.get(this) + "\n,";} catch (Exception e) {}}return getClass().getSimpleName() + "[" + (arr.length==0?s:s.substring(0, s.length() - 1)) + "]";   }
    }
    public void  setReason (String reason){this.reason=reason;}
    public String getReason (){return this.reason;}
    public void  setError_code (int error_code){this.error_code=error_code;}
    public int getError_code (){return this.error_code;}
    public void  setResultcode (String resultcode){this.resultcode=resultcode;}
    public String getResultcode (){return this.resultcode;}

    public String toString() {String s = "";Field[] arr = this.getClass().getFields();for (Field f : getClass().getFields()) {try {s += f.getName() + "=" + f.get(this) + "\n,";} catch (Exception e) {}}return getClass().getSimpleName() + "[" + (arr.length==0?s:s.substring(0, s.length() - 1)) + "]";}
}
