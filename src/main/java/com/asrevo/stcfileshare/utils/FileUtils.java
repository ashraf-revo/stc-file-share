package com.asrevo.stcfileshare.utils;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.multipart.FilePart;

import java.io.ByteArrayOutputStream;
import java.util.AbstractMap;
import java.util.HashMap;
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

    public static HashMap<String, String> extractHeaders(FilePart file) {
        List<AbstractMap.SimpleEntry<String, String>> list = file.headers().entrySet().stream()
                .filter(it -> it.getValue().size() == 1)
                .map(it -> new AbstractMap.SimpleEntry<>(it.getKey(), it.getValue().get(0))).toList();

        HashMap<String, String> headers = new HashMap<>();

        for (AbstractMap.SimpleEntry<String, String> it : list) {
            headers.put(it.getKey(), it.getValue());
        }
        return headers;
    }

}
