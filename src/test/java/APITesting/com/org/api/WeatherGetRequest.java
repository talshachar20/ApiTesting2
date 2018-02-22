package APITesting.com.org.api;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

public class WeatherGetRequest {


	//Status code 200
	@Test
	public void Test_01() {
		Response resp= when().
				get("http://samples.openweathermap.org/data/2.5/weather?q=London&appid=b6907d284e10d714a6e88b30761fae22");
		AssertJUnit.assertTrue(resp.getStatusCode() == 200);
	}

	//Parameters rest assure 
	@Test
	public void Test_02() {
		given().
		param("q", "London").
		param("appid", "b6907d284e10d714a6e88b30761fae22").
		when().
		get("http://samples.openweathermap.org/data/2.5/weather").
		then().
		assertThat().statusCode(200);
	}
	
	@Test
	public void Test_03() {
		Response resp= given().
		param("appid", "b6907d289e10d714a6e88b30761fae22").
		param("id", "2172797").
		when().
		get("http://samples.openweathermap.org/data/2.5/weather");
		
		System.out.println(resp.asString());
		
	}
	
	//incorrect params
	@Test
	public void Test_04() {
		String weatherResponse= given().
		parameter("appid", "b6907d289e10d714a6e88b30761fae22").
		parameter("id", "2172797").
		when().
		get("http://samples.openweathermap.org/data/2.5/weather").
		then().
		contentType(ContentType.JSON).
		extract().
		path("weather[0].main");
		
		//assert(weatherResponse == "test");
		assertEquals("Clouds", weatherResponse);
	}
}
