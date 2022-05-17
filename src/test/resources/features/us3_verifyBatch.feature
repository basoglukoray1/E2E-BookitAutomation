@smoke
Feature:User information verification


  @db @wip
  Scenario:three point/layer (UI,API,DATABASE)

    Given user logs in using "lfinnisz@yolasite.com" "lissiefinnis"
    And user is on the my self page
    And I get batch name from my self page
    And I logged in Bookit API using "student_leader"
    When I GET the current leader batch name from API
    And I get the batch name from Database
    Then UI,API and Database user batch information must match