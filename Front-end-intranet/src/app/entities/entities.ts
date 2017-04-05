<<<<<<< HEAD
=======
export class User {

   userlogin :string ;
   password :string ;
   email : string ;
   username : string ;
} ;
>>>>>>> 0b3dc2e8e3585190f58622a35b8397026068bddd

export class Playlist {
    playlistDuration : number;
    playlistName : string;
    playlistId : number;
    songs : Song[];
} ;

export class User {
    
   userlogin :string ;
   password :string ;
   email : string ;
   username : string ;
   playlists : Playlist;
} ;
export class Genres {
    genereId : number;
    genereName : string;
    genereImage : string;
} ;

export class Song {
    songId : number;
    nameSong : string;
    durationSong : number;
    genereN : string;
} ;
