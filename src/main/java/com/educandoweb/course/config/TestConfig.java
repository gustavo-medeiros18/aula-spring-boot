package com.educandoweb.course.config;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

/**
 * This class is used to instantiate the database with some test data.
 * It will be used only when the profile "test" is active.
 * <p>
 * The Configuration annotation indicates that this class is a
 * configuration class. Profile annotation indicates that this class
 * should be used only when the profile "test" is active. The profile
 * name must be the same as the one defined in the application.properties
 * file.
 * <p>
 * AutoWired annotation is used to automatically inject the UserRepository
 * dependency into this class.
 * <p>
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

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public void run(String... args) throws Exception {
    Category cat1 = new Category(null, "Electronics");
    Category cat2 = new Category(null, "Books");
    Category cat3 = new Category(null, "Computers");

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

    Order o1 = new Order(
        null,
        Instant.parse("2019-06-20T19:53:07Z"),
        OrderStatus.PAID, u1
    );
    Order o2 = new Order(
        null,
        Instant.parse("2019-07-21T03:42:10Z"),
        OrderStatus.WAITING_PAYMENT,
        u2
    );
    Order o3 = new Order(
        null,
        Instant.parse("2019-07-22T15:21:22Z"),
        OrderStatus.WAITING_PAYMENT,
        u1
    );

    categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
    userRepository.saveAll(Arrays.asList(u1, u2));
    orderRepository.saveAll(Arrays.asList(o1, o2, o3));
  }
}
