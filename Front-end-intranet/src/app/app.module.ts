import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { Routing } from 'app/app.routing'
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { Ng2FilterPipeModule } from 'ng2-filter-pipe';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { SongComponent } from './song/song.component';
import { GenresComponent } from './genres/genres.component';
import { HomeComponent } from './home/home.component';
import { RegistrationComponent } from './registration/registration.component';
import { PlaylistComponent } from './playlist/playlist.component';

import { UserService } from './services/user.service';
import { PlaylistService } from './services/playlist.service';
import { GenresService } from './services/genres.service';
import { SongService } from './services/song.service';
import { AuthGuard } from 'app/services/authguard';
import { UserComponent } from './user/user.component';
import { SearchComponent } from './search/search.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    RegistrationComponent,
    PlaylistComponent,
    SongComponent,
    GenresComponent,
    SongComponent,
    UserComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    Routing,
    Ng2SearchPipeModule,
    Ng2FilterPipeModule
  ],
  providers: [UserService, PlaylistService, GenresService , SongService ,AuthGuard ],
  bootstrap: [AppComponent]
})
export class AppModule { }
