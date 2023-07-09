package com.basinda.services;

import com.basinda.models.entity.Comment;
import com.basinda.models.request.user.SearchRequest;

import java.util.List;

public interface SearchService {
    List<Comment> searchComment(SearchRequest request);
}