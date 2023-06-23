package com.basinda.services;

import com.basinda.entities.Comment;

import java.util.List;

public interface CommentService {
    String createComment(Comment request);
    List<Comment> findByUserId(Long userId);
}