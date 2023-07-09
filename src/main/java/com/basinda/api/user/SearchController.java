package com.basinda.api.user;

import com.basinda.models.entity.Comment;
import com.basinda.models.response.ResponseHeader;
import com.basinda.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.basinda.models.request.user.SearchRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    public class Response extends ResponseHeader{
        List<Comment> reviews;
    }

    @PostMapping("/search")
    public ResponseEntity<Response> searchReview(@RequestBody SearchRequest request){
        Response response = new Response();
        List<Comment> reviews = searchService.searchComment(request);
        response.reviews = reviews;
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}