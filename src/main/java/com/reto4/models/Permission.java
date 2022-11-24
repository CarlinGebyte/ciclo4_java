package com.reto4.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Data
@Document(collection = "permission")
public class Permission {
    @Id
    private String _id;
    private String url;
    private String method;
}
