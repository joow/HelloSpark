package org.hello.spark;

import java.util.HashMap;
import java.util.Map;

public final class QuestionsCache {
    private QuestionsCache() {
    }

    private static final Map<String, String> CACHE = new HashMap<>();

    static {
        CACHE.put("who played James Bond in the film Dr No", "Sean Connery");
        CACHE.put("what currency did Spain use before the Euro", "Peseta");
        CACHE.put("which city is the Eiffel tower in", "Paris");
        CACHE.put("who is the Prime Minister of Great Britain", "David Cameron");
    }

    public static String getAnswerTo(String question) {
        return CACHE.get(question);
    }
}
