package edu.iis.mto.blog.rest.test;

public class StaticElements {
    public static final String USER_API = "/blog/user/";
    public static final String POST_API = "/post";

    public static final Long OWNER_USER_ID = 1L;
    public static final Long NEW_USER_ID = 2L;
    public static final Long REMOVE_USER_ID = 3L;
    public static final Long CONFIRM_USER_ID = 4L;
    public static final Long NOT_EXISTING_USER_ID = 10L;

    public static final Long NEW_USER_WITH_BLOG_POSTS_ID = 5L;
    public static final Long REMOVE_USER_WITH_BLOG_POST_ID = 6L;
    public static final Long CONFIRM_USER_WITH_BLOG_POST_ID = 7L;

    public static final Long BLOG_POST_WITHOUT_LIKES_ID = 1L;
    public static final Long BLOG_POST_WITH_LIKES_ID = 2L;

    public static String findUser(String pattern) { return "/blog/user/find?searchString=" + pattern; }

    public static String getBlogPost(Long blogPostId) {
        return "/blog/post/" + blogPostId.toString();
    }

    public static String addLike(Long userId, Long blogPostId) {
        return USER_API + userId.toString() + "/like/" + blogPostId.toString();
    }

    public static String getBlogPostByUserId(Long userId) {
        return USER_API + userId.toString() + POST_API;
    }

    public static String createBlogPost(Long userId) {
        return USER_API + userId.toString() + POST_API;
    }
}
