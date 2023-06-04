package com.asrevo.stcfileshare.service;

import com.asrevo.stcfileshare.domain.File;

import java.util.HashMap;

public interface ItemFileService {
    File save(Long folderId, String fileName, byte[] content, HashMap<String, String> headers);
}
