package com.basinda.services.impl;

import com.basinda.entities.User;
import com.basinda.entities.Comment;
import com.basinda.services.CommentService;
import org.springframework.stereotype.Service;
import com.basinda.repositories.UserRepository;
import com.basinda.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public String createComment(Comment request) {
        Optional<User> user = userRepository.findById(request.getUserId());
        if (user != null){
            Optional<User> owner = userRepository.findById(request.getOwnerId());
            if (owner != null){
                Comment comment = commentRepository.save(request);
                if (comment != null){
                    return "Created";
                }
                else{
                    return "Something went wrong.";
                }
            }
            else{
                return "Please make your comment user not found.";
            }
        }
        else{
            return "User not found.";
        }
    }

    @Override
    public List<Comment> findByUserId(Long userId) {
        return commentRepository.findByUserId(userId);
    }
}