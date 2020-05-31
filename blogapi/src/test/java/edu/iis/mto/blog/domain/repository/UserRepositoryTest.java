package edu.iis.mto.blog.domain.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import edu.iis.mto.blog.domain.model.AccountStatus;
import edu.iis.mto.blog.domain.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    private User user, user2;

    @Before
    public void setUp() {
        user = new User();
        user.setFirstName("Jan");
        user.setEmail("john@domain.com");
        user.setAccountStatus(AccountStatus.NEW);

        user2 = new User();
    }

    @Test
    public void shouldFindNoUsersIfRepositoryIsEmpty() {

        List<User> users = repository.findAll();

        assertThat(users, hasSize(0));
    }

    @Test
    public void shouldFindOneUsersIfRepositoryContainsOneUserEntity() {
        User persistedUser = entityManager.persist(user);
        List<User> users = repository.findAll();

        assertThat(users, hasSize(1));
        assertThat(users.get(0)
                        .getEmail(),
                equalTo(persistedUser.getEmail()));
    }

    @Test
    public void shouldStoreANewUser() {

        User persistedUser = repository.save(user);

        assertThat(persistedUser.getId(), notNullValue());
    }

    @Test
    public void shouldFindUserByFirstNameAndEmailAddress() {
        user2.setFirstName("Ala");
        user2.setEmail("ala@gmail.com");
        user2.setAccountStatus(AccountStatus.NEW);

        User persistedUser = repository.save(user);
        repository.save(user2);

        List<User> users = repository.findByFirstNameContainingOrLastNameContainingOrEmailContainingAllIgnoreCase("Jan", "", "john@domain.com");

        assertThat(users, hasSize(1));
        assertEquals(persistedUser.getId(), users.get(0).getId());
    }

    @Test
    public void shouldReturnAllUsersWithEmptySearchParameter() {
        user2.setFirstName("Ala");
        user2.setEmail("ala@gmail.com");
        user2.setAccountStatus(AccountStatus.NEW);

        repository.save(user);
        repository.save(user2);

        List<User> users = repository.findByFirstNameContainingOrLastNameContainingOrEmailContainingAllIgnoreCase("", "", "");

        assertThat(users, hasSize(2));
    }

    @Test
    public void shouldReturnNoUsersWithWrongSearchParameter() {
        user2.setFirstName("Ala");
        user2.setEmail("ala@gmail.com");
        user2.setAccountStatus(AccountStatus.NEW);

        repository.save(user);
        repository.save(user2);

        List<User> users = repository.findByFirstNameContainingOrLastNameContainingOrEmailContainingAllIgnoreCase("test", "test", "test");

        assertThat(users, hasSize(0));
    }
}
