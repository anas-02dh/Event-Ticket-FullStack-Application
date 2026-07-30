import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category } from '../models/category';
import { environment } from '../../../environment/environment';
import { CategoryRequest } from '../models/category-request';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

private apiUrl = `${environment.apiUrl}/categories`;

  constructor(
    private http: HttpClient
  ) {}

  getCategories(): Observable<Category[]> {

    return this.http.get<Category[]>(
      this.apiUrl
    );

  }

  saveCategory(
    category: CategoryRequest
  ): Observable<Category> {

    return this.http.post<Category>(
      this.apiUrl,
      category
    );

  }

  updateCategory(
    id: string,
    category: CategoryRequest
  ): Observable<Category> {

    return this.http.put<Category>(
      `${this.apiUrl}/${id}`,
      category
    );

  }

  deleteCategory(
    id: string
  ): Observable<void> {

    return this.http.delete<void>(
      `${this.apiUrl}/${id}`
    );

  }

}