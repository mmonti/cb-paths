package com.example.model;

import com.couchbase.client.java.repository.annotation.Field;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

/**
 * Created by mmonti on 11/7/16.
 */
@Document
@AllArgsConstructor
@Getter
public class Path {

    @Id
    private String id;

    @Field
    private String parent = null;
    @Field
    private String path = null;
    @Field
    private String child = null;


}
