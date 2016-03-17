package todomvc;

import cucumber.api.java.After;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class TodosStepHooks {

    @After("@clean")
    public void clearData(){
        executeJavaScript("localStorage.clear()");
    }
}
