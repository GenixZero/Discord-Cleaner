package me.genix;

import me.genix.utils.DiscordUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;

/**
 * @author github.com/genixzero
 * @created 24/09/2020 - 9:23 PM
 */
public class Main {

    public static Token token;
    public static final String api = "https://discord.com/api/v8";
    private static final int DELAY = 250;

    public static void main(String[] args) {
        try {
            token = new Token();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "There was an error getting your token (Please make sure it is entered correctly).", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }

        String user = null;
        try {
            JSONObject object = DiscordUtils.getJSONObject("/users/@me", "GET");
            user = object.get("username") + "#" + object.get("discriminator");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Your token is incorrect.", "Invalid Token", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }

        boolean leaveServers = ask("Do you want to leave all servers?");
        boolean closeDMs = ask("Do you want to close all DMs?");
        boolean removeFriends = ask("Do you want to remove all friends?");
        boolean removeBlocked = ask("Do you want to unblock everyone?");
        boolean removePending = ask("Do you want to remove all pending friends?");
        ask("Are you sure you want to clean " + user + "?");

        if (leaveServers) {
            try {
                JSONArray object = DiscordUtils.getJSONArray("/users/@me/guilds", "GET");

                for (Object o : object.toArray()) {
                    String ID = (String) ((JSONObject)o).get("id");

                    try {
                        DiscordUtils.send("/users/@me/guilds/" + ID, "DELETE");
                    } catch (Exception e) { }
                    Thread.sleep(DELAY);
                }
            } catch (Exception e) { }
        }

        if (closeDMs) {
            try {
                JSONArray object = DiscordUtils.getJSONArray("/users/@me/channels", "GET");
                for (Object o : object.toArray()) {
                    String ID = (String) ((JSONObject)o).get("id");

                    try {
                        DiscordUtils.send("/channels/" + ID, "DELETE");
                    } catch (Exception e) { }
                    Thread.sleep(DELAY);
                }
            } catch (Exception e) { }
        }

        if (removeFriends || removeBlocked || removePending) {
            try {
                JSONArray object = DiscordUtils.getJSONArray("/users/@me/relationships", "GET");

                for (Object o : object.toArray()) {
                    JSONObject obj = (JSONObject)o;
                    long type = (long)obj.get("type");
                    if ((type == 1 && removeFriends) || (type == 2 && removeBlocked) || (type != 1 && type != 2 && removePending)) {
                        try {
                            DiscordUtils.send("/users/@me/relationships/" + obj.get("id"), "DELETE");
                        } catch (Exception e) { }
                        Thread.sleep(DELAY);
                    }
                }
            } catch (Exception e) {}
        }

        JOptionPane.showMessageDialog(null, "Finished Cleaning.");
    }

    private static boolean ask(String question) {
        int state = JOptionPane.showConfirmDialog(null, question);

        if (state == JOptionPane.CANCEL_OPTION || state == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }

        return state == 0;
    }
}
