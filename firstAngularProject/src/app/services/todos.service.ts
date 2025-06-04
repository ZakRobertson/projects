import { Injectable, inject } from '@angular/core';
import { Todo } from '../model/todo.type';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TodosService {
  http = inject(HttpClient);
  getTodosFromApi() { 
    const url = 'https://jsonplaceholder.typicode.com/todos';
    return this.http.get<Array<Todo>>(url); 
    /*
    sends an HTTP GET request to the specified URL, which returns an API, often a JSON file.
    the get method will take this file and return it as an observable.
    in this case, it will be an array of Todo objects, but in general whatever type
    is specified in the generic type parameter will be returned.
    */
  }
}
