package test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by anton on 06.11.2023
 */
public class CheckStudentFormTest {

    @BeforeAll
    public static void beforeAll(){
        Configuration.browser="chrome";
        Configuration.browserSize="1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen=false;
        Configuration.pageLoadStrategy="eager";

    }

    @Test
     void testFillForm(){

        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("Anton");
        $("#lastName").setValue("Pavlovich");
        $("#userEmail").setValue("anton@gmail.com");
        $("label.custom-control-label[for='gender-radio-3']").click();
        $("#userNumber").setValue("9123456789");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("1989");
        $(".react-datepicker__day--013").click();
        $("#subjectsInput").setValue("Chemistry").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("pic1.jpeg");
        $("#currentAddress").setValue("Default adress city street");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Karnal")).click();
        $("#submit").click();

        $(".table-responsive").shouldHave(text("Anton Pavlovich"));
        $(".table-responsive").shouldHave(text("anton@gmail.com"));
        $(".table-responsive").shouldHave(text("Other"));
        $(".table-responsive").shouldHave(text("9123456789"));
        $(".table-responsive").shouldHave(text("Chemistry"));
        $(".table-responsive").shouldHave(text("Sports, Music"));
        $(".table-responsive").shouldHave(text("pic1.jpeg"));
        $(".table-responsive").shouldHave(text("Chemistry"));
        $(".table-responsive").shouldHave(text("Default adress city street"));
        $(".table-responsive").shouldHave(text("Haryana Karnal"));

    }

}
