import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { Routing } from 'app/app.routing'

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RegistrationComponent } from './registration/registration.component';
import { PlaylistComponent } from './playlist/playlist.component';

import { UserService } from './services/user.service';
import { PlaylistService } from './services/playlist.service';
import { GenresService } from './services/genres.service';
import { SongComponent } from './song/song.component';
import { GenresComponent } from './genres/genres.component';
import {SongService} from "./services/song.service";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    RegistrationComponent,
    PlaylistComponent,
    SongComponent,
    GenresComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    Routing
  ],
  providers: [UserService, PlaylistService, GenresService, SongService],
  bootstrap: [AppComponent]
})
export class AppModule { }
