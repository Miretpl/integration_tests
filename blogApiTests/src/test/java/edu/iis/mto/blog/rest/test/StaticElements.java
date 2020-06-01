package edu.iis.mto.blog.rest.test;

public class StaticElements {
    public static final String USER_API = "/blog/user/";
    private static final String LIKE_ADD_API = "/like/";
    private static final String BLOG_POST_API = "/blog/post/";
    private static final String POST_API = "/post";

    public static final Long OWNER_USER_ID = 1L;
    private static final Long NEW_USER_ID = 2L;
    private static final Long REMOVE_USER_ID = 3L;
    private static final Long CONFIRM_USER_ID = 4L;

    private static final Long NEW_USER_WITH_BLOG_POSTS_ID = 5L;
    private static final Long REMOVE_USER_WITH_BLOG_POST_ID = 6L;
    private static final Long CONFIRM_USER_WITH_BLOG_POST_ID = 7L;

    private static final Long BLOG_POST_WITHOUT_LIKES_ID = 1L;
    private static final Long BLOG_POST_WITH_LIKES_ID = 2L;

    public static final String ADD_LIKE_BY_OWNER_TO_POST_WITHOUT_LIKES = USER_API + OWNER_USER_ID.toString() + LIKE_ADD_API + BLOG_POST_WITHOUT_LIKES_ID.toString();
    public static final String ADD_LIKE_BY_OWNER_TO_POST_WITH_LIKES = USER_API + OWNER_USER_ID.toString() + LIKE_ADD_API + BLOG_POST_WITH_LIKES_ID.toString();

    public static final String ADD_LIKE_BY_CONFIRM_USER_TO_POST_WITHOUT_LIKES = USER_API + CONFIRM_USER_ID.toString() + LIKE_ADD_API + BLOG_POST_WITHOUT_LIKES_ID.toString();
    public static final String ADD_LIKE_BY_CONFIRM_USER_TO_POST_WITH_LIKES = USER_API + CONFIRM_USER_ID.toString() + LIKE_ADD_API + BLOG_POST_WITH_LIKES_ID.toString();

    public static final String ADD_LIKE_BY_NEW_USER_TO_POST_WITHOUT_LIKES = USER_API + NEW_USER_ID.toString() + LIKE_ADD_API + BLOG_POST_WITHOUT_LIKES_ID.toString();
    public static final String ADD_LIKE_BY_NEW_USER_TO_POST_WITH_LIKES = USER_API + NEW_USER_ID.toString() + LIKE_ADD_API + BLOG_POST_WITH_LIKES_ID.toString();

    public static final String ADD_LIKE_BY_REMOVE_USER_TO_POST_WITHOUT_LIKES = USER_API + REMOVE_USER_ID.toString() + LIKE_ADD_API + BLOG_POST_WITHOUT_LIKES_ID.toString();
    public static final String ADD_LIKE_BY_REMOVE_USER_TO_POST_WITH_LIKES = USER_API + REMOVE_USER_ID.toString() + LIKE_ADD_API + BLOG_POST_WITH_LIKES_ID.toString();

    public static final String GET_BLOG_POST_WITHOUT_LIKES_INFO = BLOG_POST_API + BLOG_POST_WITHOUT_LIKES_ID.toString();
    public static final String GET_BLOG_POST_WITH_LIKES_INFO = BLOG_POST_API + BLOG_POST_WITH_LIKES_ID.toString();

    public static final String CREATE_BLOG_POST_BY_CONFIRM_USER = USER_API + CONFIRM_USER_ID.toString() + POST_API;
    public static final String CREATE_BLOG_POST_BY_NEW_USER = USER_API + NEW_USER_ID.toString() + POST_API;
    public static final String CREATE_BLOG_POST_BY_REMOVE_USER = USER_API + REMOVE_USER_ID.toString() + POST_API;
    public static final String CREATE_BLOG_POST_BY_NOT_EXISTING_USER = USER_API + "10" + POST_API;

    public static final String GET_BLOG_POST_BY_OWNER = USER_API + OWNER_USER_ID.toString() + POST_API;
    public static final String GET_BLOG_POST_BY_CONFIRM_USER_WITHOUT_POSTS = USER_API + CONFIRM_USER_ID.toString() + POST_API;
    public static final String GET_BLOG_POST_BY_CONFIRM_USER_WITH_POSTS = USER_API + CONFIRM_USER_WITH_BLOG_POST_ID.toString() + POST_API;
    public static final String GET_BLOG_POST_BY_NEW_USER_WITHOUT_POSTS = USER_API + NEW_USER_ID.toString() + POST_API;
    public static final String GET_BLOG_POST_BY_NEW_USER_WITH_POSTS = USER_API + NEW_USER_WITH_BLOG_POSTS_ID.toString() + POST_API;
    public static final String GET_BLOG_POST_BY_REMOVE_USER_WITHOUT_POSTS = USER_API + REMOVE_USER_ID.toString() + POST_API;
    public static final String GET_BLOG_POST_BY_REMOVE_USER_WITH_POSTS = USER_API + REMOVE_USER_WITH_BLOG_POST_ID.toString() + POST_API;
    public static final String GET_BLOG_POST_BY_NOT_EXISTING_USER = USER_API + "10" + POST_API;
}
