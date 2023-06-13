package app.Controlers;

import app.Models.Post;
import app.Models.User;
import app.Repositories.PostRepository;
import app.Repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MainController {
	public static List<Map<String, String>> posts = new ArrayList<Map<String, String>>() {{
		add(new HashMap<String, String>() {{
			put("template", "What is a relational database?");
			put("url", "/content");
			put("id", "0");
		}});
		add(new HashMap<String, String>() {{
			put("template", "What is a relational database?");
			put("url", "/content");
			put("id", "1");
		}});
		add(new HashMap<String, String>() {{
			put("template", "What is a relational database?");
			put("url", "/content");
			put("id", "2");
		}});
		add(new HashMap<String, String>() {{
			put("template", "What is a relational database?");
			put("url", "/content");
			put("id", "3");
		}});
	}};

	private final UserRepository userRepository;
	private final PostRepository postRepository;

	public MainController(UserRepository userRepository, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/get-posts-recommendations")
	public List<Map<String, String>> main() {
		return posts;
	}
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/get-post-content/{id}")
	public Post getPostContent(@PathVariable String id) {
		try {
			Long postId = Long.parseLong(id);
			Post post = postRepository.findById(postId).orElse(null);
			return post;
		} catch (NumberFormatException e) {
			// Handle the case where id cannot be parsed to Long
			return null;
		}
	}
	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping("/add-post")
	public Post addPost(@RequestBody Post post) {
		return postRepository.save(post);
	}


	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/get-user/{id}")
	public User getUser(@PathVariable String id) {
		try {
			Long userId = Long.parseLong(id);
			User a = userRepository.findById(userId).orElse(null);
			return a;
		} catch (NumberFormatException e) {
			// Handle the case where id cannot be parsed to Long
			return null;
		}
	}
	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping("/add-user")
	public User addUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping("/login-user")
	public ResponseEntity<User> loginUser(@RequestBody User loginUser) {
		Optional<User> user = userRepository.findByNicknameAndPswrd(loginUser.getNickname(), loginUser.getPswrd());
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}
