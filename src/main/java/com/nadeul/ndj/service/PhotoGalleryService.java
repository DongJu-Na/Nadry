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
import com.nadeul.ndj.api.PhotoGalleryServiceApi;

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
public class PhotoGalleryService {
	
 private static final String BASE_URL = "http://apis.data.go.kr";
 
 
 final PhotoGalleryServiceApi api;
 
 public PhotoGalleryService() {
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
     
		this.api =  retrofit.create(PhotoGalleryServiceApi.class);
	}
 
 public ResponseEntity<Map<String,Object>> galleryList1(Map<String,Object> param){
	  Call<Map<String,Object>> call = api.galleryList1(param);
	  return execute(call);
 }
 
 public ResponseEntity<Map<String,Object>> gallerySearchList1(Map<String,Object> param){
	  Call<Map<String,Object>> call = api.gallerySearchList1(param);
	  return execute(call);
 }
 
 public ResponseEntity<Map<String,Object>> galleryDetailList1(Map<String,Object> param){
	  Call<Map<String,Object>> call = api.galleryDetailList1(param);
	  return execute(call);
 }
 
 public ResponseEntity<Map<String,Object>> gallerySyncDetailList1(Map<String,Object> param){
	  Call<Map<String,Object>> call = api.gallerySyncDetailList1(param);
	  return execute(call);
 }

 
 public ResponseEntity<Map<String,Object>> execute(Call<Map<String,Object>> call) {
    try {
        Response<Map<String,Object>> response = call.execute();
        log.info(this.getClass().toString() + " param > "  + response.toString());
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
