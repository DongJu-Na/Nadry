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
import com.nadeul.ndj.api.OdliiGuideServiceApi;

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
public class OdliiGuideService {
	
 private static final String BASE_URL = "http://apis.data.go.kr";
 
 
 final OdliiGuideServiceApi api;
 
 public OdliiGuideService() {
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
     
		this.api =  retrofit.create(OdliiGuideServiceApi.class);
	}
 
 public ResponseEntity<Map<String,Object>> themeBasedList(Map<String,Object> param){
	  Call<Map<String,Object>> call = api.themeBasedList(param);
	  return execute(call);
 }

 public ResponseEntity<Map<String,Object>> themeLocationBasedList(Map<String,Object> param){
	  Call<Map<String,Object>> call = api.themeLocationBasedList(param);
	  return execute(call);
}
 
 public ResponseEntity<Map<String,Object>> themeSearchList(Map<String,Object> param){
	  Call<Map<String,Object>> call = api.themeSearchList(param);
	  return execute(call);
}
 
 public ResponseEntity<Map<String,Object>> storyBasedList(Map<String,Object> param){
	  Call<Map<String,Object>> call = api.storyBasedList(param);
	  return execute(call);
}
 
 public ResponseEntity<Map<String,Object>> storyLocationBasedList(Map<String,Object> param){
	  Call<Map<String,Object>> call = api.storyLocationBasedList(param);
	  return execute(call);
}
 
 public ResponseEntity<Map<String,Object>> storySearchList(Map<String,Object> param){
	  Call<Map<String,Object>> call = api.storySearchList(param);
	  return execute(call);
}
 
 public ResponseEntity<Map<String,Object>> themeBaseSyncdList(Map<String,Object> param){
	  Call<Map<String,Object>> call = api.themeBaseSyncdList(param);
	  return execute(call);
}
 
 public ResponseEntity<Map<String,Object>> storyBasedSyncList(Map<String,Object> param){
	  Call<Map<String,Object>> call = api.storyBasedSyncList(param);
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
