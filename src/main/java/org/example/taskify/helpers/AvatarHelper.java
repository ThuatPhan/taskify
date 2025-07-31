package org.example.taskify.helpers;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class AvatarHelper {
    public static String generateDefaultAvatar(String firstName, String lastName) {
        String fullName = URLEncoder.encode(firstName.trim() + " " + lastName.trim(), StandardCharsets.UTF_8);

        return "https://ui-avatars.com/api/?name=" + fullName;
    }
}
