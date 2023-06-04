package com.asrevo.stcfileshare.service.impl;

import com.asrevo.stcfileshare.domain.File;
import com.asrevo.stcfileshare.domain.Item;
import com.asrevo.stcfileshare.service.FileService;
import com.asrevo.stcfileshare.service.ItemFileService;
import com.asrevo.stcfileshare.service.ItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ItemFileServiceImpl implements ItemFileService {
    @Autowired
    private FileService fileService;
    @Autowired
    private ItemService itemService;

    @Transactional
    @Override
    public File save(Long folderId, String fileName, byte[] content, HashMap<String, String> meta) {
        Item item = itemService.createItemFile(fileName, folderId);
        return fileService.createFile(item, content, meta);
    }
}
