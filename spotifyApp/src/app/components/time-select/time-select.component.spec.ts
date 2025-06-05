import { ComponentFixture, TestBed } from '@angular/core/testing';

import {TimeSelectComponent } from './time-select.component';

describe('YearSelectComponent', () => {
  let component: TimeSelectComponent;
  let fixture: ComponentFixture<TimeSelectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TimeSelectComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TimeSelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
