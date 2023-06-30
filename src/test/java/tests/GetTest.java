package tests;

import models.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Specification;

import java.util.List;

import static endpoints.EndPoint.GET_STATUS;
import static io.restassured.RestAssured.given;

public class GetTest {

    @Test
    public void checkAvatarAndIdTest (){
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());

        List<UserData> users = given()
                .when()
                .get(GET_STATUS)
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        users.forEach(x-> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));

        Assertions.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));

        List<String> avatars = users.stream().map(UserData::getAvatar).toList();
        List<String> ids = users.stream().map(x->x.getId().toString()).toList();
        for(int i =0; i<avatars.size(); i++)
            Assertions.assertTrue(avatars.get(i).contains(ids.get(i)));
    }

    @Test
    public void successRegister (){

    }
}
