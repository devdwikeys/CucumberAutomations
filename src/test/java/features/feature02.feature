Feature: Automations stockbit

Scenario: TestCase number two
Given User go to website testing
When User in “Book Store” page
And User search book "Qa Engineer"
Then User see “No rows found”

Scenario: TestCase number three
Given User go to website testing
When User in “Book Store” page
And User search book "Git Pocket Guide"
And User click book “Git Pocket Guide”
Then User see “Git Pocket Guide”
