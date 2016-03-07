package todomvc;

import cucumber.api.java.After;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

public class TodosStepHooks {

    @After("@clean")
    public void clearData(){
        executeJavaScript("localStorage.clear()");
        open("https://todomvc4tasj.herokuapp.com/");
    }
}
