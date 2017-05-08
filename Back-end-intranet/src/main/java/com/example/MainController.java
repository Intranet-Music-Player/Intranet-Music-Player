package com.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.entities.*;
import com.example.repository.*;
import com.example.requsest.*;
import com.example.responses.*;

@Controller
@EnableWebMvc
@RequestMapping(path = "/app")
public class MainController extends SpringBootServletInitializer {

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

	@RequestMapping(path = "/registration", method = RequestMethod.POST, produces = "Application/json", consumes = "Application/json")
	public @ResponseBody UserResponse addNewUser(@RequestBody UserRequest userRequest) {
		UserResponse response = new UserResponse();
		if (userRepository.findByUserlogin(userRequest.getUserlogin()) == null) {
			if (userRepository.findByEmail(userRequest.getEmail()) == null) {
				String encryptedPassword = hashPassword(userRequest.getPassword());
				User u = new User(userRequest.getName(), userRequest.getUserlogin(), encryptedPassword,
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

	private String hashPassword(String passwordToHash) {
		String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			// Add password bytes to digest
			md.update(passwordToHash.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest();
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST, produces = "Application/json", consumes = "Application/json")
	public @ResponseBody UserResponse checkUserLogin(@RequestBody UserRequest userRequest) {
		User log = userRepository.findByUserlogin(userRequest.getUserlogin());
		UserResponse response = new UserResponse();
		String encryptedPassword = hashPassword(userRequest.getPassword());
		if (log != null && log.getUserlogin().equals(userRequest.getUserlogin())
				&& log.getPassword().equals(encryptedPassword)) {
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

	@RequestMapping(value = "/file", method = RequestMethod.POST, consumes = "multipart/form-data")
	@ResponseBody
	public Response uploadSong(@RequestPart("uploadFile") MultipartFile file,
			@RequestPart("fileInfo") SongRequest songInfo) throws IOException{
		System.out.println("-------------------------------");
		System.out.println("1--" + file.getName());
		System.out.println("2--" + file.getContentType());
		System.out.println("3--" + file.getOriginalFilename());
		System.out.println("4--" + file.getSize());
		System.out.println("-------------------------------");
		System.out.println("FILE INFO----------------------");
		System.out.println("1--" + songInfo.getGenereN());
		System.out.println("2--" + songInfo.getDurationSong());

		Response s = new Response();
		if (createFileRepo(file)) {
			Song newS = new Song();
			Genere g = genereRepository.findByGenereName(songInfo.getGenereN());
			newS.setDurationSong(songInfo.getDurationSong());
			newS.setGenere(g);
			newS.setNameSong(file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf('.')));
			songRepository.save(newS);
			s.setSuccess(true);
		} else {
			s.setSuccess(false);
		}
		return s;
	}

	private boolean createFileRepo(MultipartFile file) throws IOException {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		String fileName = file.getOriginalFilename();
		File newFile = new File("songs/" + fileName);

		try {
			inputStream = file.getInputStream();
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			outputStream = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping(path = "/addSongToPlaylist", method = RequestMethod.POST, produces = "Application/json", consumes = "Application/json")
	public @ResponseBody Response addSongToPlaylist(@RequestBody PlaylistRequest songToPlaylist) {
		Response r = new Response();

		Playlist p = playlistRepository.findOne(songToPlaylist.getPlaylistId());
		Song s = songRepository.findOne(songToPlaylist.getSongId());
		if (p.getSongs().contains(s)) {
			r.setMessage("SONG ALREADY EXIST IN THIS PLAYLIST");
		} else {
			p.addSong(s);
			playlistRepository.save(p);
			r.setMessage("SONG ADDED TO PLAYLIST");
		}
		return r;
	}

	@RequestMapping(path = "/removeSongPlaylist", method = RequestMethod.POST, produces = "Application/json", consumes = "Application/json")
	private @ResponseBody Response removeSongPlaylist(@RequestBody PlaylistRequest removeSong) {
		Response r = new Response();
		Playlist p = playlistRepository.findOne(removeSong.getPlaylistId());
		Song s = songRepository.findOne(removeSong.getSongId());
		p.getSongs().remove(s);
		playlistRepository.save(p);
		r.setMessage("SONG DELETED SUCCESFULLY");
		return r;
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
	}

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

	@RequestMapping(path = "/generes", method = RequestMethod.GET, produces = "Application/json")
	public @ResponseBody Iterable<Genere> listAllGeneres() {
		return genereRepository.findAll();
	}

	/**********************************************************************************/

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

	@RequestMapping(path = "/bands", method = RequestMethod.GET, produces = "Application/json")
	public @ResponseBody Iterable<Band> listAllBands() {
		return bandRepository.findAll();
	}

	/**********************************************************************************/

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

	@RequestMapping(path = "/artists", method = RequestMethod.GET, produces = "Application/json")
	public @ResponseBody Iterable<Artist> listAllArtist() {
		return artistRepository.findAll();
	}

	/**********************************************************************************/

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

	@GetMapping(path = "albums")
	public @ResponseBody Iterable<Album> listAllAlbum() {
		return albumRepository.findAll();
	}

	/**********************************************************************************/

	@RequestMapping(path = "/upload", method = RequestMethod.POST, consumes = "multipart/form-data")
	public void uploadFiles(@RequestBody MultipartFile uploadFile) {
		System.out.println("dades fitxer");
		System.out.println(uploadFile.getName());
		System.out.println(uploadFile.getSize());
	}

	@RequestMapping(path = "/newSong", method = RequestMethod.POST, produces = "Application/json", consumes = "Application/json")
	public @ResponseBody SongResponse addNewSong(@RequestBody SongRequest songRequest) {

		SongResponse response = new SongResponse();
		if (songRepository.findByNameSong(songRequest.getNameSong()) == null) {

			Genere owner = genereRepository.findByGenereName(songRequest.getGenereN());
			Song newSong = new Song(songRequest.getNameSong(), songRequest.getDurationSong(), owner);
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
	}

	@RequestMapping(path = "/songs", method = RequestMethod.GET, produces = "Application/json")
	public @ResponseBody Iterable<Song> listAllSong() {
		return songRepository.findAll();
	}

	/**********************************************************************************/

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

}
