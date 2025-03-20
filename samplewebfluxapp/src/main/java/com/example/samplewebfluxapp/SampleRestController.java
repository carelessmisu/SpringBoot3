package com.example.samplewebfluxapp;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class SampleRestController {

  @Autowired
  private PostRepository postRepository;

  @PostConstruct
  public void init() {
    // データベースをクリアしてから再作成する方法
    postRepository.deleteAll();
    postRepository.flush();

    // 初期データを作成
    Post p1 = new Post();
    p1.setUserId(1);
    p1.setTitle("Hello");
    p1.setBody("Hello FLux!");

    Post p2 = new Post();
    p2.setUserId(2);
    p2.setTitle("Sample");
    p2.setBody("This is sample post.");

    Post p3 = new Post();
    p3.setUserId(3);
    p3.setTitle("ハロー");
    p3.setBody("これはサンプルです。");

    // IDを指定せずに保存（自動生成させる）
    postRepository.save(p1);
    postRepository.save(p2);
    postRepository.save(p3);
    postRepository.flush();
  }

  @RequestMapping("/")
  public String hello() {
    return "Hello Flux!";
  }

  @RequestMapping("/flux")
  public Mono<String> flux() {
    return Mono.just("Hello Flux (Mono).");
  }

  @RequestMapping("/flux2")
  public Flux<String> flux2() {
    return Flux.just("Hello Flux.", "これはFluxのサンプルです。");
  }

  // @RequestMapping("/post")
  // public Mono<Post> post() {
  // Post post = new Post(0, 0, "dummy", "dummy message...");
  // return Mono.just(post);
  // }
  @RequestMapping("/post/{id}")
  public Mono<Post> post(@PathVariable("id") int id) {
    Post post = postRepository.findById(id);
    return Mono.just(post);
  }

  @RequestMapping("/posts")
  public Flux<Object> posts() {
    List<Post> posts = postRepository.findAll();
    return Flux.fromArray(posts.toArray());
  }
}
