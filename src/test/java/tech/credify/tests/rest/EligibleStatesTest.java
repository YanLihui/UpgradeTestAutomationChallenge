package tech.credify.tests.rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static tech.credify.utils.GlobalConstant.BASE_URL;


public class EligibleStatesTest {


     private Logger logger = Logger.getLogger(EligibleStatesTest.class);

    @Test
    public void testAPIServiceUP()
    {

       given().
                when().
                get(BASE_URL)
               .then()
               .assertThat()
                    .statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void validateEligibleStatesNumber(){

        Response response = RestAssured.get(BASE_URL);
        List<Map<String,Object>> states = response.jsonPath().getJsonObject("states");
        Assert.assertEquals(states.size(),48);
        logger.info("The number of eligible states is: " + states.size() + "\n");
    }

    @Test
    public void validateOnlyOneStateHasMinLoanAmount3005()
    {
        Response response = RestAssured.get(BASE_URL);
        List<Map<String,Object>> states = response.jsonPath().getJsonObject("states");

         int countForMinAge19 = 0;
         String stateNameMinAge19 = new String();

         String stateWithMinLoanAmount3005 = new String();
         int countForMinLoanAmount3005 = 0;

        for (Map<String, Object> state : states) {
            int minAge = (int)state.get("minAge");
            float minLoanAmount = (float)state.get("minLoanAmount");

            if (minAge == 19) {
                countForMinAge19 += 1;
                stateNameMinAge19 = (String) state.get("label");
                logger.info("Only the state of " + stateNameMinAge19 + "has minAge of 19\n");

            }
            if(minLoanAmount == 3005){
                countForMinLoanAmount3005 += 1;
                stateWithMinLoanAmount3005 = (String) state.get("label");
                logger.info(stateWithMinLoanAmount3005 + "is the only state has min Loan amount of $3,005\n");
            }


        }

        Assert.assertEquals(countForMinAge19,1, "FAILED: Not Only One State has minAge of 19");
        Assert.assertEquals(countForMinLoanAmount3005,1, "FAILED: Georgia is NOT the only state with min loan amount requirement of $3,005 \n");

    }


}
