package todomvc;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static core.CustomConditions.exactTexts;

public class TodosStepdefs {

    public static SelenideElement newTask = $("#new-todo");
    public static ElementsCollection tasks = $$("#todo-list li");

    @Given("^open TodoMVC page$")
    public void openTodoMVCPage() {
        if (!url().equals("https://todomvc4tasj.herokuapp.com/"))
            open("https://todomvc4tasj.herokuapp.com/");
    }

    @When("^add tasks: (.*)$")
    public void addTasks(List<String> taskTexts) {
        for (String text : taskTexts) {
            newTask.setValue(text).pressEnter();
        }
    }

    @Then("^tasks are: (.*)$")
    public void tasksAre(List<String> taskTexts) {
        tasks.shouldHave(exactTexts(taskTexts));
    }

    @And("^delete task (.*)$")
    public void deleteTask(String taskText) {
        tasks.find(exactText(taskText)).hover().$(".destroy").click();
    }

    public static SelenideElement startEditing(String oldText, String newText) {
        tasks.find(exactText(oldText)).doubleClick();
        return tasks.find(cssClass("editing")).$(".edit").setValue(newText);
    }

    @And("^edit task: (.*), (.*)")
    public void editTask(String oldText, String newText) {
        startEditing(oldText, newText).pressEnter();
    }

    @And("^edit task and cancel edit: (.*), (.*)$")
    public void editTaskAndCancelEdit(String oldText, String newText) {
        startEditing(oldText, newText).pressEscape();
    }

    @And("^edit task and click outside: (.*), (.*)$")
    public void editTaskAndClickOutside(String oldText, String newText) {
        startEditing(oldText, newText);
        newTask.click();
    }

    @And("^toggle task: (.*)$")
    public void toggleTask(String taskText) {
        tasks.find(exactText(taskText)).$(".toggle").click();
    }

    @And("^toggle all tasks$")
    public void toggleAllTasks() {
        $("#toggle-all").click();
    }

    @And("^clear completed tasks$")
    public void clearCompleteTask() {
        $("#clear-completed").click();
        $("#clear-completed").shouldBe(hidden);
    }

    @Then("^empty visible tasks$")
    public void assertEmptyVisibleTasks() {
        tasks.filter(visible).shouldBe(empty);
    }

    @Then("^visible tasks are: (.*)")
    public void assertVisibleTasks(List<String> taskTexts) {
        tasks.filter(visible).shouldHave(exactTexts((taskTexts)));
    }

    @Then("^items left: (.*)$")
    public void itemsLeft(int number) {
        $("#todo-count>strong").shouldHave(exactText(Integer.toString(number)));
    }
}
