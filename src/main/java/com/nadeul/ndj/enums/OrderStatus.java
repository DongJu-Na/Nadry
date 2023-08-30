package com.nadeul.ndj.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    CREATED("주문생성"),
    PAID("결제완료"),
    PROCESSING("상품준비중"),
    SHIPPING("배송중"),
    DELIVERED("배송완료"),
    CANCELED("주문취소");

    private final String title;
}
