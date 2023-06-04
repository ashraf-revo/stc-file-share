package com.asrevo.stcfileshare.service.impl;

import com.asrevo.stcfileshare.domain.Item;
import com.asrevo.stcfileshare.domain.enumration.ItemType;
import com.asrevo.stcfileshare.repository.ItemRepository;
import com.asrevo.stcfileshare.service.ItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    @Override
    public Item createItemFile(String fileName, Long folderId) {
        Optional<Item> getFolder = itemRepository.findById(folderId);
        Item folder = getFolder.orElseThrow(() -> new RuntimeException("did not find this folder"));
        Item item = new Item();
        item.setItemType(ItemType.FILE);
        item.setName(fileName);
        item.setParent(folder);
        return itemRepository.save(item);
    }

    @Override
    public Item findOne(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("can't find this item"));
    }

    @Transactional
    @Override
    public Item createItemSpace(String name) {
        Item item = new Item();
        item.setItemType(ItemType.SPACE);
        item.setName(name);
        return itemRepository.save(item);
    }

    @Transactional
    @Override
    public Item createItemFolder(String name, Item parent) {
        Item one = itemRepository.findById(parent.getId()).orElseThrow(() -> new RuntimeException("parent can not be found"));
        Item item = new Item();
        item.setItemType(ItemType.FOLDER);
        item.setParent(one);
        item.setName(name);
        return itemRepository.save(item);
    }
}
