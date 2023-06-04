package com.asrevo.stcfileshare.repository;

import com.asrevo.stcfileshare.domain.File;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface FileRepository extends CrudRepository<File, Long> {
    @Query(value = """
            select fmt.key as key , fmt.value as value from public.file_meta_mapping fmt where id=:itemId
            """, nativeQuery = true)
    List<Map.Entry<String, String>> findMetaByItemId(@Param("itemId") Long itemId);
}
