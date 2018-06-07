package com.birdjunior.moviesearch;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.robolectric.RuntimeEnvironment;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public abstract class MockWebServerTest extends TestCase {

    protected ApplicationModel applicationModel;
    private MockWebServer mockWebServer;

    @Before
    public void setup() throws Exception {
        // Start a mock web server
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        // Init the app model with the mock server's base URL
        MovieSearchApplication application = (MovieSearchApplication) RuntimeEnvironment.application;
        applicationModel = application.model;
        if (applicationModel instanceof TestApplicationModel) {
            ((TestApplicationModel)applicationModel).initializeApiServiceFactory(mockWebServer.url("/").toString());
        }
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    /**
     * Queues up a response in the MockServer
     * @param body The body to be returned, can be null
     * @param responseCode The response code the server should return. If 0, response code will not be set.
     */
    protected void queueMockResponse(@Nullable String body, int responseCode) {
        MockResponse response = new MockResponse();

        if (!TextUtils.isEmpty(body)) {
            response.setBody(body);
        }

        if (responseCode > 0) {
            response.setResponseCode(responseCode);
        }

        mockWebServer.enqueue(response);
    }
}
