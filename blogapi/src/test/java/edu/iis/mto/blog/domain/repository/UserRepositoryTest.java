package edu.iis.mto.blog.domain.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
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
        user.setLastName("Kowalski");
        user.setEmail("john@domain.com");
        user.setAccountStatus(AccountStatus.NEW);

        user2 = new User();
        user2.setFirstName("Ala");
        user2.setLastName("Kala");
        user2.setEmail("ala@gmail.com");
        user2.setAccountStatus(AccountStatus.NEW);
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
    public void shouldFindUserByFirstNameAndLastNameAndEmailAddress() {
        User persistedUser = repository.save(user);
        repository.save(user2);

        List<User> users = repository
                .findByFirstNameContainingOrLastNameContainingOrEmailContainingAllIgnoreCase("Jan", "Kowalski", "john@domain.com");

        assertThat(users, hasSize(1));
        assertEquals(persistedUser.getId(), users.get(0).getId());
    }

    @Test
    public void shouldReturnAllUsersWithEmptySearchParameter() {
        repository.save(user);
        repository.save(user2);

        List<User> users = repository
                .findByFirstNameContainingOrLastNameContainingOrEmailContainingAllIgnoreCase("", "", "");

        assertThat(users, hasSize(2));
    }

    @Test
    public void shouldReturnNoUsersWithWrongSearchParameters() {
        repository.save(user);
        repository.save(user2);

        List<User> users = repository
                .findByFirstNameContainingOrLastNameContainingOrEmailContainingAllIgnoreCase("test", "test", "test");

        assertThat(users, hasSize(0));
    }

    @Test
    public void shouldReturnAllUsersWithFirstNameParameter() {
        repository.save(user);
        repository.save(user2);

        List<User> users = repository
                .findByFirstNameContainingOrLastNameContainingOrEmailContainingAllIgnoreCase("Jan", "", "");

        assertThat(users, hasSize(2));
    }

    @Test
    public void shouldReturnAllUsersWithLastNameParameter() {
        repository.save(user);
        repository.save(user2);

        List<User> users = repository
                .findByFirstNameContainingOrLastNameContainingOrEmailContainingAllIgnoreCase("", "Kowalski", "");

        assertThat(users, hasSize(2));
    }

    @Test
    public void shouldReturnAllUsersWithEmailAddressParameter() {
        repository.save(user);
        repository.save(user2);

        List<User> users = repository
                .findByFirstNameContainingOrLastNameContainingOrEmailContainingAllIgnoreCase("", "", "john@domain.com");

        assertThat(users, hasSize(2));
    }

    @Test
    public void shouldFindUserWithNoCompleteFirstNameParameter() {
        User persistedUser = repository.save(user);
        repository.save(user2);

        List<User> users = repository
                .findByFirstNameContainingOrLastNameContainingOrEmailContainingAllIgnoreCase("Ja", "Kowalski", "john@domain.com");

        assertThat(users, hasSize(1));
        assertEquals(persistedUser.getId(), users.get(0).getId());
    }

    @Test
    public void shouldFindUserWithNoCompleteLastNameParameter() {
        User persistedUser = repository.save(user);
        repository.save(user2);

        List<User> users = repository
                .findByFirstNameContainingOrLastNameContainingOrEmailContainingAllIgnoreCase("Jan", "Ko", "john@domain.com");

        assertThat(users, hasSize(1));
        assertEquals(persistedUser.getId(), users.get(0).getId());
    }

    @Test
    public void shouldFindUserWithNoCompleteEmailParameter() {
        User persistedUser = repository.save(user);
        repository.save(user2);

        List<User> users = repository
                .findByFirstNameContainingOrLastNameContainingOrEmailContainingAllIgnoreCase("Jan", "Kowalski", "john");

        assertThat(users, hasSize(1));
        assertEquals(persistedUser.getId(), users.get(0).getId());
    }
}
