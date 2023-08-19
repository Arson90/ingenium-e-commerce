import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import * as AuthSelector from 'src/app/stores/selectors/auth.selectors';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {
  username$: Observable<string | null>;

  constructor(private store: Store) {
    this.username$ = this.store.select(AuthSelector.getUsername);
  }
  ngOnInit(): void {
  }

}
