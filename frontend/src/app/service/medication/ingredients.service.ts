import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ingredient } from 'src/app/model/medications/ingredient';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class IngredientService {

  constructor(private http : HttpClient) { }

  findAllIngredients(): Observable<Ingredient[]> {
    return this.http.get<Ingredient[]>(`${environment.baseUrl}/${environment.ingredients}/${environment.findAll}`);
  }

  addIngredient(data : Ingredient) {
    return this.http.post(`${environment.baseUrl}/${environment.ingredients}/${environment.add}`,data, {responseType : 'text'});
  }
  
  getIngredient(id : Number) : Observable<Ingredient>{
    return this.http.get<Ingredient>(`${environment.baseUrl}/${environment.ingredients}/${environment.getById}?id=${id}`);

  }


}
