package edu.iis.mto.blog.domain.repository;

import edu.iis.mto.blog.domain.model.AccountStatus;
import edu.iis.mto.blog.domain.model.BlogPost;
import edu.iis.mto.blog.domain.model.LikePost;
import edu.iis.mto.blog.domain.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LikePostRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LikePostRepository likePostRepository;

    private User user;
    private LikePost likePost;
    private BlogPost blogPost;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setFirstName("Jan");
        user.setEmail("john@domain.com");
        user.setAccountStatus(AccountStatus.NEW);

        blogPost = new BlogPost();
        blogPost.setEntry("Testowy post");
        blogPost.setUser(user);

        likePost = new LikePost();
        likePost.setPost(blogPost);
        likePost.setUser(user);

        entityManager.persist(user);
        entityManager.persist(blogPost);
    }

    @Test
    public void shouldFindNoLikesIfRepositoryIsEmpty() {
        List<LikePost> likePosts = likePostRepository.findAll();

        assertThat(likePosts, hasSize(0));
    }

    @Test
    public void shouldSaveLikePost() {
        likePostRepository.save(likePost);
        List<LikePost> likePosts = likePostRepository.findAll();

        assertThat(likePosts, hasSize(1));
    }

    @Test
    public void checkBlogPostOfLikePost() {
        likePostRepository.save(likePost);
        List<LikePost> likePosts = likePostRepository.findAll();

        assertEquals(likePosts.get(0).getPost().getId(), blogPost.getId());
    }

    @Test
    public void checkUserOfLikePost() {
        likePostRepository.save(likePost);
        List<LikePost> likePosts = likePostRepository.findAll();

        assertEquals(likePosts.get(0).getUser().getId(), user.getId());
    }

    @Test
    public void checkLikePostModifyWithNewBlokPost() {
        BlogPost newBlokPost = new BlogPost();
        newBlokPost.setUser(user);
        newBlokPost.setEntry("new blok post");
        entityManager.persist(newBlokPost);

        likePostRepository.save(likePost);
        likePostRepository.findAll().get(0).setPost(newBlokPost);
        likePostRepository.save(likePost);

        List<LikePost> likePosts = likePostRepository.findAll();

        assertThat(likePosts, hasSize(1));
        assertThat(likePosts.get(0).getPost(), is(newBlokPost));
    }

    @Test
    public void checkLikePostModifyWithNewUser() {
        User newUser = new User();
        newUser.setFirstName("Ala");
        newUser.setEmail("ala@gmail.com");
        newUser.setAccountStatus(AccountStatus.NEW);
        entityManager.persist(newUser);

        likePostRepository.save(likePost);
        likePostRepository.findAll().get(0).setUser(newUser);
        likePostRepository.save(likePost);

        List<LikePost> likePosts = likePostRepository.findAll();

        assertThat(likePosts, hasSize(1));
        assertThat(likePosts.get(0).getUser(), is(newUser));
    }

    @Test
    public void checkStateOfFindByUserAndPostWithTwoNullParameters() {
        likePostRepository.save(likePost);
        Optional<LikePost> likePostOptional = likePostRepository.findByUserAndPost(null, null);

        assertFalse(likePostOptional.isPresent());
    }

    @Test
    public void checkStateOfFindByUserAndPostWithUserNullParameter() {
        likePostRepository.save(likePost);
        Optional<LikePost> likePostOptional = likePostRepository.findByUserAndPost(null, blogPost);

        assertFalse(likePostOptional.isPresent());
    }

    @Test
    public void checkStateOfFindByUserAndPostWithBlogPostNullParameter() {
        likePostRepository.save(likePost);
        Optional<LikePost> likePostOptional = likePostRepository.findByUserAndPost(user, null);

        assertFalse(likePostOptional.isPresent());
    }

    @Test
    public void shouldFindLikePostByUserAndBlogPost() {
        likePostRepository.save(likePost);
        Optional<LikePost> likePostOptional = likePostRepository.findByUserAndPost(user, blogPost);

        assertTrue(likePostOptional.isPresent());
        assertEquals(likePostOptional.get().getPost(), blogPost);
        assertEquals(likePostOptional.get().getUser(), user);
    }

    @Test
    public void shouldNotFindLikePostByProperUserAndWrongBlogPost() {
        likePostRepository.save(likePost);

        BlogPost newBlokPost = new BlogPost();
        newBlokPost.setUser(user);
        newBlokPost.setEntry("new blok post");
        entityManager.persist(newBlokPost);

        Optional<LikePost> likePostOptional = likePostRepository.findByUserAndPost(user, newBlokPost);

        assertFalse(likePostOptional.isPresent());
    }

    @Test
    public void shouldNotFindLikePostByWrongUserAndProperBlogPost() {
        likePostRepository.save(likePost);

        User newUser = new User();
        newUser.setFirstName("Ala");
        newUser.setEmail("ala@gmail.com");
        newUser.setAccountStatus(AccountStatus.NEW);
        entityManager.persist(newUser);

        Optional<LikePost> likePostOptional = likePostRepository.findByUserAndPost(newUser, blogPost);

        assertFalse(likePostOptional.isPresent());
    }

    @Test
    public void checkBehaviourOfFindByUserAndPostWithTwoNullParameters() {
        assertDoesNotThrow(() -> likePostRepository.findByUserAndPost(null, null));
    }

    @Test
    public void checkBehaviourOfFindByUserAndPostWithUserNullParameter() {
        assertDoesNotThrow(() -> likePostRepository.findByUserAndPost(null, blogPost));
    }

    @Test
    public void checkBehaviourOfFindByUserAndPostWithBlogPostNullParameter() {
        assertDoesNotThrow(() -> likePostRepository.findByUserAndPost(user, null));
    }

    @Test
    public void checkBehaviourOfFindByUserAndPostWithNoNullParameters() {
        assertDoesNotThrow(() -> likePostRepository.findByUserAndPost(user, blogPost));
    }
}
