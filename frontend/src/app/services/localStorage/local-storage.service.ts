import { Injectable } from '@angular/core';

export enum LocalStorageKeys {
  AUTH_STATE = '__auth_state__',
  CART_STATE = '__cart_state__',
  CHECKOUT_STATE = '__checkout_state__',
  JWT_KEY = '__jwt_key__'
}

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {
  constructor() { }

  setState(state: any, localStorageKey: string) {
    console.log("setState -> " + JSON.stringify(state))
    localStorage.setItem(localStorageKey, JSON.stringify(state));
  }

  getState(localStorageKey: string) {
    const storageValue = localStorage.getItem(localStorageKey);
    if (storageValue) {
      try {
        return JSON.parse(storageValue);
      } catch {
        localStorage.removeItem(localStorageKey);
      }
    }
  }

  public get jwt(): string {
    return localStorage.getItem(LocalStorageKeys.JWT_KEY) ?? '';
  }

  public set jwt(value: string) {
    localStorage.setItem(LocalStorageKeys.JWT_KEY, value);
  }
}


