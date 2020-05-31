package edu.iis.mto.blog.domain;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.iis.mto.blog.domain.errors.DomainError;
import edu.iis.mto.blog.domain.model.BlogPost;
import edu.iis.mto.blog.domain.repository.BlogPostRepository;
import edu.iis.mto.blog.domain.repository.LikePostRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import edu.iis.mto.blog.api.request.UserRequest;
import edu.iis.mto.blog.domain.model.AccountStatus;
import edu.iis.mto.blog.domain.model.User;
import edu.iis.mto.blog.domain.repository.UserRepository;
import edu.iis.mto.blog.mapper.BlogDataMapper;
import edu.iis.mto.blog.services.BlogService;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogManagerTest {

    @MockBean
    private BlogPostRepository blogPostRepository;

    @MockBean
    private LikePostRepository likePostRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private BlogDataMapper dataMapper;

    @Autowired
    private BlogService blogService;

    @Captor
    private ArgumentCaptor<User> userParam;

    private User user;
    private BlogPost blogPost;

    @Before
    public void setUp() throws Exception {
        Long userID = 1L;
        Long ownerID = 2L;
        Long blogPostID = 3L;

        User owner = new User();
        owner.setId(ownerID);

        user = new User();
        user.setId(userID);

        blogPost = new BlogPost();
        blogPost.setId(blogPostID);
        blogPost.setUser(owner);

        when(userRepository.findById(ownerID)).thenReturn(Optional.of(owner));
        when(userRepository.findById(userID)).thenReturn(Optional.of(user));
        when(blogPostRepository.findById(blogPostID)).thenReturn(Optional.of(blogPost));
    }

    @Test
    public void creatingNewUserShouldSetAccountStatusToNEW() {
        blogService.createUser(new UserRequest("John", "Steward", "john@domain.com"));
        verify(userRepository).save(userParam.capture());
        User user = userParam.getValue();
        assertThat(user.getAccountStatus(), Matchers.equalTo(AccountStatus.NEW));
    }

    @Test
    public void shouldDoesNotThrowDomainErrorWithUserAccountStatusEqualsConfirmed() {
        user.setAccountStatus(AccountStatus.CONFIRMED);
        assertDoesNotThrow(() -> blogService.addLikeToPost(user.getId(), blogPost.getId()));
    }

    @Test
    public void shouldThrowDomainErrorWithUserAccountStatusEqualsNew() {
        user.setAccountStatus(AccountStatus.NEW);
        assertThrows(DomainError.class, () -> blogService.addLikeToPost(user.getId(), blogPost.getId()));
    }

    @Test
    public void shouldThrowDomainErrorWithUserAccountStatusEqualsRemoved() {
        user.setAccountStatus(AccountStatus.REMOVED);
        assertThrows(DomainError.class, () -> blogService.addLikeToPost(user.getId(), blogPost.getId()));
    }
}
