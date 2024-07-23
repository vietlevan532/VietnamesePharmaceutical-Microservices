package com.zezanziet.pharmaceutical.vn.ms.user_service.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    private ObjectId categoryId;
    private String categoryName;
    private Boolean status;
    private Date createAt;
    private Date updateAt;
}
