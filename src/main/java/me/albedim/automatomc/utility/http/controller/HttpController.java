package me.albedim.automatomc.utility.http.controller;

import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpController {

    private static final String baseUrl = "https://api.groq.com/openai/v1/chat";
    private static final Gson gson = new Gson();

    @SuppressWarnings("deprecation")
    public static <T> T get(String endpoint, String token, Class<T> responseType){
        try {
            URL url = new URL(baseUrl + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            setAuthenticationHeader(connection, token);

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (InputStream inputStream = connection.getInputStream();
                     InputStreamReader reader = new InputStreamReader(inputStream);
                     BufferedReader bufferedReader = new BufferedReader(reader)) {

                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        response.append(line);
                    }
                    return gson.fromJson(response.toString(), responseType);
                }
            } else {
                return gson.fromJson(String.format("{code: %d}", responseCode), responseType);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("deprecation")
    public static <T> T post(String endpoint, String token, Object data, Class<T> responseType) {
        try {
            URL url = new URL(baseUrl + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            setAuthenticationHeader(connection, token);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            try (OutputStream outputStream = connection.getOutputStream();
                 OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {
                String requestData = gson.toJson(data);
                writer.write(requestData);
                writer.flush();
            }

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (InputStream inputStream = connection.getInputStream();
                     InputStreamReader reader = new InputStreamReader(inputStream);
                     BufferedReader bufferedReader = new BufferedReader(reader)) {

                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        response.append(line);
                    }

                    return gson.fromJson(response.toString(), responseType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                return gson.fromJson(String.format("{code: %d}", responseCode), responseType);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("deprecation")
    public static <T> T put(String endpoint, String token, Object data, Class<T> responseType) {
        try {
            URL url = new URL(baseUrl + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            setAuthenticationHeader(connection, token);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            try (OutputStream outputStream = connection.getOutputStream();
                 OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {
                String requestData = gson.toJson(data);
                writer.write(requestData);
                writer.flush();
            }

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (InputStream inputStream = connection.getInputStream();
                     InputStreamReader reader = new InputStreamReader(inputStream);
                     BufferedReader bufferedReader = new BufferedReader(reader)) {

                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        response.append(line);
                    }

                    return gson.fromJson(response.toString(), responseType);
                }
            } else {
                return gson.fromJson(String.format("{code: %d}", responseCode), responseType);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("deprecation")
    public boolean delete(String endpoint, String token) throws IOException {
        URL url = new URL(baseUrl + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        setAuthenticationHeader(connection, token);

        int responseCode = connection.getResponseCode();
        return responseCode == HttpURLConnection.HTTP_OK;
    }


    private static void setAuthenticationHeader(HttpURLConnection connection, String token) {
        if (token != null && !token.isEmpty()) {
            connection.setRequestProperty("Authorization", "Bearer " + token);
        }
    }
}
