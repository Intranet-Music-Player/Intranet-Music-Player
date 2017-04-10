package com.example;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.Iterator;

/*04.04.217*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.entities.*;
import com.example.repository.*;
import com.example.requsest.PlaylistRequest;
import com.example.requsest.SongRequest;
import com.example.requsest.UserRequest;
import com.example.responses.UserResponse;
import com.example.responses.*;

@Controller
@EnableWebMvc
@RequestMapping(path = "/app")
public class MainController extends WebMvcConfigurerAdapter {
	// KO B
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

	// USER ADD ---> WORKING FINE
	@RequestMapping(path = "/registration", method = RequestMethod.POST, produces = "Application/json", consumes = "Application/json")
	public @ResponseBody UserResponse addNewUser(@RequestBody UserRequest userRequest) {
		UserResponse response = new UserResponse();
		if (userRepository.findByUserlogin(userRequest.getUserlogin()) == null) {
			if (userRepository.findByEmail(userRequest.getEmail()) == null) {
				User u = new User(userRequest.getName(), userRequest.getUserlogin(), userRequest.getPassword(),
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
		System.err.println(userRequest.getUserlogin() + "-----" + userRequest.getPassword());
		User log = userRepository.findByUserlogin(userRequest.getUserlogin());
		UserResponse response = new UserResponse();

		if (log != null && log.getUserlogin().equals(userRequest.getUserlogin())
				&& log.getPassword().equals(userRequest.getPassword())) {
			response.setUser(log);
			response.setMessage("SUCCESFULL");
			response.setSuccess(true);
			return response;
		} else {
			response.setMessage("LOGIN OR PASSWORD WRONG");
			response.setSuccess(false);
			return response;
		}
	}

	@RequestMapping(path = "/userlogin", method = RequestMethod.POST, produces = "Application/json", consumes = "Application/json")
	public @ResponseBody UserResponse getCurrentUser(@RequestBody UserRequest userRequest) {
		UserResponse u = new UserResponse();
		User currentUser = userRepository.findByUserlogin(userRequest.getUserlogin());
		u.setUser(currentUser);
		return u;
	}

	@RequestMapping(path = "/removePlaylist", method = RequestMethod.POST, produces = "Application/json", consumes = "Application/json")
	public @ResponseBody Response removeUserPlaylist(@RequestBody PlaylistRequest removeRequest) {
		System.out.println(removeRequest.getUserlogin() + "--//--" + removeRequest.getPlaylistId());

		User us = userRepository.findByUserlogin(removeRequest.getUserlogin());
		Playlist remove = playlistRepository.findOne(removeRequest.getPlaylistId());

		us.getPlaylists().remove(remove);
		userRepository.save(us);

		Response r = new Response();
		r.setMessage("PLAYLIST DELETED");
		r.setSuccess(true);
		return r;
	}

	@RequestMapping(path = "/followPlaylist", method = RequestMethod.POST, produces = "Application/json", consumes = "Application/json")
	public @ResponseBody Response userFollowPlaylist(@RequestBody PlaylistRequest followRequest) {
		Response r = new Response();
		User owner = userRepository.findByUserlogin(followRequest.getUserlogin());
		Playlist p = playlistRepository.findOne(followRequest.getPlaylistId());
		if (owner.getPlaylists().contains(p)) {
			r.setMessage("YOU ARE ALREADY FOLLOWING THIS PLAYLIST");
			r.setSuccess(false);
			return r;
		} else {
			r.setSuccess(true);
			r.setMessage("NOW YOU FOLLOW THIS PLAYLIST");
			owner.getPlaylists().add(p);
			userRepository.save(owner);
			return r;
		}

	}

	@RequestMapping(path = "/users", method = RequestMethod.GET, produces = "Application/json")
	public @ResponseBody Iterable<User> listAllUsers() {
		return userRepository.findAll();
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST, produces = "Application/json", consumes = "Application/json")
	public @ResponseBody SearchResponse searchData(@RequestBody String searchValue) {

		System.out.println(searchValue);
		ArrayList<Song> s = (ArrayList<Song>) songRepository.findAll();
		ArrayList<Artist> a = (ArrayList<Artist>) artistRepository.findAll();

		SearchResponse match = new SearchResponse(s, a, null, null);
		return match;
	}

	/**********************************************************************************/
	// PLAYLIST ADD ---> WORKING FINE
	@RequestMapping(path = "/playlist/add", method = RequestMethod.POST, produces = "Application/json", consumes = "Application/json")
	public @ResponseBody Response addNewPlaylist(@RequestBody PlaylistRequest playlistRequest) {
		Response r = new Response();
		System.out.println(playlistRequest.getName() + "/////" + playlistRequest.getUserlogin());
		if (playlistRepository.findByName(playlistRequest.getName()) == null) {
			Playlist p = new Playlist();
			User owner = userRepository.findByUserlogin(playlistRequest.getUserlogin());

			p.setPlaylistName(playlistRequest.getName());
			playlistRepository.save(p);
			owner.getPlaylists().add(p);
			r.setMessage("PLAYLIST CREATED CORRECTLY");
			r.setSuccess(true);
			userRepository.save(owner);
			return r;
		} else {
			r.setMessage("SOMETHING GONE WRONG");
			r.setSuccess(false);
			return r;
		}
		// if (playlistRepository.findByName(playlistName) != null) {
		// Playlist newPlay = playlistRepository.findByName(playlistName);
		//
		// // Song song = songRepository.findByNameSong(nameSong);
		// // song.addPlaylist(newPlay);
		// User owner = userRepository.findByUserlogin(userlogin);
		// if (owner != null) {
		// owner.addPlaylist(newPlay);
		// playlistRepository.save(newPlay);
		// return "YOU ARE FOLLOWING A PLAYLIST";
		// } else {
		// return "USER DON'T EXISTS";
		// }
		//
		// } else {
		//
		// Playlist newPlay = new Playlist();
		//
		// newPlay.setPlaylistName(playlistName);
		// // newPlay.setPlaylistDuration(duration);
		// // Song song = songRepository.findByNameSong(nameSong);
		// // song.addPlaylist(newPlay);
		// User owner = userRepository.findByUserlogin(userlogin);
		// owner.addPlaylist(newPlay);
		// playlistRepository.save(newPlay);
		//
		// return "NEW PLAYLIST ADDED";
		// }

	}

	// PLAYLIST LIST ---> WORKING FINE
	@RequestMapping(path = "/playlists", method = RequestMethod.GET, produces = "Application/json")
	public @ResponseBody Iterable<Playlist> listAllPlaylist() {
		return playlistRepository.findAll();
	}

	/**********************************************************************************/
	// GENERE ADD ---> WORKING FINE
	@GetMapping(path = "/genere/add")
	public @ResponseBody String addNewGenere(@RequestParam String genereName) {

		Genere newG = new Genere();
		if ((genereRepository.findByGenereName(genereName)) != null) {
			return "GENERE ALREADY EXISTS";
		} else {
			newG.setGenereName(genereName);
			genereRepository.save(newG);
			return "NEW GENERE SAVED";
		}
	}

	// GENERE LIST WORKING FINE
	@RequestMapping(path = "/generes", method = RequestMethod.GET, produces = "Application/json")
	public @ResponseBody Iterable<Genere> listAllGeneres() {
		return genereRepository.findAll();
	}

	/**********************************************************************************/
	// BAND ADD ---> WORKING FINE
	@GetMapping(path = "/band/add")
	public @ResponseBody String addNewBand(@RequestParam String name) {

		Band newB = new Band();
		if ((bandRepository.findByName(name)) != null) {
			return "BAND ALREADY EXISTS";
		} else {
			newB.setName(name);
			bandRepository.save(newB);
			return "NEW BAND SAVED";
		}

	}

	// BAND LIST ---> WORKING FINE
	@RequestMapping(path = "/bands", method = RequestMethod.GET, produces = "Application/json")
	public @ResponseBody Iterable<Band> listAllBands() {
		return bandRepository.findAll();
	}

	/**********************************************************************************/
	// ARTIST ADD ---> WORKING FINE
	@GetMapping(path = "/artist/add")
	public @ResponseBody String addNewArtist(@RequestParam String bandName, @RequestParam String name,
			@RequestParam String birth) {

		Artist newA = new Artist();
		if ((artistRepository.findByName(name)) != null) {
			return "ARTIST ALREADY EXISTS";
		} else {
			newA.setName(name);
			newA.setBirth(birth);
			Band owner = bandRepository.findByName(bandName);
			if (owner == null) {
				return "BAND DONT'T EXISTS";
			} else {
				newA.setBand(owner);
				// newA.setBand(bandRepository.findByName(bandName));
				artistRepository.save(newA);
				return "NEW ARTIST SAVED";
			}
		}
	}

	// ARTIST LIST ---> WORKING FINE
	@RequestMapping(path = "/artists", method = RequestMethod.GET, produces = "Application/json")
	public @ResponseBody Iterable<Artist> listAllArtist() {
		return artistRepository.findAll();
	}

	/**********************************************************************************/
	// ALBUM ADD ---> WORKING FINE
	@GetMapping(path = "/album/add")
	public @ResponseBody String addNewAlbum(@RequestParam String nameAlbum, @RequestParam String datePub,
			@RequestParam String bandName) {
		Album newAlbum = new Album();
		newAlbum.setNameAlbum(nameAlbum);
		newAlbum.setDatePub(datePub);
		if (bandRepository.findByName(bandName) == null) {
			return "THERE IS NO BAND";

		} else {
			Band owner = bandRepository.findByName(bandName);
			newAlbum.setBand(owner);
			albumRepository.save(newAlbum);
			return "NEW ALBUM SAVED";
		}
	}

	// ALBUM LIST ---> WORKING FINE
	@GetMapping(path = "albums")
	public @ResponseBody Iterable<Album> listAllAlbum() {
		return albumRepository.findAll();
	}

	/**********************************************************************************/
	// NEEDS REVISONS
	@RequestMapping(path = "/newSong", method = RequestMethod.POST, produces = "Application/json", consumes = "Application/json")
	public @ResponseBody SongResponse addNewSong(@RequestBody SongRequest songRequest) {
		// if ( albumRepository.findByNameAlbum(albumRequest.getNameAlbum()) !=
		// null ){

		// Album album =
		// albumRepository.findByNameAlbum(albumRequest.getNameAlbum());
		// Song song = songRepository.findByNameSong(songRequest.getNameSong());

		// if ( album.getAlbumsongs().contains(song)) {
		// return "ALBUM CONTAINS THE SONG";
		//
		// } else {
		SongResponse response = new SongResponse();
		if (songRepository.findByNameSong(songRequest.getNameSong()) == null) {

			Genere owner = genereRepository.findByGenereName(songRequest.getGenereN());
			Song newSong = new Song(songRequest.getNameSong(), songRequest.getDurationSong(), owner);
			// album.addSong(newSong);
			newSong.setGenere(owner);
			songRepository.save(newSong);
			response.setMessage("SONG ADDED");
			response.setSuccess(true);

			return response;

		} else {
			response.setMessage("SONG EXIST");
			response.setSuccess(false);
			return response;
		}
		// }
		// } else {
		// return " ALBUM DOESN'T EXIST";
		// }
	}

	@RequestMapping(path = "/songs", method = RequestMethod.GET, produces = "Application/json")
	public @ResponseBody Iterable<Song> listAllSong() {
		return songRepository.findAll();
	}

	/**********************************************************************************/
	/**********************************************************************************/
	// PLAYLIST VALORATION --> WORKING FINE
	@GetMapping(path = "/playlist/valoration")
	public @ResponseBody String valoratePlaylist(@RequestParam String userlogin, @RequestParam String playlistName,
			@RequestParam Long points) {
		Playlist newPlay = playlistRepository.findByName(playlistName);
		User usr = userRepository.findByUserlogin(userlogin);
		ValorationPk pk = new ValorationPk();
		pk.setUser(usr.getUserId());
		pk.setPlaylist(newPlay.getPlaylistId());
		Valoration opinion = new Valoration();
		opinion.setId(pk);
		opinion.setPoints(points);
		valorationRepository.save(opinion);
		return "THANKS FOR YOUR VALORATION";

	}

	// ADD SONGS TO PLAYLIST --->WORKING FINE
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

				int duration = 0;
				for (int i = 0; i < p.getSongs().size(); i++) {
					duration = duration + p.getSongs().get(i).getDurationSong();
				}
				p.setPlaylistDuration(duration);

				playlistRepository.save(p);
				return "SONG ADDED";
			}
		}
	}

	// WE CAN ADD SOME OTHER EXTRAS LIKE
	// ADD ALBUM TO PLAYLIST
	// VALORATION TO SONG ( we now have valoration in playlist )
	// ...
}
