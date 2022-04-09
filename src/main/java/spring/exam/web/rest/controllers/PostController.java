package spring.exam.web.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.exam.web.rest.models.Post;
import spring.exam.web.rest.services.PostService;


@RestController
@RequestMapping("posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<Post> get(@RequestParam Integer id) {
        return ResponseEntity.ok(postService.get(id).orElse(null));
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody Post post) {
        return ResponseEntity.ok(postService.create(post));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Integer id) {
        postService.delete(id);
        return ResponseEntity.ok().build();
    }


}
