package me.genix.utils;

import me.genix.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author github.com/genixzero
 * @created 24/09/2020 - 9:23 PM
 */
public class DiscordUtils {

    private static final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36";

    public static String send(String URL, String method) throws IOException {
        URL obj = new URL(Main.api + URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod(method);

        con.setRequestProperty("authorization", Main.token.getToken());
        con.setRequestProperty("User-Agent", userAgent);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public static JSONObject getJSONObject(String URL, String method) throws IOException, ParseException {
        URL obj = new URL(Main.api + URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod(method);

        con.setRequestProperty("authorization", Main.token.getToken());
        con.setRequestProperty("User-Agent", userAgent);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return (JSONObject) new JSONParser().parse(response.toString());
    }

    public static JSONArray getJSONArray(String URL, String method) throws IOException, ParseException {
        URL obj = new URL(Main.api + URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod(method);

        con.setRequestProperty("authorization", Main.token.getToken());
        con.setRequestProperty("User-Agent", userAgent);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return (JSONArray) new JSONParser().parse(response.toString());
    }
}
