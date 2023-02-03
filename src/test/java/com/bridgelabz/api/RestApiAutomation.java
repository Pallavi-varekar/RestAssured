//****************
//Author Name : Pallavi Varekar
//Project     : Rest Assured APis/ Json-Server
//Date        : 30/01/2023
//****************

package com.bridgelabz.api;

import io.restassured.internal.path.json.mapping.JsonPathJackson2ObjectDeserializer;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class RestApiAutomation {
    public String Authorization;
    public static String userId;
    public static String playlistId;

    @BeforeTest
    public void setUp() {
        Authorization = "Bearer BQAgmm8F2NpQ7IdsdRoyo03or_x65NrJakRTx3TAoHxSORxfMtYtURW3MuVLPcB2mC8vYPbHBqRXv5KdUHUFlAMk570P47fQhHF3yRf-9CwoDQH04jOTxgajXg8146y0S7VWITrkMFcPw8bf7EPHogJqq2a2q6PUZSwv8O2BKdsuJfolfTkUMjZerjbZx51knvrvwpCjLhlgNBitUNsnaP7OGOU5mIcJJUC-xXaFKngu_R4P-8TGLs3bZfeLhi4hPM1dqKMSxie3jwNThIWSUlp89Wlkzs_xlo9hNWcaT-hbg11G4c30UNvq";
        userId = "31jfde2mixb7ityhwmtyxwel7xay";
        playlistId="5gD1WUGcJmORRGY1et95Dp";
    }

    @Test(priority = 1)
    public void getCurrentUserProfile() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/me");
        //System.out.println("Response: " +result);
        userId = result.path("id");
        System.out.println("UserId : " + userId);
        String userName = result.path("display_name");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
        Assert.assertEquals("Pallavivarekar", userName);
        System.out.println("UserName: " + userName);
    }

    @Test(priority =2)
    public void getUsersProfiles() {
        System.out.println("UserId: " + userId);
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/users/" + userId);
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/users/").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }
    @Test(priority = 3)
    public void toCreatePlaylist(){
        JSONObject requestBody = new JSONObject();
        requestBody.put("name","new playlist20");
        requestBody.put("description","new playlist description20");
        requestBody.put("public","false");

        Response result =  given().contentType("application/json")
                .accept("application/json")
                .header("Authorization",Authorization)
             //   .body("{\"name\":\"Sam Playlist\",\"description\":\"New playlist description\",\"public\":false}")
                .body(requestBody.toJSONString())
                .when()
                //.post("https://api.spotify.com/v1/users/31jfde2mixb7ityhwmtyxwel7xay/playlists");
                .post("https://api.spotify.com/v1/users/"+userId+"/playlists");
        result.prettyPrint();
        result.then().assertThat().statusCode(201);
        result.then().log().all();
        System.out.println(given().when().post("https://api.spotify.com/v1/users/"+userId+"/playlists").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(201);
    }
    @Test(priority = 4)
    public void AddItemstoPlaylist() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .body("{\"uris\":[\"spotify:track:1301WleyT98MSxVHPZCA6M\",\"spotify:episode:512ojhOuo1ktJprKbVcKyQ\"]}")
                .when()
                .post("https://api.spotify.com/v1/playlists/4Ml3Ppc3U8M6lCHTFx7FeY/tracks");
        result.prettyPrint();
        result.then().assertThat().statusCode(201);
        result.then().log().all();
        System.out.println(given().when().post("https://api.spotify.com/v1/playlists/4Ml3Ppc3U8M6lCHTFx7FeY/tracks").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(201);
    }
    @Test(priority = 5)
    public void UpdateItemstoPlaylist() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("range_start",1);
        requestBody.put("insert_before",3);
        requestBody.put("range_length",2);

        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .body(requestBody.toJSONString())

                .when()
                .put("https://api.spotify.com/v1/playlists/74V2iHnZJZMT1QqNhi1N0w/tracks");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
        result.then().log().all();
        System.out.println(given().when().put("https://api.spotify.com/v1/playlists/74V2iHnZJZMT1QqNhi1N0w/tracks").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }
    @Test(priority = 6)
    public void ChangePlaylistDetails() {

        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .body("{\n" +
                        "  \"name\": \"Updated Playlist Name\",\n" +
                        "  \"description\": \"Updated playlist description\",\n" +
                        "  \"public\": false\n" +
                      "}")
                .when()
                .put("https://api.spotify.com/v1/playlists/5gD1WUGcJmORRGY1et95Dp");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
        result.then().log().all();
        System.out.println(given().when() .put("https://api.spotify.com/v1/playlists/5gD1WUGcJmORRGY1et95Dp").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }
    @Test(priority = 7)
    public void GetPlaylists() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/playlists/5gD1WUGcJmORRGY1et95Dp");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
        result.then().log().all();
        System.out.println(given().when() .get("https://api.spotify.com/v1/playlists/5gD1WUGcJmORRGY1et95Dp").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }
    @Test(priority = 8)
    public void GetCurrentUsersPlaylists() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/me/playlists");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/me/playlists").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }
    @Test(priority = 9)
    public void GetUsersPlaylists() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/users/31jfde2mixb7ityhwmtyxwel7xay/playlists");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/users/31jfde2mixb7ityhwmtyxwel7xay/playlists").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }
    @Test(priority = 10)
    public void GetPlaylistItems() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/playlists/5gD1WUGcJmORRGY1et95Dp/tracks");
        result.prettyPrint();
        result.then().assertThat().statusCode(200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/playlists/5gD1WUGcJmORRGY1et95Dp/tracks").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }
    @Test(priority = 11)
    public void removePlaylistItems() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .body("{\"tracks\":[{\"uri\":\"spotify:track:4iV5W9uYEdYUVa79Axb7Rh\"},{\"uri\":\"spotify:track:1301WleyT98MSxVHPZCA6M\"}]}")
                .when()
                .delete("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks");
        result.prettyPrint();
        result.then().statusCode(200);
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        System.out.println(given().when().delete("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }

    @Test(priority = 12)
    public void getSeveralTracks() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/tracks?ids=7ouMYWpwJ422jRcDASZB7P%2C4VqPOruhp5EdPBeR92t6lQ%2C2takcwOaAZWiXQijPHIx7B");
        result.prettyPrint();
        result.then().statusCode(200);
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/tracks?ids=7ouMYWpwJ422jRcDASZB7P%2C4VqPOruhp5EdPBeR92t6lQ%2C2takcwOaAZWiXQijPHIx7B").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }

    @Test(priority = 13)
    public void getTrack() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/tracks/7ouMYWpwJ422jRcDASZB7P");
        result.prettyPrint();
        result.then().statusCode(200);
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/tracks/7ouMYWpwJ422jRcDASZB7P").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }


    @Test(priority = 14)
    public void getTracksAudioFeatures() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/audio-features/7ouMYWpwJ422jRcDASZB7P");
        result.prettyPrint();
        result.then().statusCode(200);
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/audio-features/7ouMYWpwJ422jRcDASZB7P").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }
    @Test(priority = 15)
    public void getTracksAudioAnalysis() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/audio-analysis/7ouMYWpwJ422jRcDASZB7P");
        result.prettyPrint();
        result.then().statusCode(200);
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/audio-analysis/7ouMYWpwJ422jRcDASZB7P").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }

    @Test(priority = 16)
    public void searchForItem() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .queryParam("q" , "artist")
                .queryParam("type", "track")
                .when()
                .get("https://api.spotify.com/v1/search?q=emaster%2520track%3ADoxy%2520artist%3AMiles%2520Davis");
        result.prettyPrint();
        result.then().statusCode(200);
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/search?q=emaster%2520track%3ADoxy%2520artist%3AMiles%2520Davis").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }

    @Test(priority = 17)
    public void getSeveralShows() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/shows?ids=5as3aKmN2k11yfDDDSrvaZ");
        result.prettyPrint();
        result.then().statusCode(200);
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/shows?ids=5as3aKmN2k11yfDDDSrvaZ").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }
    @Test(priority = 18)
    public void getShowEpisodes() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/shows/38bS44xjbVVZ3No3ByF1dJ/episodes" );
        result.prettyPrint();
        result.then().statusCode(200);
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/shows/38bS44xjbVVZ3No3ByF1dJ/episodes" ).timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }
    @Test(priority = 19)
    public void getShow() {
        Response result = given().contentType("application/json")
                .accept("application/json")
                .header("Authorization", Authorization)
                .when()
                .get("https://api.spotify.com/v1/shows/38bS44xjbVVZ3No3ByF1dJ");
        result.prettyPrint();
        result.then().statusCode(200);
        Assert.assertEquals(result.statusCode(), 200);
        result.then().log().all();
        System.out.println(given().when().get("https://api.spotify.com/v1/shows/38bS44xjbVVZ3No3ByF1dJ").timeIn(TimeUnit.MILLISECONDS));
        result.then().log().ifValidationFails().statusCode(200);
    }






}
