Feature: Build News from User input
  Build the News meet restrictions

  Scenario Outline: Build News
    Given new News with title <title>
    And text <text>
    And publisher <publisher>
    And expiration time is OK
    When I collect this News
    Then I should have News with this title <correctTitle>
    Then I should have News with this text <correctText>
    Then I should have News with this publisher <correctPublisher>

    Examples:
      | title                        | correctTitle  | text                | correctText         | publisher | correctPublisher |
      | "News title"                 | "News title"  | "Some text of News" | "Some text of News" | "I"       | "I"              |
      | "Very very very long title for my News thet must be cutted automatically" | "Very very very long title for my News thet must be" | "Some text of News" | "Some text of News" | "I"       | "I"              |

  Scenario: Build News with late date
    Given new News with title, text, publisher
    And expiration time is yesterday
    When I collect this News
    Then I should have News with expiration date tomorrow

  Scenario: Build News with date far in the future
    Given new News with title, text, publisher
    And expiration time is in next year
    When I collect this News
    Then I should have News with expiration date tomorrow