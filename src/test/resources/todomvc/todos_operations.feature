@clean
Feature: Todos operations at All Filter

  Background:
    Given open TodoMVC page

  Scenario: Add tasks
    When add tasks: a, b, c
    Then tasks are: a, b, c

  Scenario: Edit task
    Given add tasks: a, b

    When edit task 'a' to have text 'a edited'
    Then tasks are: a edited, b

  Scenario: Cancel edit task
    Given add tasks: a, b

    When start edit task 'a' to have text 'a edited' and cancel edit by press escape
    Then tasks are: a, b

  Scenario: Edit task and click outside
    Given add tasks: a, b

    When start edit task 'a' to have text 'a edited' and approve edit by click outside
    Then tasks are: a edited, b

  Scenario: Delete task
    Given add tasks: a, b

    When delete task 'a'
    Then tasks are: b
    And items left counter shows: 1

  Scenario: Complete task
    Given add tasks: a, b

    When toggle task 'a'
    Then tasks are: a, b
    And items left counter shows: 1

  Scenario: Activate task
    Given add tasks: a, b
    And toggle task 'b'

    When toggle task 'b'
    Then tasks are: a, b
    And items left counter shows: 2

  Scenario: Complete and activate all tasks
    Given add tasks: a, b
    And toggle all tasks

    When toggle all tasks
    Then tasks are: a, b
    And items left counter shows: 2

  Scenario: Check all and clear
    Given add tasks: a, b
    And toggle all tasks

    When clear completed
    Then there is no tasks left
    And clear completed button should be hidden

