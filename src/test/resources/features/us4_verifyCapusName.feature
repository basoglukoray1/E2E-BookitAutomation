@smoke
Feature:User information verification


  @db @pair4
  Scenario:three point/layer (UI,API,DATABASE)

    Given user logs in using "kd34@msu.edu" "kevindurant34"
    And user is on the my self page
    And I GET campus location from my self page
    And I logged Bookit API using with student "student_member_4"
    When I GET the current student campus location from API
    And I GET the campus location from Database
    Then UI,API and Database user campus location must match