package com.asrevo.stcfileshare.service.impl;

import com.asrevo.stcfileshare.domain.File;
import com.asrevo.stcfileshare.domain.Item;
import com.asrevo.stcfileshare.repository.FileRepository;
import com.asrevo.stcfileshare.service.FileService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    @Override
    public File createFile(Item item, byte[] content) {
        File entity = new File();
        entity.setFile(content);
        entity.setItem(item);
        return fileRepository.save(entity);
    }
}
