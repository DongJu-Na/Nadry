package com.nadeul.ndj.dto;


import java.time.LocalDateTime;

import com.nadeul.ndj.entity.Point;
import com.nadeul.ndj.entity.PointHistory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointHistoryDto {
	
	@Schema(description = "포인트 히스토리 id")
	private Integer phId;
	@Schema(description = "포인트 사용일자")
	private LocalDateTime useDate;
	@Schema(description = "사용 포인트")
	private Integer usePoint;
	@Schema(description = "사용한 사람")
	private String usedBy;
	@Schema(description = "상품")
	private ProductDto product;
	@Schema(description = "포인트")
	private PointDto point;
	@Schema(description = "고객정보")
	private MemberDto member;
	@Schema(description = "비고")
	private String remarks;
	
	public PointHistoryDto(PointHistory ph) {
		this.phId = ph.getPhId();
		this.useDate = ph.getUseDate();
		this.usePoint = ph.getUsePoint();
		this.usedBy = ph.getUsedBy();
		this.product = new ProductDto(ph.getProduct());
		this.point = new PointDto(ph.getPoint());
		this.member = new MemberDto(ph.getMember());
		this.remarks = ph.getRemarks();
	}
	
}
