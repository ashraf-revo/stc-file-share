package com.asrevo.stcfileshare.service;

import com.asrevo.stcfileshare.domain.Item;

public interface ItemService {
    Item createItemFile(String fileName, Long folderId);

    Item findOne(Long itemId);

    Item createItemSpace(String name);

    Item createItemFolder(String name, Item parent);
}
