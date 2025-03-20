import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { PostService } from './services/post.service';
import { Post } from './models/post';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, FormsModule, HttpClientModule],
  template: `
    <div class="container">
      <header>
        <h1>Angular app.</h1>
      </header>

      <div class="content">
        <div class="selector">
          <select
            [(ngModel)]="selectedId"
            (change)="loadPost()"
            class="id-select"
          >
            <option *ngFor="let id of availableIds" [value]="id">
              {{ id }}
            </option>
          </select>
        </div>

        <div class="post-container" *ngIf="post">
          <ul class="post-details">
            <li><strong>ID:</strong> {{ post.id }}</li>
            <li><strong>USERID:</strong> {{ post.userId }}</li>
            <li><strong>TITLE:</strong> {{ post.title }}</li>
            <li><strong>BODY:</strong> {{ post.body }}</li>
          </ul>
        </div>

        <div *ngIf="loading">Loading...</div>
        <div *ngIf="error" class="error">
          Error loading post. Please try again.
        </div>
      </div>
    </div>
  `,
  styles: [
    `
      .container {
        max-width: 800px;
        margin: 0 auto;
        font-family: Arial, sans-serif;
      }

      header {
        text-align: center;
        margin-bottom: 20px;
      }

      header h1 {
        font-size: 2.5rem;
      }

      .selector {
        display: flex;
        justify-content: center;
        margin-bottom: 20px;
      }

      .id-select {
        padding: 8px;
        width: 300px;
        font-size: 1rem;
      }

      .post-container {
        border: 1px solid #ccc;
        padding: 20px;
        margin-top: 10px;
      }

      .post-details {
        list-style-type: disc;
        padding-left: 20px;
      }

      .post-details li {
        margin-bottom: 10px;
        font-size: 1.1rem;
      }

      .error {
        color: red;
        text-align: center;
      }
    `,
  ],
})
export class AppComponent implements OnInit {
  availableIds = [1, 2, 3];
  selectedId = 1;
  post: Post | null = null;
  loading = false;
  error = false;

  constructor(private postService: PostService) {}

  ngOnInit() {
    this.loadPost();
  }

  loadPost() {
    this.loading = true;
    this.error = false;
    this.post = null;

    this.postService.getPostById(this.selectedId).subscribe({
      next: (data) => {
        this.post = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading post:', err);
        this.error = true;
        this.loading = false;
      },
    });
  }
}
