package com.asrevo.stcfileshare.repository;

import com.asrevo.stcfileshare.domain.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
