package com.nadeul.ndj.entity;

import com.nadeul.ndj.enums.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Order {

	@Id
	@GeneratedValue
	private Integer odId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    /*
    @NotNull
    @ManyToOne(fetch = LAZY)
    private Cart cart;

    @NotNull
    @OneToOne(fetch = LAZY)
    private Address address;

    @ManyToOne(fetch = LAZY)
    private Member member;
    */
}
