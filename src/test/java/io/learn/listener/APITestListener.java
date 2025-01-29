package io.learn.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class APITestListener implements ITestListener {

    private static final ExtentReports extentReports = new ExtentReports();
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    static {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/extent-reports-api.html");
        extentReports.attachReporter(sparkReporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extentReports.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        test.info("Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (extentTest.get() != null) {
            Response apiResponse = (Response)result.getTestContext().getAttribute("apiResponse");
            extentTest.get().pass("Test passed");
            extentTest.get().info("Response Body: " + formatJSON(apiResponse.getBody().asString()));
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (extentTest.get() != null) {
            extentTest.get().fail("Test failed: " + result.getThrowable());

            // Retrieve API request and response from the context
            String apiRequest = (String)result.getTestContext().getAttribute("apiRequest");
            Response apiResponse = (Response)result.getTestContext().getAttribute("apiResponse");

            if(apiRequest != null && apiResponse != null) {
                // Log the API request and response details
                extentTest.get().info("API Request:" +apiRequest);
                extentTest.get().info("Response Status:" +apiResponse.getStatusCode());
                extentTest.get().info("Response Body:" + formatJSON(apiResponse.getBody().asString()));
            } else {
                extentTest.get().info("API Request/response data is not available");
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (extentTest.get() != null) {
            extentTest.get().skip("Test skipped: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }

    @Override
    public void onStart(ITestContext context) {
        // No implementation needed
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    // Utility method to format the JSON for better readability
    private String formatJSON(String jsonString) {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter()
                    .writeValueAsString(new ObjectMapper().readTree(jsonString));
        } catch (Exception e) {
            return jsonString; // in case of an error, just return the raw JSON
        }
    }
}
