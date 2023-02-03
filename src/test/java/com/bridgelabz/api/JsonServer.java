//****************
//Author Name : Pallavi Varekar
//Project     : Json-Server
//Date        : 31/01/2023
//****************
package com.bridgelabz.api;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class JsonServer {
    //posts
        @Test
        public void getAllPost(){
            Response result = given().contentType("application/json")
                    .accept("application/json")
                    .when()
                    .get("http://localhost:3000/posts");
            result.prettyPrint();
            result.then().assertThat().statusCode(200);
        }

        @Test
        public void createPost() {
            Response result = given().contentType("application/json")
                    .accept("application/json")
                    .body("{\n" +
                            "\"id\":\"2\",\n"+
                            "  \"title\": \"json-server\",\n" +
                            "   \"author\": \"riya\"\n" +
                            "    }")
                    .when()
                    .post("http://localhost:3000/posts");
            result.prettyPrint();
            result.then().assertThat().statusCode(201);
        }
        @Test
        public void updatePost() {
            Response result = given().contentType("application/json")
                    .accept("application/json")
                    .body("{\n" +
                            "        \"id\": 1,\n" +
                            "        \"title\": \"json-server\",\n" +
                            "        \"author\": \"girish\"\n" +
                            "    }")
                    .when()
                    .put("http://localhost:3000/posts/4");
            result.prettyPrint();
            result.then().assertThat().statusCode(200);
        }
        @Test
        public void deletePost() {
            Response result = given().contentType("application/json")
                    .accept("application/json")
                    .when()
                    .delete("http://localhost:3000/posts/1");
            result.prettyPrint();
            result.then().assertThat().statusCode(200);
        }

        //comments
        @Test
        public void getAllComments(){
            Response result = given().contentType("application/json")
                    .accept("application/json")
                    .when()
                    .get("http://localhost:3000/comments");
            result.prettyPrint();
            result.then().assertThat().statusCode(200);
        }

        @Test
        public void createComment(){
            Response result = given().contentType("application/json")
                    .accept("application/json")
                    .body("{\n" +
                            "      \"id\": 5,\n" +
                            "      \"name\": \"naina\",\n" +
                            "      \"body\": \"some comment\",\n" +
                            "      \"postId\": 1\n" +
                            "    }")
                    .when()
                    .post("http://localhost:3000/comments");
            result.prettyPrint();
            result.then().assertThat().statusCode(201);
        }

        @Test
        public void updateComment(){
            Response result = given().contentType("application/json")
                    .accept("application/json")
                    .body("{\n" +
                            "      \"id\": 3,\n" +
                            "      \"name\": \"giri\",\n" +
                            "      \"body\": \"some comment\",\n" +
                            "      \"postId\": 1\n" +
                            "    }")
                    .when()
                    .put("http://localhost:3000/comments/4");
            result.prettyPrint();
            result.then().assertThat().statusCode(200);
        }

        @Test
        public void deleteComment(){
            Response result = given().contentType("application/json")
                    .accept("application/json")
                    .when()
                    .delete("http://localhost:3000/comments/2");
            result.prettyPrint();
            result.then().assertThat().statusCode(200);
        }

        //Profile
        @Test
        public void getProfile(){
            Response result = given().contentType("application/json")
                    .accept("application/json")
                    .when()
                    .get("http://localhost:3000/profile");
            result.prettyPrint();
            result.then().assertThat().statusCode(200);
        }

        @Test
        public void createProfile(){
            Response result = given().contentType("application/json")
                    .accept("application/json")
                    .body("{\n" +
                            "    \"id\": \"3\",\n" +
                            "    \"name\": \"son\",\n" +
                            "    \"age\": \"21\"\n" +
                            "  }")
                    .when()
                    .post("http://localhost:3000/profile");
            result.prettyPrint();
            result.then().assertThat().statusCode(201);
        }

        @Test
        public void updateProfile(){
            Response result = given().contentType("application/json")
                    .accept("application/json")
                    .body("{\n" +
                            "    \"id\": \"1\",\n" +
                            "    \"name\": \"mina\",\n" +
                            "    \"age\": \"21\"\n" +
                            "  }")
                    .when()
                    .put("http://localhost:3000/profile/3");
            result.prettyPrint();
            result.then().assertThat().statusCode(200);
        }

        @Test
        public void deleteProfile(){
            Response result = given().contentType("application/json")
                    .accept("application/json")
                    .when()
                    .delete("http://localhost:3000/profile/1");
            result.prettyPrint();
            result.then().assertThat().statusCode(200);
        }
    }

