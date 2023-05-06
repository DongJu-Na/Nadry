package com.nadeul.ndj.service;

import static java.time.Duration.ofSeconds;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.nadeul.ndj.api.TourInfoService1Api;

import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Slf4j
@Service
public class TourInfoService1ApiService {
	
 private static final String BASE_URL = "http://apis.data.go.kr";
 
 
 final TourInfoService1Api api;
 
 public TourInfoService1ApiService() {
     ObjectMapper mapper = new ObjectMapper();
			      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			      mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);

     OkHttpClient client = new OkHttpClient.Builder()
             .connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS))
             .readTimeout(ofSeconds(10).toMillis(), TimeUnit.MILLISECONDS)
             .build();

     Retrofit retrofit = new Retrofit.Builder()
             .baseUrl(BASE_URL)
             .client(client)
             .addConverterFactory(JacksonConverterFactory.create(mapper))
             .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
             .build();
     
	this.api =  retrofit.create(TourInfoService1Api.class);
}
 
	
 public ResponseEntity<Map<String,Object>> locationBasedList1(String lang , Map<String,Object> param){
	Call<Map<String,Object>> call = api.locationBasedList1(lang,param);
	return execute(call);
 }
 
 public ResponseEntity<Map<String,Object>> searchKeyword1(Map<String,Object> param){
	Call<Map<String,Object>> call = api.searchKeyword1(param);
	return execute(call);
 }
 
 public ResponseEntity<Map<String,Object>> searchFestival1(Map<String,Object> param){
	Call<Map<String,Object>> call = api.searchFestival1(param);
	return execute(call); 
 }
 
 public ResponseEntity<Map<String,Object>> searchStay1(Map<String,Object> param){
	Call<Map<String,Object>> call = api.searchStay1(param);
	return execute(call); 
 }
 
 public ResponseEntity<Map<String,Object>> detailCommon1(Map<String,Object> param){
	Call<Map<String,Object>> call = api.detailCommon1(param);
	return execute(call);
 }
 
 public ResponseEntity<Map<String,Object>> detailIntro1(Map<String,Object> param){
	Call<Map<String,Object>> call = api.detailIntro1(param);
	return execute(call);
 }
 
 public ResponseEntity<Map<String,Object>> detailInfo1(Map<String,Object> param){
	Call<Map<String,Object>> call = api.detailInfo1(param);
	return execute(call);
 }
 
 public ResponseEntity<Map<String,Object>> detailImage1(Map<String,Object> param){
  Call<Map<String,Object>> call = api.detailImage1(param);
  return execute(call);
 }
 
 public ResponseEntity<Map<String,Object>> areaBasedSyncList1(Map<String,Object> param){
  Call<Map<String,Object>> call = api.areaBasedSyncList1(param);
  return execute(call);
 }
 
 public ResponseEntity<Map<String,Object>> areaCode1(Map<String,Object> param){
  Call<Map<String,Object>> call = api.areaCode1(param);
  return execute(call);
 }
 
 public ResponseEntity<Map<String,Object>> detailPetTour1(Map<String,Object> param){
  Call<Map<String,Object>> call = api.detailPetTour1(param);
  return execute(call);
 }
 
 public ResponseEntity<Map<String,Object>> categoryCode1(Map<String,Object> param){
  Call<Map<String,Object>> call = api.categoryCode1(param);
  return execute(call);
 }
 
 public ResponseEntity<Map<String,Object>> areaBasedList1(Map<String,Object> param){
  Call<Map<String,Object>> call = api.areaBasedList1(param);
  return execute(call);
 }
 
 public ResponseEntity<Map<String,Object>> execute(Call<Map<String,Object>> call) {
    try {
        Response<Map<String,Object>> response = call.execute();
        log.info("locationBasedList1 param > "  + response.toString());
        if (response.isSuccessful()) {
            return new ResponseEntity<>(response.body(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } catch (IOException e) {
        e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
 }
 

}
