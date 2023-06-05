package com.asrevo.stcfileshare.domain;

import com.asrevo.stcfileshare.domain.enumration.ItemType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private ItemType itemType;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    @JsonBackReference
    private PermissionGroup permissionGroup;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Item parent;
    @OneToOne(mappedBy = "item")
    private File file;
}
