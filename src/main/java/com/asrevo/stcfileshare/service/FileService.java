package com.asrevo.stcfileshare.service;

import com.asrevo.stcfileshare.domain.File;
import com.asrevo.stcfileshare.domain.Item;

import java.util.HashMap;
import java.util.Map;

public interface FileService {
    File createFile(Item file, byte[] content, HashMap<String, String> headers);

    Map<String, String> getMeta(Long itemId);
}
