package petstore;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class Pet {

    String uri = "https://petstore.swagger.io/v2/pet";

    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test
    public void inclurPet() throws IOException {
        String jsonBody = lerJson("dataBase/pet1.json");

        given()
                .contentType("application/json") //comum em APIs REST, as mais antigas eram text/xml
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)
        .then()
                .log().all().statusCode(200)
        ;


    }

}
