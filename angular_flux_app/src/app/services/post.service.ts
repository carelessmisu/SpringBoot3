import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from '../models/post';

@Injectable({
  providedIn: 'root',
})
export class PostService {
  // private apiUrl = 'http://localhost:8080'; // バックエンドAPIのURL
  private apiUrl = '/api';

  constructor(private http: HttpClient) {}

  // 指定されたIDのPostを取得するメソッド
  getPostById(id: number): Observable<Post> {
    return this.http.get<Post>(`${this.apiUrl}/post/${id}`);
  }
}
