package com.asrevo.stcfileshare.repository;

import com.asrevo.stcfileshare.domain.File;
import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<File, Long> {
}
