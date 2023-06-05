package com.asrevo.stcfileshare.service;

import com.asrevo.stcfileshare.domain.enumration.PermissionLevel;
import org.springframework.security.core.userdetails.User;

public interface PermissionsService {
    boolean checkUserAccessOnSpace( User principal, PermissionLevel permissionLevel);
    boolean checkUserAccessOnSpace(Long spaceId, User principal, PermissionLevel permissionLevel);

    boolean checkUserAccessOnFolder(Long folderId, User principal, PermissionLevel permissionLevel);

    boolean checkUserAccessOnFile(Long fileId, User principal, PermissionLevel permissionLevel);
}
