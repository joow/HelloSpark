package org.hello.spark;

import java.util.StringTokenizer;
import java.util.regex.Matcher;

public enum QuestionType {
    WHAT_IS_YOUR_NAME("what is your name") {
        public Object answer(Matcher matcher) {
            return "benoit";
        }
    },

    LARGEST_NUMBER("which of the following numbers is the largest: (.*)") {
        public Object answer(Matcher matcher) {
            final String numbers = matcher.group(1);
            final StringTokenizer stringTokenizer = new StringTokenizer(numbers, ",");
            Integer largest = null;

            while (stringTokenizer.hasMoreTokens()) {
                final Integer current = Integer.valueOf(stringTokenizer.nextToken().trim());
                if (largest == null || largest < current) {
                    largest = current;
                }
            }

            return largest;
        }
    },

    ADDITION("what is ([\\d]+) plus ([\\d]+)") {
        public Object answer(Matcher matcher) {
            final Integer a = Integer.valueOf(matcher.group(1));
            final Integer b = Integer.valueOf(matcher.group(2));

            return a + b;
        }
    },

    MULTIPLICATION("what is ([\\d]+) multiplied by ([\\d]+)") {
        public Object answer(Matcher matcher) {
            final Integer a = Integer.valueOf(matcher.group(1));
            final Integer b = Integer.valueOf(matcher.group(2));

            return a * b;
        }
    },

    CUBE_AND_SQUARE("which of the following numbers is both a square and a cube: ([\\d]+), ([\\d]+)") {
        @Override
        public Object answer(Matcher matcher) {
            final Integer a = Integer.valueOf(matcher.group(1));
            final Integer b = Integer.valueOf(matcher.group(2));

            return a;
        }
    };

    private final String question;

    private QuestionType(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public abstract Object answer(Matcher matcher);
}
