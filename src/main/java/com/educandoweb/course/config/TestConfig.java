package com.educandoweb.course.config;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

/**
 * This class is used to instantiate the database with some test data.
 * It will be used only when the profile "test" is active.
 *
 * The Configuration annotation indicates that this class is a
 * configuration class. Profile annotation indicates that this class
 * should be used only when the profile "test" is active. The profile
 * name must be the same as the one defined in the application.properties
 * file.
 *
 * AutoWired annotation is used to automatically inject the UserRepository
 * dependency into this class.
 *
 * The CommandLineRunner interface is used to run a method when the
 * application starts. The run method will be executed when the application
 * starts. In this example, the run method will insert two users into the
 * database by using the UserRepository.
 */

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
  @Autowired
  private UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {
    User u1 = new User(
        null,
        "Maria Brown",
        "maria@gmail.com",
        "988888888",
        "123456"
    );
    User u2 = new User(
        null,
        "Alex Green",
        "alex@gmail.com",
        "977777777",
        "123456"
    );

    userRepository.saveAll(Arrays.asList(u1, u2));
  }
}
