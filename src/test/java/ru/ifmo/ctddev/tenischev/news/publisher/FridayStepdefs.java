package ru.ifmo.ctddev.tenischev.news.publisher;

import static org.junit.Assert.assertEquals;

import io.cucumber.java8.En;

/**
 * This class is
 *
 * @author setenish 07.09.2019.
 */
public class FridayStepdefs implements En {
    private String actualAnswer;
    private String today;

    public FridayStepdefs() {
        Given("today is {string}", (String day) -> today = day);
        When("I ask whether it's Friday yet", () -> actualAnswer = IsItFriday.isItFriday(today));
        Then("I should be told {string}", (String answer) -> assertEquals(answer, actualAnswer));
    }

    /**
     * This class is
     *
     * @author setenish 07.09.2019.
     */
    public static class IsItFriday {
        static String isItFriday(String today) {
            return "Friday".equals(today) ? "Yes" : "Nope";
        }
    }
}
