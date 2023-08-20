package com.nadeul.ndj.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductType {

    REFRIGERATED("냉장"),
    FROZEN("냉동"),
    ROOM("상온");

    private final String title;

}