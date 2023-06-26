package com.nadeul.ndj.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
  * @FileName : MapUtil.java
  * @Project : ndj
  * @Date : 2023. 6. 26. 
  * @작성자 : ndj
  * @변경이력 :
  * @프로그램 설명 : Map Util 모음
  */
public class MapUtil<S> {
	
	public static <S> S class2Map(Object obj , Class<S>type2){
		ObjectMapper om = new ObjectMapper();
		S s = om.convertValue(obj, type2);
		return s;
	}
	


}
