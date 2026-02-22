package vitor.decisaoimobiliaria.util;

import java.text.Normalizer;

public final class TextNormalizer {

    private TextNormalizer() {
    }

    public static String normalizeKey(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        String trimmed = value.trim();

        String noAccents = Normalizer.normalize(trimmed, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        return noAccents.toUpperCase();

    }
}
