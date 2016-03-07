@clean
Feature: Todos operations

  Background:
    Given open TodoMVC page

  Scenario: add tasks
    When add tasks: a, b, c
    Then tasks are: a, b, c

  Scenario: delete task
    When add tasks: a, b
    And delete task a
    Then tasks are: b

  Scenario: edit task
    When add tasks: a, b
    And edit task: a, a edited
    Then tasks are: a edited, b

  Scenario: cancel edit task
    When add tasks: a, b
    And edit task and cancel edit: a, a edited
    Then tasks are: a, b

  Scenario: edit task and click outside
    When add tasks: a, b
    And edit task and click outside: a, a edited
    Then tasks are: a edited, b

  Scenario: complete task
    When add tasks: a, b
    And toggle task: a
    Then items left: 1

  Scenario: activate all tasks
    When add tasks: a, b
    And toggle all tasks
    Then items left: 0
    And toggle all tasks
    Then tasks are: a, b

  Scenario: activate task
    When add tasks: a, b
    And toggle task: a
    Then items left: 1
    And toggle task: a
    Then tasks are: a, b


    