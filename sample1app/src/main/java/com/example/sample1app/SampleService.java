package com.example.sample1app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.sample1app.repositories.PostRepository;

@Service
public class SampleService {
    private String baseUrl = "https://jsonplaceholder.typicode.com/posts";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PostRepository postRepository;

    public Post[] getAllPosts() {
        return restTemplate.getForObject(baseUrl, Post[].class);
    }

    public Post getPost(int id) {
        String url = baseUrl + "/" + id;
        return restTemplate.getForObject(url, Post.class);
    }

    public Object[] getLocalPost() {
        return postRepository.findAll().toArray();
    }

    @SuppressWarnings("null")
    public Post getAndSavePost(int id) {
        String url = baseUrl + "/" + id;
        Post post = restTemplate.getForObject(url, Post.class);

        // 新しいエンティティを作成し、APIデータをコピー
        Post newPost = new Post();
        newPost.setTitle(post.getTitle());
        newPost.setBody(post.getBody());
        newPost.setUserId(post.getUserId());

        postRepository.save(newPost);
        return post;
    }

    // public Post getPost() {
    // return new Post(0, 0, "Dummy", "This is sample.");
    // }
}