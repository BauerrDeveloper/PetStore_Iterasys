package petstore;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;


public class Pet {

    String uri = "https://petstore.swagger.io/v2/pet";

    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test(priority = 1)
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
                .body("name", is("Stark"))
                .body("category.name", is("AX2345LORT"))
                .body("status", is("available"))

        ;


    }



@Test(priority = 2)
    public void consultarPet(){
        String petId = "98765432198";

        String token =
        given()
                .contentType("application/json")
                .log().all()
                .when()
                        .get(uri + "/" + petId)
                .then()
                        .log().all()
                        .statusCode(200)
                .body("name", is("Stark"))
                .body("category.name", is("AX2345LORT"))
                .body("status", is("available"))
        .extract()
            .path("category.name");

    System.out.println("TOKEN: " + token);
        ;
    }

    @Test(priority = 3)
    public void alterarPet() throws IOException {
        String jsonBody = lerJson("dataBase/pet2.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .put(uri)
        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Stark"))
                .body("status", is("sold"))
        ;
    }

}