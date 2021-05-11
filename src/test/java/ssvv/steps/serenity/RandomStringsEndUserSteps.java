package ssvv.steps.serenity;

import net.thucydides.core.annotations.Step;
import ssvv.pages.RandomStringsPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class RandomStringsEndUserSteps {
    RandomStringsPage page;
    String valid_data_filepath = "";
    String invalid_data_filepath = "";

    @Step
    public void is_on_string_generaion_page() {
        page.open();
    }

    @Step
    public void enters(String numStrings, String stringLength) {
        page.enter_num_string(numStrings);
        page.enter_string_length(stringLength);
    }

    @Step
    public void disables_numeric_filter() {
        page.uncheck_numeric_filter();
    }

    @Step
    public void enables_uppercase_filter() {
        page.check_uppercase_filter();
    }

    @Step
    public void generates_strings() {
        page.generate_strings();
    }

    @Step
    public void should_see_content_of_length(int length) {
        assert(page.get_generated_content().length() == length);
    }

    @Step
    public void should_see_numeric_content() {
        boolean is_numeric = true;
        String content = page.get_generated_content();
        for(int i = 0; i < content.length() && is_numeric; i++) {
            if(!Character.isDigit(content.charAt(i))) {
                is_numeric = false;
            }
        }
        assert(is_numeric);
    }

    @Step
    public void should_see_uppercase_content() {
        boolean is_uppercase = true;
        String content = page.get_generated_content();
        for(int i = 0; i < content.length() && is_uppercase; i++) {
            if(!Character.isUpperCase(content.charAt(i))) {
                is_uppercase = false;
            }
        }
        assert(is_uppercase);
    }

    @Step
    public void should_see_error_message(String error_message) {
        System.err.println(page.get_error_message());
        assert(page.get_error_message().equals(error_message));
    }
}
