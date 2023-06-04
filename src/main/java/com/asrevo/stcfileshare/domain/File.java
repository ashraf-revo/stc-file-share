package com.asrevo.stcfileshare.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class File implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Lob
    private byte[] file;
    @OneToOne
    @JoinColumn
    @MapsId
    private Item item;
    @ElementCollection
    @CollectionTable(name = "file_meta_mapping", joinColumns = {@JoinColumn(name = "id", referencedColumnName = "item_id")})
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private Map<String, String> meta;
}
