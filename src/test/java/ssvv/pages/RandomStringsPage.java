package ssvv.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("https://www.random.org/strings/")
public class RandomStringsPage extends PageObject {
    @FindBy(name="num")
    private WebElementFacade num_strings;

    @FindBy(name="len")
    private WebElementFacade string_length;

    @FindBy(name="digits")
    private WebElementFacade digits_allowed;

    @FindBy(name="upperalpha")
    private WebElementFacade uppercase_allowed;

    @FindBy(name="loweralpha")
    private WebElementFacade lowercase_allowed;

    @FindBy(xpath = "//input[@value='Get Strings']")
    private WebElementFacade generate_button;

    public void enter_num_string(String num) {
        num_strings.clear(); // there's data set by default so we have to clear it
        num_strings.type(num);
    }

    public void enter_string_length(String length) {
        string_length.clear(); // there's data set by default so we have to clear it
        string_length.type(length);
    }

    public void uncheck_numeric_filter() {
        digits_allowed.click();
    }

    public void check_uppercase_filter() {
        uppercase_allowed.click();
    }

    public void generate_strings() {
        generate_button.click();
    }

    public String get_generated_content() {
        return find(By.tagName("pre")).getText();
    }

    public String get_error_message() {
        return find(By.xpath("/html/body/div/p[1]")).getText();
    }
}
