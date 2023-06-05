package com.asrevo.stcfileshare.configration;

import com.asrevo.stcfileshare.domain.Item;
import com.asrevo.stcfileshare.service.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;

import static com.asrevo.stcfileshare.domain.enumration.PermissionLevel.EDIT;
import static com.asrevo.stcfileshare.domain.enumration.PermissionLevel.VIEW;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    private PermissionsService permissionsService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object target) {
        User principal = (User) authentication.getPrincipal();
        return switch (targetDomainObject.toString()) {
            case PermissionsDefinitions.CREATE_SPACE -> permissionsService.checkUserAccessOnSpace(principal, EDIT);
            case PermissionsDefinitions.CREATE_FOLDER ->
                    permissionsService.checkUserAccessOnSpace(((Item) target).getParent().getId(), principal, EDIT);
            case PermissionsDefinitions.CREATE_File ->
                    permissionsService.checkUserAccessOnFolder(((Long) target), principal, EDIT);
            case PermissionsDefinitions.VIEW_FILE ->
                    permissionsService.checkUserAccessOnFile(((Long) target), principal, VIEW);
            default -> false;
        };
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
