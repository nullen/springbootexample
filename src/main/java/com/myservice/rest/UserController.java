package com.myservice.rest;

import com.myservice.model.User;
import com.myservice.rest.exception.UserNotFoundException;
import com.myservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody User user) throws UserNotFoundException {
        log.info("POST ../user/ is called for creating a new user with following details {}", user.toString());

        final long id = this.userService.create(user);

        if (id == 0) {
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body("Something go wrongs under creating new User.");
        }

        Link link = linkTo(methodOn(UserController.class).findUserById(id)).withSelfRel();
        return ResponseEntity.created(URI.create(link.getHref())).build();

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity findUserById(@PathVariable("id") long id) throws UserNotFoundException {
        log.info("GET ../user/id is called with user id: {}", id);

        final User user = this.userService.findUserById(id);

        if (user == null) {
            throw new UserNotFoundException(id);
        }

        UserResource resource = new UserResource(user);
        resource.add(linkTo(methodOn(UserController.class).findUserById(id)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ping")
    public ResponseEntity isAlive() {
        return ok("User REST Service is alive.");
    }
}
