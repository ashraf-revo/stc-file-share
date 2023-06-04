package com.asrevo.stcfileshare.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionGroup implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String groupName;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "permissionGroup")
    private List<Item> items;
}
