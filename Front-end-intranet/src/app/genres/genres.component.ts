import { Component, OnInit } from '@angular/core';
import { GenresService } from '../services/genres.service';
import { Genres } from '../entities/entities';
@Component({
  selector: 'app-genres',
  templateUrl: './genres.component.html',
  styleUrls: ['./genres.component.css']
})
export class GenresComponent implements OnInit {
  genres: Genres[];
  constructor(private genresService: GenresService) { }

  ngOnInit() {
    this.loadGenres()
  }

  loadGenres() {
    this.genresService.getGenres()
        .subscribe(
            genres => this.genres = genres ,
            err => { console.log(err)});
  }

}
