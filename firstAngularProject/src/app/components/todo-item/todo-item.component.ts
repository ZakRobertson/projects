import { Component, input, output } from '@angular/core';
import { Todo } from '../../model/todo.type';
import { HighlightCompletedTodosDirective } from '../../directives/highlight-completed-todos.directive';
import { UpperCasePipe } from '@angular/common';

@Component({
  selector: 'app-todo-item',
  imports: [HighlightCompletedTodosDirective], 
  // imports the HighlightCompletedTodosDirective which applies styles based on the todo's completion status
  templateUrl: './todo-item.component.html',
  styleUrl: './todo-item.component.scss'
})
export class TodoItemComponent {
  todo = input.required<Todo>(); 
  // takes todo item in when <app-todo-item> is used in parent component, and stores it in a signal.
  // The input is required, so it must be provided by the parent component when the selector is used.

  todoToggle = output<Todo>(); 
  // output allows the component to emit an event when the todo item is clicked.
  // in this case, it emits a todo object 

  todoClicked() {
    this.todoToggle.emit(this.todo());
  }
  // this method is called in the template as a response the the (change) event
  //it emits the same todo item that was passed in as input after toggling the .completed attribute.
}
