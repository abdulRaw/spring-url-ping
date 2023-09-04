package io.abd.isthesiteup.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class URLchecker {
    private final String IS_SITE_IS_UP = "Site is UP";
    private final String IS_SITE_IS_DOWN = "Site is DOWN";

    @GetMapping("/check")
    public String getURLStatus(@RequestParam String Url) {
        String result = "SITE IS UP";
        try {
            URL urlObj = new URL(Url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int responseCode = con.getResponseCode();
            if (responseCode / 100 != 2 || responseCode / 100 != 3) {
                System.out.println(responseCode/100);
                result = "SITE IS UP"; 
            } else {
                result = "SITE IS DOWN";
            }
        } catch (IOException e) {
            e.printStackTrace();

            return "INVALID URL";
        }
        return result;
    }
}
