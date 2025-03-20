import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { PostService } from '../../services/post.service';
import { Post } from '../../models/post';

@Component({
  selector: 'app-post-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.css'],
})
export class PostDetailComponent implements OnInit {
  post: Post | null = null;
  loading = true;
  error = false;

  constructor(
    private route: ActivatedRoute,
    private postService: PostService
  ) {}

  ngOnInit(): void {
    // URLからpostのIDを取得
    this.route.params.subscribe((params) => {
      const id = +params['id']; // '+'で文字列を数値に変換

      // PostServiceを使ってデータを取得
      this.postService.getPostById(id).subscribe({
        next: (data) => {
          this.post = data;
          this.loading = false;
        },
        error: (err) => {
          console.error('Error fetching post:', err);
          this.error = true;
          this.loading = false;
        },
      });
    });
  }
}
