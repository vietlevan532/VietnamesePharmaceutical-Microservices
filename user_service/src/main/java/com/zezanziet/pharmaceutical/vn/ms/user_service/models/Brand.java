package com.zezanziet.pharmaceutical.vn.ms.user_service.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "brands")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
    @Id
    private ObjectId brandId;
    private String brandName;
    private Boolean status;
    private Date createAt;
    private Date updateAt;
}
