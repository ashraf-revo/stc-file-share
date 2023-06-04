package com.asrevo.stcfileshare.controller.v1;

import com.asrevo.stcfileshare.domain.Item;
import com.asrevo.stcfileshare.domain.enumration.ItemType;
import com.asrevo.stcfileshare.service.FileService;
import com.asrevo.stcfileshare.service.ItemFileService;
import com.asrevo.stcfileshare.service.ItemService;
import com.asrevo.stcfileshare.utils.FileUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping(FileApiV1.PATH)
public class FileApiV1 {
    public final static String PATH = "/api/file/v1/";
    private final ItemService itemService;
    private final ItemFileService itemFileService;
    private final FileService fileService;

    public FileApiV1(ItemService itemService, ItemFileService itemFileService, FileService fileService) {
        this.itemService = itemService;
        this.itemFileService = itemFileService;
        this.fileService = fileService;
    }

    @PostMapping(value = "upload/{folderId}", consumes = MULTIPART_FORM_DATA_VALUE)
    public Mono<Void> upload(@PathVariable("folderId") Long folderId, @RequestPart("file") FilePart file) {
        HashMap<String, String> meta = FileUtils.extractHeaders(file);
        return file.content().collectList().map(FileUtils::getFileContent).map(it -> itemFileService.save(folderId, file.filename(), it, meta)).then();

    }

    @GetMapping(value = "download/{itemId}")
    public ResponseEntity<InputStreamResource> download(@PathVariable("itemId") Long itemId) {
        Item item = itemService.findOne(itemId);
        Assert.isTrue(ItemType.FILE.equals(item.getItemType()), "download should only call file item");
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + item.getName()).body(new InputStreamResource(new ByteArrayInputStream(item.getFile().getFile())));
    }

    @GetMapping(value = "meta/{itemId}")
    public Map<String, String> getMeta(@PathVariable("itemId") Long itemId) {
        return fileService.getMeta(itemId);
    }


}
