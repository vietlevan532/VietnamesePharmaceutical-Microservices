package com.zezanziet.pharmaceutical.vn.ms.product_service.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
@SecondaryTable(name = "product_image", pkJoinColumns = @PrimaryKeyJoinColumn(name = "product_id"))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "name")
    private String productName;

    @NotNull
    @Size(max = 50)
    @Column(name = "price")
    private String price;

    @Column(name = "image_url", table = "product_image")
    private String image;

    @Column(name = "product_status")
    private boolean status; // in stock is true, sold out is false

    @NotNull
    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "list_rate")
    @OneToMany(mappedBy = "product")
    private List<Rate> rates;

    @Column(name = "sold")
    private String sold;

    @Column(name = "is_favorite")
    private boolean isFavorite;

    @Column(name = "shop_id")
    private Long shopId;
}
