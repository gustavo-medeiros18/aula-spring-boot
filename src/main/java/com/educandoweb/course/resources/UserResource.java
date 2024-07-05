package com.educandoweb.course.resources;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
  @Autowired
  private UserService service;

  @GetMapping
  public ResponseEntity<List<User>> findAll() {
    List<User> users = service.findAll();

    return ResponseEntity.ok().body(users);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<User> findById(@PathVariable Long id) {
    User user = service.findById(id);

    return ResponseEntity.ok().body(user);
  }

  /**
   * PostMapping is used to define a POST endpoint.
   * RequestBody is used to parse the JSON request body
   * into the User object.
   */
  @PostMapping
  public ResponseEntity<User> insert(@RequestBody User user) {
    user = service.insert(user);

    /**
     * The uri variable is used to store the location of the new resource.
     * ServletUriComponentsBuilder.fromCurrentRequest() method retrieves
     * the current request URI. The path() method appends the id of the new
     * resource to the URI. The buildAndExpand() method substitutes the id
     * variable in the path() method with the id of the new resource. The
     * toUri() method converts the builder to a URI.
     */
    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(user.getId()).toUri();

    return ResponseEntity.created(uri).body(user);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);

    return ResponseEntity.noContent().build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
    user = service.update(id, user);

    return ResponseEntity.ok().body(user);
  }
}
