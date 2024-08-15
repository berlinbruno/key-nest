package dev.berlinbruno.key_nest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "secrets")
public class Secret {

    @Id
    private String id;
    private String secretName;
    private String name;
    private String value;
    private String category;
    private String notes;
    private String userId;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date lastModifiedAt;
}