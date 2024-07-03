package com.educandoweb.course.repositories;

import com.educandoweb.course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A repository is the component that will provide the CRUD operations for
 * the entity that it is related to. It is an interface that extends the
 * JpaRepository interface, passing the entity class and the type of the
 * entity ID as type arguments.
 *
 * The JpaRepository already has some built-in CRUD methods, such as save,
 * delete, findById, findAll, etc.
 *
 * Repositories are interfaces because Spring Data JPA will create a
 * repository implementation automatically at runtime.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
