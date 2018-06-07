package com.birdjunior.moviesearch;

/**
 * This class is used by Robolectric when running unit tests.
 * It gives us a chance to inject a custom TestApplicationModel where we can use a mock http server.
 */
@SuppressWarnings("unused")
public class TestMovieSearchApplication extends MovieSearchApplication {
    @Override
    protected ApplicationModel getModel() {
        return new TestApplicationModel();
    }
}
