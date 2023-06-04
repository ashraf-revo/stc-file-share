package com.asrevo.stcfileshare.service;

import com.asrevo.stcfileshare.domain.File;

public interface ItemFileService {
    File save(Long folderId, String fileName, byte[] content);
}
