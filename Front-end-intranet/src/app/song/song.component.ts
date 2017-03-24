import { Component, OnInit } from '@angular/core';
import {Song} from "../entities/entities";
import {SongService} from "../services/song.service";

@Component({
  selector: 'app-song',
  templateUrl: './song.component.html',
  styleUrls: ['./song.component.css']
})
export class SongComponent implements OnInit {

  songs : Song[];

  showSongs: boolean = false;

  constructor(private songService: SongService) { }

  ngOnInit() {
    this.loadSongs();
  }

  loadSongs() {
    this.songService.getSong().subscribe(
        songs => this.songs = songs,
        err => {
          console.log(err)
        }
    );
  }

  onShowSongs() {
    this.showSongs = !this.showSongs;
  }
}