@smoke
Feature: User information verification

  Background:
    Given user logs in using "email" "password"
    And user is on the my self page

  @db @pair1
  Scenario: three point/layer (UI,API,DATABASE)

    And I logged Bookit api using "user"
    When I get the current student full name and role from api
    Then UI,API and Database user information must be match

  @db @pair2
  Scenario: three point/layer (UI,API,DATABASE)

    And I logged Bookit api using "user"
    When I get the current user information from api
    Then UI,API and Database user information must be match

  @db @pair3
  Scenario: three point/layer (UI,API,DATABASE)

    And I logged Bookit api using "user"
    When I get the current user information from api
    Then UI,API and Database user information must be match

  @db @pair4
  Scenario: three point/layer (UI,API,DATABASE)

    And I logged Bookit api using "user"
    When I get the current user information from api
    Then UI,API and Database user information must be match