package APITesting.com.org.api;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import APITesting.com.org.classes.Info;
import APITesting.com.org.classes.Posts;
import APITesting.com.org.classes._Postd;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class JasonServerRequests {
	
	//GET /posts

	@Test
	public void Test_01() {
		Response resp= given().
				when().
				get("http://localhost:3000/posts");
		System.out.println(resp.asString());
				
	}
	
	//PUT /posts
	
	@Test
	public void Test_02() {
		Response resp= given().
				body("	{\"id\":\"2\","
						+ " \"title\": \"talShachar\","
						+ " \"author\": \"Tazz\" } ").
				when().
				contentType(ContentType.JSON).
				put("http://localhost:3000/posts");
		System.out.println(resp.asString());
				
	}
	
	//PUT for object /posts  
	
	@Test
	public void Test_03() {
		
		Posts posts = new Posts();
		posts.setId("8");
		posts.setTitle("Talosh");
		posts.setAuthor("tazosss");
		
		Response resp= given().
				when().
				contentType(ContentType.JSON).
				body(posts).
				put("http://localhost:3000/posts");
		System.out.println(resp.asString());
				
	}
	
	//PATCH for object /posts/2
	
	@Test
	public void Test_04() {
		
		Posts posts = new Posts();
		posts.setTitle("New title");
		posts.setAuthor("tazosss");
		
		Response resp= given().
				when().
				contentType(ContentType.JSON).
				body(posts).
				patch("http://localhost:3000/posts/2");
		System.out.println(resp.asString());
				
	}
	
	//DELETE for object /posts/8
	
	@Test
	public void Test_05() {
		Response resp= given().
				when().
				delete("http://localhost:3000/posts/8");
		System.out.println(resp.asString());
				
	}
	
	//Nested db test
	@Test
	public void Test_06() {
		Info info = new Info();
		info.setEmail("secondemail@gmail.com");
		info.setPhone("493393");
		
		_Postd postd = new _Postd();
		postd.setTitle("New title2");
		postd.setAuthor("tazosssssssSSS");
		postd.setInfo(info);
		
		Response resp= given().
				when().
				contentType(ContentType.JSON).
				body(postd).
				post("http://localhost:3000/posts");
		System.out.println(resp.asString());
	}
	
	@Test
	public void Test_07() {
		String resp= given().
				when().
				get("http://localhost:3000/posts/2").
				then().
				contentType(ContentType.JSON).
				extract().
				path("author");
		assertEquals("tazosss", resp);
	}
	
	@Test
	public void Test_08() {
		String resp= given().
				when().
				get("http://localhost:3000/posts/2").
				then().
				contentType(ContentType.JSON).
				extract().
				path("author");
		assertEquals("tazosss", resp);
	}
	
	@Test
	public void Test_09() {
		Response resp = given().
		when().
		get("http://localhost:3000/posts/2");
		
		Long time = resp.
		then().
		extract().
		time();
		
		System.out.println("Response time is: " + time);
		
		given().
		when().
		then().
		and().
		time(lessThan(5L));
	}
}
