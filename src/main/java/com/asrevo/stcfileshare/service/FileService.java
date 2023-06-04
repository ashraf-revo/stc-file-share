package com.asrevo.stcfileshare.service;

import com.asrevo.stcfileshare.domain.File;
import com.asrevo.stcfileshare.domain.Item;

public interface FileService {
    File createFile(Item file, byte[] content);
}
