package org.hello.spark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static spark.Spark.get;

public class HelloSpark {
    private static Logger logger = LoggerFactory.getLogger(HelloSpark.class);

    public static void main(String[] args) {
        get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                final String question = request.queryParams("q");
                logger.info("Question is: " + question);

                final Object answer = answer(question);
                logger.info("Answer is: " + answer);

                return answer;
            }
        });
    }

    private static Object answer(String question) {
        for (QuestionType questionType : QuestionType.values()) {
            final Pattern pattern = Pattern.compile(".+: " + questionType.getQuestion());
            final Matcher matcher = pattern.matcher(question);

            if (matcher.matches()) {
                return questionType.answer(matcher);
            }
        }

        return "unknown question !";
    }
}
