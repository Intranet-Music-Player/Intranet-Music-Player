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

const APP_ROUTES: Routes = [
    { path: '', component: LoginComponent },
    { path: 'login', component: LoginComponent },
    { path: 'registration', component: RegistrationComponent },
    {
        path: 'home', component: HomeComponent,
        children: [
            { path: 'user', component: UserComponent },
            { path: 'playlists', component: PlaylistComponent },
            { path: 'songs', component: SongComponent },
            { path: 'genres', component: GenresComponent }
        ],
        canActivate: [AuthGuard]
    },
];

export const Routing: ModuleWithProviders = RouterModule.forRoot(APP_ROUTES);