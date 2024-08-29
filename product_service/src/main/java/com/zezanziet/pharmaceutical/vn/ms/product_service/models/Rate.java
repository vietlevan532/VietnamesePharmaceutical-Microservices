package com.zezanziet.pharmaceutical.vn.ms.product_service.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rate")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "reviewer")
    private Long userId; // set user email into this property

    @NotNull
    @Column(name = "number_of_star")
    private byte numberOfStar;

    @Column(name = "feedback_text")
    private String comment;

    @Column(name = "file_url")
    private String feedbackFileUrl;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
