export class User {

  username : string ;
  userlogin :string ;
  email : string ;
  password :string ;

}

export class Playlist {

  playlistDuration : number;
  playlistName : string;
  playlistId : number;
  songs : Song[];

}

export class Genres {

  genereId : number;
  genereName : string;
  genereImage : string;

}

export class Song {

  songId : number;
  nameSong : string;
  durationSong : number;

}
