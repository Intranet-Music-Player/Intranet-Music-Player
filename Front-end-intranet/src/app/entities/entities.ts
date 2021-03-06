export class User {
   userlogin :string ;
   password :string ;
   email : string ;
   username : string ;
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
    genereN : string;
}

export class Album {
    datapub : string;
    nameAlbum : string;
    bandId : number;
}

