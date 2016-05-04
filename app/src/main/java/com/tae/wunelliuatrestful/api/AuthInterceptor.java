package com.tae.wunelliuatrestful.api;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Eduardo on 04/05/2016.
 */
public class AuthInterceptor implements Interceptor {
    private static final String USER = "WunelliTestUser";
    private static final String PASS = "resUtseTillenuW";

    @Override
    public Response intercept(Chain chain) throws IOException {
        // concatenate username and password with colon for authentication
        String credentials = USER + ":" + PASS;
        // create Base64 encodet string
        final String basic =
                "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

        Request request = chain.request().newBuilder()
                .header("Authorization", basic)
                .build();
        return chain.proceed(request);
    }
}
