package com.zezanziet.pharmaceutical.vn.ms.user_service.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "shops")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {
    @Id
    private ObjectId shopId;
    private String shopName;
    private Double star;
    private Boolean favorite;
    private Boolean active; // "selling" or "stop selling"
    private String status; // "sold", "not sold", "ever sold"
    private Date createAt;
    private Date updateAt;
    // nganh hang, sao, brand,...
}
