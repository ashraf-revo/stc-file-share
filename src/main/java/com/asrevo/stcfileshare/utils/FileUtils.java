package com.asrevo.stcfileshare.utils;

import org.springframework.core.io.buffer.DataBuffer;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class FileUtils {
    public static byte[] getFileContent(List<DataBuffer> content) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (DataBuffer dataBuffer : content) {
            outputStream.writeBytes(dataBuffer.toByteBuffer().array());
        }
        byte[] byteArray = outputStream.toByteArray();
        return byteArray;
    }
}
