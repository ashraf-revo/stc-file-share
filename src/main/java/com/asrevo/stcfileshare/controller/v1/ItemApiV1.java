package com.asrevo.stcfileshare.controller.v1;

import com.asrevo.stcfileshare.domain.Item;
import com.asrevo.stcfileshare.service.ItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ItemApiV1.PATH)
public class ItemApiV1 {
    public final static String PATH = "/api/item/v1/";
    private final ItemService itemService;


    public ItemApiV1(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("space")
    @PreAuthorize("hasPermission('CREATE_SPACE',#item)")
    public Mono<Item> createSpace(@RequestBody Item item) {
        Assert.hasText(item.getName(), "name should not be empty when creating space");
        return Mono.justOrEmpty(itemService.createItemSpace(item.getName()));
    }

    @PostMapping("folder")
    @PreAuthorize("hasPermission('CREATE_FOLDER',#item)")
    public Mono<Item> createFolder(@RequestBody Item item) {
        Assert.hasText(item.getName(), "name should not be empty when creating folder");
        Assert.notNull(item.getParent(), "parent should not be null when creating folder");
        Assert.notNull(item.getParent().getId(), "parent's id should not be null when creating folder");
        return Mono.justOrEmpty(itemService.createItemFolder(item.getName(), item.getParent()));
    }

}
