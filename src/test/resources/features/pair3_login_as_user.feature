@smoke
Feature:User information verification

@db @pair3
Scenario:three point/layer (UI,API,DATABASE)

  Given user  logs in using "Jedios@msu.edu" "osmanjedi1234"
  And user is  on the my self page
  And I get  team name from my self page
  And I logged  Bookit API using "student_member_3"
  When I GET  the current student team name from API
  And I get the  team name from Database
  Then UI,API  and Database user information must match