package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.entities.*;
import com.example.repository.*;
import com.example.requsest.AlbumRequest;
import com.example.requsest.GenereRequest;
import com.example.requsest.SongRequest;
import com.example.requsest.UserRequest;
import com.example.response.UserResponse;
@Controller
@EnableWebMvc
@RequestMapping(path="/app")
public class MainController extends WebMvcConfigurerAdapter {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PlaylistRepository playlistRepository;
	@Autowired
	private GenereRepository genereRepository;
	@Autowired
	private BandRepository bandRepository;
	@Autowired
	private ArtistRepository artistRepository;
	@Autowired
	private AlbumRepository albumRepository;
	@Autowired
	private SongRepository songRepository;
	@Autowired
	private ValorationRepository valorationRepository;
	
	@RequestMapping(path = "/registration", method = RequestMethod.POST, produces="Application/json" , consumes= "Application/json")
	public @ResponseBody UserResponse addNewUser(@RequestBody UserRequest userRequest) {
		UserResponse response = new UserResponse();
		if (userRepository.findByUserlogin(userRequest.getUserlogin()) == null) {
			if (userRepository.findByEmail(userRequest.getEmail()) == null) {
				User u = new User(userRequest.getUsername(), userRequest.getUserlogin(), userRequest.getPassword(),
						userRequest.getEmail());
				userRepository.save(u);
				response.setMessage("WELCOME TO OUR APP");
				response.setSuccess(true);
				return response;
			} else {
				response.setMessage("EMAIL IS ALREADY USED");
				response.setSuccess(false);
				return response;
			}
		} else {
			response.setMessage("LOGIN IS ALREADY USED");
			response.setSuccess(false);
			return response;
		}
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST, produces = "Application/json", consumes = "Application/json")
	public @ResponseBody UserResponse checkUserLogin(@RequestBody UserRequest userRequest) {

		User log = userRepository.findByUserlogin(userRequest.getUserlogin());
		UserResponse response = new UserResponse();

		if (log != null && log.getUserlogin().equals(userRequest.getUserlogin())
				&& log.getPassword().equals(userRequest.getPassword())) {
			System.out.println(log.getUserlogin());
			return response.loginCorrect(userRequest);
		} else {
			response.setMessage("LOGIN or PASSWORD WRONG");
			response.setSuccess(false);
			return response;
		}
	}
	
	@GetMapping(path="/genere/add")
	public @ResponseBody String addNewGenere ( @RequestParam String genereName){
		
		Genere newG = new Genere();
		if (( genereRepository.findByGenereName(genereName))!= null){
			return "GENERE ALREADY EXISTS";
		} else {
			newG.setGenereName(genereName);
			genereRepository.save(newG);
			return "NEW GENERE SAVED";
		}
	}
	
	@GetMapping(path = "/playlist/add")
	public @ResponseBody String addNewPlaylist( // @RequestParam String
												// nameSong,
			@RequestParam String userlogin, @RequestParam String playlistName) {

		if (playlistRepository.findByName(playlistName) != null) {
			Playlist newPlay = playlistRepository.findByName(playlistName);

			// Song song = songRepository.findByNameSong(nameSong);
			// song.addPlaylist(newPlay);
			User owner = userRepository.findByUserlogin(userlogin);
			if (owner != null) {
				owner.addPlaylist(newPlay);
				playlistRepository.save(newPlay);
				return "YOU ARE FOLLOWING A PLAYLIST";
			} else {
				return "USER DON'T EXISTS";
			}

		} else {

			Playlist newPlay = new Playlist();

			newPlay.setPlaylistName(playlistName);
			// newPlay.setPlaylistDuration(duration);
			// Song song = songRepository.findByNameSong(nameSong);
			// song.addPlaylist(newPlay);
			User owner = userRepository.findByUserlogin(userlogin);
			owner.addPlaylist(newPlay);
			playlistRepository.save(newPlay);

			return "NEW PLAYLIST ADDED";
		}

	}
	
	@GetMapping(path = "/playlist/addSong")
	public @ResponseBody String addSongPlaylist(@RequestParam String playlistName, @RequestParam String songName) {

		if (playlistRepository.findByName(playlistName) == null) {
			return "PLAYLIST NOT EXISTS";
		} else {
			if (songRepository.findByNameSong(songName) == null) {
				return "SONG NOT EXISTS";
			} else {
				Playlist p = playlistRepository.findByName(playlistName);
				Song s = songRepository.findByNameSong(songName);
				p.addSong(s);

				Float duration = 0F;
				for (int i = 0; i < p.getSongs().size(); i++) {
					duration = duration + p.getSongs().get(i).getDurationSong();
				}
				p.setPlaylistDuration(duration);

				playlistRepository.save(p);
				return "SONG ADDED";
			}
		}
	}
	
	@RequestMapping(path = "/newSong", method = RequestMethod.POST, produces="Application/json" , consumes= "Application/json")
	public  @ResponseBody String addNewSong(@RequestBody SongRequest songRequest, @RequestBody AlbumRequest albumRequest, @RequestBody GenereRequest genereRequest) {	
//		if (  albumRepository.findByNameAlbum(albumRequest.getNameAlbum()) != null ){
			
//			Album album = albumRepository.findByNameAlbum(albumRequest.getNameAlbum());
//			Song song = songRepository.findByNameSong(songRequest.getNameSong());
				
//				if ( album.getAlbumsongs().contains(song)) {
//					return "ALBUM CONTAINS THE SONG";
//				
//				} else {
					Genere owner = genereRepository.findByGenereName(genereRequest.getGenereName());
					Song newSong = new Song(songRequest.getNameSong(), songRequest.getDurationSong(), owner);
//					album.addSong(newSong);
					newSong.setGenere(owner);
					songRepository.save(newSong);
					return "SONG ADDED TO THE ALBUM";
//				}
//		} else {
//			return " ALBUM DOESN'T EXIST";
//		}
	}
	
	
	
	@RequestMapping(path = "/users", method = RequestMethod.GET, produces="Application/json")
	public @ResponseBody Iterable<User> listAllUsers (){
		return userRepository.findAll();
	}
	
	@RequestMapping(path = "/playlists", method = RequestMethod.GET, produces="Application/json")
	public @ResponseBody Iterable<Playlist> listAllPlaylist( ){
		return playlistRepository.findAll();
	}
	
	@RequestMapping(path = "/generes", method = RequestMethod.GET, produces="Application/json")
	public @ResponseBody Iterable<Genere> listAllGeneres(){
		return genereRepository.findAll();
	}
	
	
	@RequestMapping(path = "/bands", method = RequestMethod.GET, produces="Application/json")
	public @ResponseBody Iterable<Band> listAllBands(){
		return bandRepository.findAll();
	}
	
	@RequestMapping(path = "/artists", method = RequestMethod.GET, produces="Application/json")
	public @ResponseBody Iterable<Artist> listAllArtist(){
		return artistRepository.findAll();
	}
	
	@GetMapping(path="/albums")
	public @ResponseBody Iterable<Album> listAllAlbum(){
		return albumRepository.findAll();
	}
	
	@RequestMapping(path = "/songs", method = RequestMethod.GET, produces="Application/json")
	public @ResponseBody Iterable<Song> listAllSong(){
		return songRepository.findAll();
	}
	
}
