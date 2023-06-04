package com.asrevo.stcfileshare.domain;

import com.asrevo.stcfileshare.domain.enumration.PermissionLevel;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Permissions implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String userEmail;
    @Enumerated
    private PermissionLevel permissionLevel;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private PermissionGroup permissionGroup;
}
