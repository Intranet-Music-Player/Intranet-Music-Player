import { ModuleWithProviders } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { AuthGuard } from 'app/services/authguard';

import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RegistrationComponent } from './registration/registration.component';
import { PlaylistComponent } from './playlist/playlist.component';
import { SongComponent } from './song/song.component';
import { GenresComponent } from './genres/genres.component';
import { UserComponent } from './user/user.component';
import { SearchComponent } from './search/search.component';
import { ArtistComponent } from './artist/artist.component';
import { AlbumsComponent } from './albums/albums.component';

const APP_ROUTES: Routes = [
    { path: '', component: LoginComponent },
    { path: 'login', component: LoginComponent },
    { path: 'registration', component: RegistrationComponent },
    {
        path: 'home', component: HomeComponent,
        children: [
            { path: 'playlists', component: PlaylistComponent },
            { path: 'songs', component: SongComponent },
            { path: 'upload', component: SongComponent },
            { path: 'genres', component: GenresComponent },
            { path: 'user', component: UserComponent },
            { path: 'search', component: SearchComponent },
            { path: 'artist', component: ArtistComponent },
            { path: 'albums', component: AlbumsComponent },

        ],
        canActivate: [AuthGuard]
    },
];

export const Routing: ModuleWithProviders = RouterModule.forRoot(APP_ROUTES);
