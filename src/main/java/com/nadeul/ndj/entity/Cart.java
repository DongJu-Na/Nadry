package com.nadeul.ndj.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
public class Cart {


	@Id
	@GeneratedValue
	private Integer ctId;

    @OneToMany(mappedBy = "cart", orphanRemoval = true)
    private List<CartProduct> includedProducts = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    private Member member;
}
