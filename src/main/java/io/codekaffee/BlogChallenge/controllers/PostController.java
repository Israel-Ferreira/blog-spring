package io.codekaffee.BlogChallenge.controllers;


import io.codekaffee.BlogChallenge.models.Post;
import io.codekaffee.BlogChallenge.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(path = "/posts")
@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;


    @GetMapping(path = "/")
    public List<Post> allPosts(){
        return postRepository.findAll();
    }

    @GetMapping(path = "/{postId}")
    public  Post getPost(@PathVariable("postId") Long id){
        return postRepository.findById(id).get();
    }

    @PostMapping(path = "/")
    public String createPost(@RequestBody Post post){
        postRepository.save(post);
        return "Post criado com sucesso";
    }

    @PutMapping(path = "/{postId}" )
    public Post updatePost(@PathVariable("postId") Long id, @RequestBody Post post){
        Post oldPost =  postRepository.findById(id).get();

        oldPost.setContent(post.getContent());
        oldPost.setDescription(post.getDescription());
        oldPost.setTitle(post.getTitle());

        return postRepository.save(oldPost);
    }


    @DeleteMapping(path = "/{postId}")
    public void deletePost(@PathVariable("postId") Long id){
        Post post = postRepository.findById(id).get();
        postRepository.delete(post);
    }
}
