package spring.exam.web.rest.services;

import org.springframework.stereotype.Service;
import spring.exam.web.rest.models.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PostService {
    List<Post> posts = new ArrayList<>();

    public Optional<Post> get(Integer id) {
        return posts.stream().filter(post -> post.getId() == id).findFirst();
    }

    public Post create(Post post) {
        Post newPost = new Post(new Random().nextInt(), post.getContent());
        posts.add(newPost);
        return newPost;
    }

    public void delete(Integer id) {
        posts.remove(id);
    }


}
