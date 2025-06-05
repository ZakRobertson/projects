import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpotifystatsComponent } from './spotifystats.component';

describe('SpotifystatsComponent', () => {
  let component: SpotifystatsComponent;
  let fixture: ComponentFixture<SpotifystatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SpotifystatsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SpotifystatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
