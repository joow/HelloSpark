package org.hello.spark;

import com.google.common.base.*;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;
import com.sun.istack.internal.Nullable;

import java.util.regex.Matcher;

public enum QuestionType {
    WHAT_IS_YOUR_NAME("what is your name") {
        public Object answer(Matcher matcher) {
            return "benoit";
        }
    },

    LARGEST_NUMBER("which of the following numbers is the largest: (.*)") {
        public Object answer(Matcher matcher) {
            return Ordering.natural().max(getNumbers(matcher.group(1)));
        }
    },

    ADDITION("what is ([\\d]+) plus ([\\d]+)") {
        public Object answer(Matcher matcher) {
            final Integer a = Integer.valueOf(matcher.group(1));
            final Integer b = Integer.valueOf(matcher.group(2));

            return a + b;
        }
    },

    MINUS("what is ([\\d]+) minus ([\\d]+)") {
        public Object answer(Matcher matcher) {
            final Integer a = Integer.valueOf(matcher.group(1));
            final Integer b = Integer.valueOf(matcher.group(2));

            return a - b;
        }
    },

    MULTIPLICATION("what is ([\\d]+) multiplied by ([\\d]+)") {
        public Object answer(Matcher matcher) {
            final Integer a = Integer.valueOf(matcher.group(1));
            final Integer b = Integer.valueOf(matcher.group(2));

            return a * b;
        }
    },

    CUBE_AND_SQUARE("which of the following numbers is both a square and a cube: (.*)") {
        @Override
        public Object answer(Matcher matcher) {
            final Iterable<Integer> numbers = getNumbers(matcher.group(1));
            final Predicate isSquareAndCubePredicate = new Predicate<Integer>() {
                @Override
                public boolean apply(@Nullable java.lang.Integer input) {
                    return Maths.isSquare(input) && Maths.isCube(input);
                }
            };

            final Optional<Integer> result = Iterables.tryFind(numbers, isSquareAndCubePredicate);
            if (result.isPresent()) {
                return result.get();
            }

            return "";
        }
    },

    PRIMES("which of the following numbers are primes: (.*)") {
        @Override
        public Object answer(Matcher matcher) {
            final Iterable<Integer> numbers = getNumbers(matcher.group(1));
            final Predicate<Integer> primeNumberPredicate = new Predicate<Integer>() {
                @Override
                public boolean apply(@Nullable java.lang.Integer input) {
                    return Maths.isPrime(input);
                }
            };

            return Joiner.on(", ").join(Iterables.filter(numbers, primeNumberPredicate));
        }
    },

    COLOR("what colour is a (\\b)") {
        @Override
        public Object answer(Matcher matcher) {
            final String fruit = matcher.group(1);

            if ("banana".equals(fruit)) {
                return "yellow";
            }

            return "?";
        }
    },

    FIBONACCI("what is the ([\\d]+).* number in the Fibonacci sequence") {
        @Override
        public Object answer(Matcher matcher) {
            return Maths.fibonacci1(Integer.valueOf(matcher.group(1)));
        }
    },

    GENERIC("(.*)") {
        @Override
        public Object answer(Matcher matcher) {
            return QuestionsCache.getAnswerTo(matcher.group(1));
        }
    };

    private static final Splitter NUMBERS_SPLITTER = Splitter.on(',').trimResults();

    private final String question;

    private QuestionType(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    private static Iterable<Integer> getNumbers(String numbersList) {
        final Function<String, Integer> stringToIntegerFunction = new Function<String, Integer>() {
            @Override
            public Integer apply(@Nullable java.lang.String input) {
                return Integer.valueOf(input);
            }
        };

        return Iterables.transform(NUMBERS_SPLITTER.split(numbersList), stringToIntegerFunction);
    }

    public abstract Object answer(Matcher matcher);
}
