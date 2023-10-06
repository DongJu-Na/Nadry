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
import com.nadeul.ndj.api.VilageFcstInfoServiceApi;

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
public class VilageFcstInfoService {
	
 private static final String BASE_URL = "https://apis.data.go.kr";
 
 
 final VilageFcstInfoServiceApi api;
 
 public VilageFcstInfoService() {
     ObjectMapper mapper = new ObjectMapper();
			      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			      mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);

     OkHttpClient client = new OkHttpClient.Builder()
             .connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS))
             .readTimeout(ofSeconds(30).toMillis(), TimeUnit.MILLISECONDS)
             .build();

     Retrofit retrofit = new Retrofit.Builder()
             .baseUrl(BASE_URL)
             .client(client)
             .addConverterFactory(JacksonConverterFactory.create(mapper))
             .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
             .build();
     
		this.api =  retrofit.create(VilageFcstInfoServiceApi.class);
	}
 
 public ResponseEntity<Map<String,Object>> getUltraSrtNcst(Map<String,Object> param){
	  Call<Map<String,Object>> call = api.getUltraSrtNcst(param);
	  return execute(call);
 }

 public ResponseEntity<Map<String,Object>> getUltraSrtFcst(Map<String,Object> param){
	  Call<Map<String,Object>> call = api.getUltraSrtFcst(param);
	  return execute(call);
 }

 public ResponseEntity<Map<String,Object>> getVilageFcst(Map<String,Object> param){
	  Call<Map<String,Object>> call = api.getVilageFcst(param);
	  return execute(call);
 }
 
 public ResponseEntity<Map<String,Object>> getFcstVersion(Map<String,Object> param){
	  Call<Map<String,Object>> call = api.getFcstVersion(param);
	  return execute(call);
 }
 
 public ResponseEntity<Map<String,Object>> execute(Call<Map<String,Object>> call) {
    try {
    	log.info(this.getClass().toString() + " url > "  + call.request().url());
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
