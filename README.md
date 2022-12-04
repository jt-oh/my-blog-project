# my-blog-project

## Introduction

This is a side-project which services simple personal blog using java and spring framework.
This project only contains server-side APIs.

## Objectives

1. familiarize with java/kotlin, spring/springboot framework.
2. practice some design principles with hexagonal architecture.

## Functions

- User Sign Up
- User Sign in/out
- CRUD Post
- CRUD Tags on Post
- CRUD Comments on Post

## Actors

- Logined User
  - Postor
  - Commentor
- Visitor

## Use Cases

### Sign UP (Actor: Visitor)

1. Sign Up with 'login_id', 'email', 'password', 'name'

### Login (Actor: Visitor)

1. Login with 'login_id', 'password'

### Logout (Actor: Logined User)

1. Logout

### Create Post (Actor: Logined User)

1. Sign in with credentials
1. Move to a Post Creation Page
1. Write 'title', 'content'
1. Add Tags
1. Create Post

### Read Post (Actor: all)

1. Move to a Post

### Update Post (Actor: Poster)

1. Sign in with credentials
1. Move to a Post Edit Page
1. Update 'title' or 'content'
1. Udpate Tags
1. Update Post

### Delete Post (Actor: Poster)

1. Sign in with credentials
1. Move to a Post
1. Delete the Post

### Create Comment on Post (Actor: Logined User)

1. Sign in with credentials
1. Move to a Post
1. Write Comment
1. Create Comment

### Read Comments on Post (Actor: all)

1. Move to a Post

### Update Comment on Post (Actor: Commentor)

1. Sign in with credentials
1. Move to a Post
1. Edit Comment
1. Update Comment

### Delete Comment On Post (Actor: Poster, Commentor)

1. Sign in with credentials
1. Move to a Post
1. Delete the Comment

## EndPoints

### Post Service

1. POST '/api/v1/posts'
  Request Body
    - title : Required String
    - content : Required String
    - tags : Optional Array of String

1. GET '/api/v1/posts'
  Query Parameter
    - page_index : int = 1
    - page_size : int = 10
    - search_by : String

1. GET '/api/v1/posts/{post_id}'
1. PUT '/api/v1/posts/{post_id}'
  Request Body
    - title : Required String
    - content : Required String
    - tags : Optional Array of String

1. DELETE '/api/v1/posts/{post_id}'
1. GET '/api/v1/users/{user_id}/posts'
  Query Parameter
    - page_index : int = 1
    - page_size : int = 10
    - search_by : String

### User Service

1. POST '/users/register'
  Request Body
    - login_id : Required String
    - eamil : Required String
    - password : Required String
    - name : Required String

1. POST '/users/login'
  Request Body
    - login_id : Required String
    - password : Required String

1. POST '/users/logout'

### Comment Service

1. POST '/api/v1/posts/{post_id}/comments'
  Request Body
    - content

1. GET '/api/v1/posts/{post_id}/comments'
  Query Paramter
    - page_index : int = 1
    - page_size : int = 10

1. PUT '/api/v1/comments/{comments_id}'
  Request Body
    - content

1. DELETE '/api/v1/comments/{comments_id}'

## Trouble Shooting

### Dependency Injection with property Autowiring does not work

``` java
public class PostRestController {
    @Autowired
    private PostRestPresentor postRestPresentor;
    
    // ...

    public PostRestController() {
        PostRepository postRepository = new MemRepository();
        postService = new PostServiceImpl(postRepository, postRestPresentor);
    }
}
```

`postRestPresentor` was null so that PostService throws NullPointerException.
I assumed that @Autowired annotations on instance property works with no-args-constructor.
And because I implemented the constructor, spring cannot use no-args-constructor.

I decided to use Constructor Dependency Injection and finally it works.

Below is a fixed code.

``` java
public class PostRestController {
    // ...

    @Autowired
    public PostRestController(PostRestPresentor postRestPresentor) {
        PostRepository postRepository = new MemRepository();
        
        postService = new PostServiceImpl(postRepository, postRestPresentor);
    }
}
```
