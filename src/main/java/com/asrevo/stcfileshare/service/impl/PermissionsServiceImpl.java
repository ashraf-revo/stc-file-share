package com.asrevo.stcfileshare.service.impl;

import com.asrevo.stcfileshare.domain.enumration.PermissionLevel;
import com.asrevo.stcfileshare.repository.PermissionsRepository;
import com.asrevo.stcfileshare.service.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class PermissionsServiceImpl implements PermissionsService {
    @Autowired
    private PermissionsRepository permissionsRepository;

    @Override
    public boolean checkUserAccessOnSpace(User principal, PermissionLevel permissionLevel) {
        return permissionsRepository.checkUserAccessOnSpace(principal.getUsername(), permissionLevel.name());
    }

    @Override
    public boolean checkUserAccessOnSpace(Long spaceId, User principal, PermissionLevel permissionLevel) {
        return permissionsRepository.checkUserAccessOnSpace(spaceId, principal.getUsername(), permissionLevel.name());
    }

    @Override
    public boolean checkUserAccessOnFolder(Long folderId, User principal, PermissionLevel permissionLevel) {
        return permissionsRepository.checkUserAccessOnFolder(folderId, principal.getUsername(), permissionLevel.name());
    }

    @Override
    public boolean checkUserAccessOnFile(Long fileId, User principal, PermissionLevel permissionLevel) {
        return permissionsRepository.checkUserAccessOnFile(fileId, principal.getUsername(), permissionLevel.name());
    }
}
