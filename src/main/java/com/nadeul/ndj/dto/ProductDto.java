package com.nadeul.ndj.dto;


import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
	/** exif.js 메타데이터 값들 보고 엔티티 , 컬럼값들도 변경 필요함   
	* 
	**/
	@Schema(description = "상품 번호" , example = "1")
    private Integer pdId;
	@Schema(description = "상품명" , example = "1")
    private String name;
	@Schema(description = "재고" , example = "1")
    private Integer stockQty;
	@Schema(description = "썸네일 이미지 url" , example = "1")
    private String thumbnailUrl;
	@Schema(description = "짧은 상품 설명" , example = "맛있는 고구마")
    private String shortDescription;
	@Schema(description = "상품타입" , example = "REFRIGERATED")
    private String productType;
    @Schema(description = "포인트" , example = "2000")
    private Integer point;
    @Schema(description = "등록일" , example = "2023-08-21")
    private LocalDateTime createDate;
    @Schema(description = "등록자" , example = "홍길동")
    private String createBy;
    @Schema(description = "수정일" , example = "2023-08-21")
    private LocalDateTime updateDate;
    @Schema(description = "수정자" , example = "홍길동")
    private String updateBy;
    @Schema(description = "상품 상세 설명" , example = "고구마는 정말 맛있다")
    private String description;
	
}
