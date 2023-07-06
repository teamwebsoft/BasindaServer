package com.basinda.api.user;

import com.basinda.models.entity.Comment;
import com.basinda.models.response.ResponseHeader;
import com.basinda.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    public class Response extends ResponseHeader{

    }

    @PostMapping("/create")
    public ResponseEntity<Response> createComment(@RequestBody Comment request){
        Response response = new Response();

        String createdComment = commentService.createComment(request);
        if (createdComment.equalsIgnoreCase("created")){
            response.setStatusCode(HttpStatus.CREATED);
            response.setStatus(true);
            response.setContent("Comment created successfully.");
        }
        else{
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setStatus(true);
            response.setContent("Something went wrong please try again.");
        }
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Comment>> getCommentsForSpecificUser(@PathVariable Long userId){
        List<Comment> comments = commentService.findByUserId(userId);
        return ResponseEntity.ok(comments);
    }
}