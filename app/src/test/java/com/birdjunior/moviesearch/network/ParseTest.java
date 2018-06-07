package com.birdjunior.moviesearch.network;

import com.birdjunior.moviesearch.MockWebServerTest;
import com.birdjunior.moviesearch.TestMovieSearchApplication;
import com.birdjunior.moviesearch.models.Result;
import com.birdjunior.moviesearch.models.SearchResults;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import rx.Subscriber;

@RunWith(RobolectricTestRunner.class)
@Config(application = TestMovieSearchApplication.class)
public class ParseTest extends MockWebServerTest {

    private static final String SIMPLE_RESULT_TITLE = "Big Little Lies";
    private static final String SIMPLE_RESULT_POSTER = "https://ia.media-imdb.com/images/M/MV5BN2VjYmU2NjYtMzgyZi00YmYwLWJhNWUtNTk4ZmRmNWNmZTljL2ltYWdlXkEyXkFqcGdeQXVyNDkzNTM2ODg@._V1_SX300.jpg";
    private static final int SIMPLE_TOTAL_RESULTS = 5684;

    private static final String SIMPLE_RESPONSE = "{\n"
            + "    \"Search\": [\n"
            + "        {\n"
            + "            \"Title\": \"" + SIMPLE_RESULT_TITLE + "\",\n"
            + "            \"Year\": \"2017-\",\n"
            + "            \"imdbID\": \"tt3920596\",\n"
            + "            \"Type\": \"series\",\n"
            + "            \"Poster\": \"" + SIMPLE_RESULT_POSTER + "\"\n"
            + "        }\n"
            + "    ],\n"
            + "    \"totalResults\": \"" + Integer.toString(SIMPLE_TOTAL_RESULTS) + "\",\n"
            + "    \"Response\": \"True\"\n"
            + "}";

    @Before
    public void setup() throws Exception {
        super.setup();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void simpleApiTest() throws Exception {
        // Simple test to confirm parsing a response yields the expected results
        queueMockResponse(SIMPLE_RESPONSE, 200);

        ApiService apiService = applicationModel.getApiService();
        apiService.performSearch("big", 1)
                .observeOn(applicationModel.getObserveOnScheduler())
                .subscribeOn(applicationModel.getBackgroundScheduler())
                .subscribe(new Subscriber<SearchResults>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SearchResults searchResults) {
                        // Validate response
                        assertNotNull(searchResults);
                        assertNotNull(searchResults.getSearchResults());
                        assertEquals(searchResults.getSearchResults().size(), 1);
                        assertEquals(searchResults.getTotalResults(), SIMPLE_TOTAL_RESULTS);

                        // Validate result object
                        Result result = searchResults.getSearchResults().get(0);
                        assertNotNull(result);
                        assertEquals(result.getTitle(), SIMPLE_RESULT_TITLE);
                        assertEquals(result.getPoster(), SIMPLE_RESULT_POSTER);
                    }
                });
    }
}
