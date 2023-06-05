package com.asrevo.stcfileshare.repository;

import com.asrevo.stcfileshare.domain.Permissions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PermissionsRepository extends CrudRepository<Permissions, Long> {
    //    check user can view or editor can edit on space
    @Query(value = """
            select p.*
                from permissions p
                      join permission_group pg on p.permission_group_id = pg.id
            where p.permission_level = :permissionLevel
              and p.user_email = :userEmail
                        """, nativeQuery = true)
    boolean checkUserAccessOnSpace(@Param("userEmail") String userEmail, @Param("permissionLevel") String permissionLevel);
    //    check user can view or editor can edit on space
    @Query(value = """
            select count(p.id)=1 as premited
            from permissions p
                      join permission_group pg on p.permission_group_id = pg.id
                      join item space on (pg.id = space.permission_group_id and space.id = :spaceId and space.item_type = 'SPACE')
            where p.permission_level = :permissionLevel
              and p.user_email = :userEmail
            """, nativeQuery = true)
    boolean checkUserAccessOnSpace(@Param("spaceId") Long spaceId, @Param("userEmail") String userEmail, @Param("permissionLevel") String permissionLevel);

    //    check user can view or editor can edit on folder
    @Query(value = """
            with parent as (select space.*
                            from item folder
                                      join item space on folder.parent_id = space.id
                            where folder.item_type = 'FOLDER'
                              and folder.id = :folderId)

            select count(p.id) = 1 as premited
            from permissions p
                      join permission_group pg on p.permission_group_id = pg.id
                      join parent pt on pt.permission_group_id = pg.id
            where p.permission_level = :permissionLevel
              and p.user_email = :userEmail
                                    """, nativeQuery = true)
    boolean checkUserAccessOnFolder(@Param("folderId") Long folderId, @Param("userEmail") String userEmail, @Param("permissionLevel") String permissionLevel);

    //    check user can view or editor can edit on file
    @Query(value = """
            with parent as (select space.*
                            from item file
                                      join item folder on file.parent_id = folder.id
                                      join item space on folder.parent_id = space.id
                            where file.id = :fileId
                              and file.item_type = 'FILE'
                              and folder.item_type = 'FOLDER'
                              and space.item_type = 'SPACE')

            select count(p.id) = 1 as premited
            from permissions p
                      join permission_group pg on p.permission_group_id = pg.id
                      join parent pt on pt.permission_group_id = pg.id
            where p.permission_level = :permissionLevel
              and p.user_email = :userEmail
                                    """, nativeQuery = true)
    boolean checkUserAccessOnFile(@Param("fileId") Long fileId, @Param("userEmail") String userEmail, @Param("permissionLevel") String permissionLevel);
}
