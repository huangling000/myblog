package com.zero.service;

import com.zero.po.Blog;
import com.zero.po.Tag;
import com.zero.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface BlogService {
    Blog getBlog(Long id);

    Blog getAndConvert(Long id);

    Blog getBlogByTitle(String title);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(Long tagId,Pageable pageable);
    Page<Blog> listBlog(Pageable pageable);

    List<Blog> listRecommendBlogTop(Integer size);

    Page<Blog> listBlog(String query,Pageable pageable);

    Blog saveBlog(Blog blog);
    Map<String,List<Blog>> archiveBlog();
    Blog updateBlog(Long id, Blog blog);
    Long countBlog();
    void deleteBlog(Long id);
}
