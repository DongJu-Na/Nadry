package com.nadeul.ndj.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Location {
    SEOUL(1, "서울","1"),
    INCHEON(2, "인천","2"),
    DAEJEON(3, "대전","3"),
    DAEGU(4, "대구","4"),
    GWANGJU(5, "광주","5"),
    BUSAN(6, "부산","6"),
    ULSAN(7, "울산","7"),
    SEJONG(8, "세종특별자치시","8"),
    GYEONGGI(9, "경기도","31"),
    GANGWON(10, "강원특별자치도","32"),
    CHUNGBUK(11, "충청북도","33"),
    CHUNGNAM(12, "충청남도","34"),
    GYEONGBUK(13, "경상북도","35"),
    GYEONGNAM(14, "경상남도","36"),
    JEONBUK(15, "전라북도","37"),
    JEONNAM(16, "전라남도","38"),
    JEJU(17, "제주도","39");
	
    private final int code;
    private final String name;
    private final String areaCode;
    
    public static Location getByAreaCode(String areaCode) {
        for (Location location : values()) {
        	if (location.getAreaCode().equals(areaCode)) {
                return location;
            }
        }
        return null;
    }
    
    public static int getCodeByAreaCode(String areaCode) {
        for (Location location : values()) {
            if (location.getAreaCode().equals(areaCode)) {
                return location.getCode();
            }
        }
        return -1;
    }
    
}
