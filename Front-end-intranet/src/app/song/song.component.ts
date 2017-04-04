import { Component, OnInit } from '@angular/core';
import { Playlist , Song } from './../entities/entities';
import { SongService } from './../services/song.service';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-song',
  templateUrl: './song.component.html',
  styleUrls: ['./song.component.css']
})
export class SongComponent implements OnInit {
  songs : Song[];

  constructor(private songService : SongService) { }

  ngOnInit() {
    this.loadSongs();
  }

  loadSongs(){
    this.songService.getSongs().subscribe(
          songs => this.songs = songs , 
          err => { console.log(err)});
  }

  addNewSong(newSong : any){
    this.songService.addNewSong(newSong).subscribe( err => console.log("OK"));
    this.loadSongs();
    console.log(newSong);
  }

}
function handleError(error: any) {
  let errorMsg = error.message || 'ERROR -1-0-1'
  console.error(errorMsg);
  return Observable.throw(errorMsg);
}