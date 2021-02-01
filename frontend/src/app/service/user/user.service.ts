import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '@environments/environment';
import { User } from '@app/models';

@Injectable({ providedIn: 'root' })
export class UserService {
    constructor(private http: HttpClient) { }

    findAll() {
        return this.http.get<User[]>(`${environment.baseUrl}/users`);
    }
    getById(id: number) {
    return this.http.get<User>(`${environment.baseUrl}/users/${id}`);
    }
}
