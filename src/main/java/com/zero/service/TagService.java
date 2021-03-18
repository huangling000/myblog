package com.zero.service;

import com.zero.po.Tag;
import com.zero.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TagService {
    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Page<Tag> listTag(Pageable pageable);

    Tag getTagByName(String name);

    Tag updateTag(Long id, Tag tag);

    void deletedTag(Long id);
}
