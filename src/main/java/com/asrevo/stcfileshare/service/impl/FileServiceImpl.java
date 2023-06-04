package com.asrevo.stcfileshare.service.impl;

import com.asrevo.stcfileshare.domain.File;
import com.asrevo.stcfileshare.domain.Item;
import com.asrevo.stcfileshare.repository.FileRepository;
import com.asrevo.stcfileshare.service.FileService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    @Override
    public File createFile(Item item, byte[] content, HashMap<String, String> meta) {
        File entity = new File();
        entity.setFile(content);
        entity.setItem(item);
        entity.setMeta(meta);
        return fileRepository.save(entity);
    }

    @Override
    public Map<String, String> getMeta(Long itemId) {
        return fileRepository.findMetaByItemId(itemId).stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
