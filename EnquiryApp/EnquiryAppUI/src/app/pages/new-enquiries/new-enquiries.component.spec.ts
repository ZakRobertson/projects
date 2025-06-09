import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewEnquiriesComponent } from './new-enquiries.component';

describe('NewEnquiriesComponent', () => {
  let component: NewEnquiriesComponent;
  let fixture: ComponentFixture<NewEnquiriesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewEnquiriesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewEnquiriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
