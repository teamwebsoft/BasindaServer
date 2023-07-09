package com.basinda.services.impl;

import com.basinda.models.entity.Comment;
import com.basinda.repositories.CommentRepository;
import com.basinda.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.basinda.models.request.user.SearchRequest;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> searchComment(SearchRequest request) {
        return commentRepository.findByOwnerId(request.getSearchedField());
    }
}