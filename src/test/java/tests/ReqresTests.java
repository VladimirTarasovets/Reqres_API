package tests;

import models.get.ColorsData;
import models.get.UserData;
import models.post.Register;
import models.post.SuccessReg;
import models.post.UnSuccessReg;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Specification;

import java.util.List;

import static endpoints.EndPoint.*;
import static io.restassured.RestAssured.given;

public class ReqresTests {

    @Test
    public void checkAvatarAndIdTest(){
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());

        List<UserData> users = given()
                .when()
                .get(LIST_USERS)
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        users.forEach(x-> Assertions.assertTrue(x.getAvatar().contains(x.getId().toString())));

        Assertions.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));

        List<String> avatars = users.stream().map(UserData::getAvatar).toList();
        List<String> ids = users.stream().map(x->x.getId().toString()).toList();

        for(int i = 0; i < avatars.size(); i++)
            Assertions.assertTrue(avatars.get(i).contains(ids.get(i)));
    }

    @Test
    public void successRegisterTest(){
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());

        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";

        Register user = new Register("eve.holt@reqres.in", "pistol");
        SuccessReg successReg = given()
                .body(user)
                .when()
                .post(REGISTER)
                .then().log().all()
                .extract().as(SuccessReg.class);

        Assertions.assertEquals(id, successReg.getId());
        Assertions.assertEquals(token, successReg.getToken());
    }

    @Test
    public void unSuccessRegisterTest(){
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK400());

        Register user = new Register("sydney@fife", "");
        UnSuccessReg unSuccessReg = given()
                .body(user)
                .when()
                .post(REGISTER)
                .then().log().all()
                .extract().as(UnSuccessReg.class);

        Assertions.assertEquals("Missing password", unSuccessReg.getError());
    }

    @Test
    public void sortedYearsTest(){
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecOK200());

        List<ColorsData> colors = given()
                .when()
                .get(LIST_RESOURCE)
                .then().log().all()
                .extract().body().jsonPath().getList("data", ColorsData.class);

        List<Integer> years = colors.stream().map(ColorsData::getYear).toList();
        List<Integer> sortYears = years.stream().sorted().toList();

        Assertions.assertEquals(sortYears, years);
    }

    @Test
    public void deleteUserTest(){
        Specification.installSpecification(Specification.requestSpec(), Specification.responseSpecStatus(204));

        given()
                .when()
                .delete(DELETE_USER)
                .then().log().all();
    }
}
