package io.learn.tests.api;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.learn.api.ApiClient;
import io.learn.listener.APITestListener;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class APITests {

    private static final String PET_ID = "6789999";
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Test
    public void testGETRequest(ITestContext context) {
        ExtentTest test = APITestListener.getTest();
        extentTest.set(test);

        test.log(Status.INFO, "Sending GET request to retrieve pet with ID: " +PET_ID);
        Response response = ApiClient.get("/" + PET_ID);

        // Store the request and response in the context for failure handling
        context.setAttribute("apiRequest", "/pet/" + PET_ID);
        context.setAttribute("apiResponse", response);

        test.log(Status.INFO, "Response received with status code: " +response.getStatusCode());

        // Assertions
        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.jsonPath().getString("name"), "doggie-restassured");
        test.log(Status.PASS, "Pet retrieved successfully with ID: " +PET_ID);
    }
}
