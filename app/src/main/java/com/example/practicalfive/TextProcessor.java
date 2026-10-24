
package com.example.practicalfive;

import java.util.Locale;

public class TextProcessor {

    public static String processMunicipality(String municipality) {

        if (municipality == null || municipality.trim().isEmpty()) {
            return null;
        }

        String name = municipality.trim().toLowerCase(Locale.ROOT);

        switch (name) {
            case "beijing":
                return "Beijing";

            case "shanghai":
                return "Shanghai";

            case "tianjin":
                return "Tianjin";

            case "chongqing":
                return "Chongqing";

            default:
                return null;
        }
    }
}

