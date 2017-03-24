import { ModuleWithProviders } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RegistrationComponent } from './registration/registration.component';
import { PlaylistComponent } from './playlist/playlist.component';
import { SongComponent } from './song/song.component';
import { GenresComponent } from './genres/genres.component';

const APP_ROUTES: Routes = [
    {path: 'home' , component : HomeComponent},
    {path: 'login' , component : LoginComponent},
    {path: 'registration' , component : RegistrationComponent},
    {path: 'playlist' , component : PlaylistComponent},
    {path: 'songs' , component : SongComponent},
    {path: 'genres' , component : GenresComponent },
    {path: 'songs' , component : SongComponent }
];

export const Routing: ModuleWithProviders = RouterModule.forRoot(APP_ROUTES);
