import { Directive, input, effect, inject,ElementRef } from '@angular/core';

@Directive({
  selector: '[appHighlightCompletedTodos]'
})
export class HighlightCompletedTodosDirective {
  isCompleted = input(false);
  //takes a boolean input when the selector is used in a template, stored in a signal, which updates every time the list element is called.
  //the isCompleted signal is bound to the completed property of the todo item in the parent component, so it updates 
  //automatically when the todo is toggled.
  constructor() { }
  el = inject(ElementRef)
  // injects the ElementRef to access the DOM element this directive is applied to. 
  // This lets us manipulate the styles of an HTML element directly with the effect function.
  stylesEffect = effect(() => {  
    if (this.isCompleted()) {
      this.el.nativeElement.style.textDecoration = 'line-through';
      this.el.nativeElement.style.backgroundColor = '#d3f9d8';
      this.el.nativeElement.style.color = '#6c757d';
    }
    else {
      this.el.nativeElement.style.textDecoration = 'none';
      this.el.nativeElement.style.backgroundColor = '#fff';
      this.el.nativeElement.style.color = '#000';
    }
    // the effect function changes the style of the element in the DOM wherever it is called, in this case 
    // the <li> element in the todo-item.component.html template. 
  })
}
