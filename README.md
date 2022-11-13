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
1. Move to a Post Createion Page
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
