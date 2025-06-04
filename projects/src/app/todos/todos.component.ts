import { Component, inject, OnInit, signal } from '@angular/core';
import { TodosService } from '../services/todos.service';
import { Todo } from '../model/todo.type';
import { catchError } from 'rxjs/operators';
import { TodoItemComponent } from '../components/todo-item/todo-item.component';
import { FormsModule } from '@angular/forms';
import { FilterTodosPipe } from '../pipes/filter-todos.pipe';
//this import is a TS import, which allows us to use the TodoItemComponent in this component.

@Component({
  selector: 'app-todos',
  imports: [TodoItemComponent, FormsModule, FilterTodosPipe], //this allows us to use the TodoItemComponent in the template
  templateUrl: './todos.component.html',
  styleUrl: './todos.component.scss'
})
export class TodosComponent implements OnInit {
  todoService = inject(TodosService);
   //injecting the TodosService allows us to access its methods, namely getTodosFromApi
  
   todoItems = signal<Array<Todo>>([]);
  //creates a signal that holds an empty array of Todo objects.

   searchTerm = signal('');

  ngOnInit(): void {
    this.todoService.getTodosFromApi() 
    .pipe(
      /*
      the pipe operator allows us to chain operators together, in this case catchError, 
      but we could also use map, filter, etc. to process the data in order before it is passed to the subscribe method.

      pipe allows us to use RxJS operators on an observable, which are unable to be 
      used directly on the observable. In this case, even though there is only one operator,
      catchError, we still need to use pipe to chain it to the observable returned by getTodosFromApi.
       */
      
       catchError((error) => { //catches any errors that occur during the API call
        console.log(error);
        throw error;
      }
      )
    ) 
    .subscribe((todos) => {
      this.todoItems.set(todos);
    })
    /* 
    subscribe is used to process the data from an observable.
    an observable is a stream of data that can be processed asynchronously.
    in this case however, the stream is just a single array of Todo objects returned from the API call.
    */
  }

  updateTodoItem(todoItem: Todo) {
    this.todoItems.update((localTodos) => { 
      // the update method replaces the current value of the signal with a new value.
      //localTodos is a local variable of the current value of the todoItems signal.
      return localTodos.map(todo => {
        //maps function to every todo in the localTodos array and returns a new array.
        if (todo.id === todoItem.id) {
          return { ...todo, completed: !todoItem.completed };
          /*
          this syntax is called the spread operator, which creates a
          new object with all the properties of the original todo object.
          completed: !todoItem.completed changes the completed property of this new object
          */
        }
        return todo;
      })
    })
  }
  /* this function updates the completed status of a to-do item by replacing the entire list with 
  a new identical one but changing the completed status of the toggled item. While it seems inefficient,
  The reason for this is to preserve the data of the previous list, that way if you want to undo you can 
  more easily. It also ensures that the components reacting to the updated signal will know that the signal 
  has changed. */
}
