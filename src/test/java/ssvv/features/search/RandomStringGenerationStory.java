package ssvv.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import ssvv.steps.serenity.RandomStringsEndUserSteps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SerenityRunner.class)
public class RandomStringGenerationStory {
    @Managed(uniqueSession = true)
    public WebDriver driver;

    @Steps
    public RandomStringsEndUserSteps user;

    private String valid_input_filepath = "C:\\Data\\Ayy\\web_project\\src\\test\\resources\\valid_input.csv";
    private String invalid_input_filepath = "C:\\Data\\Ayy\\web_project\\src\\test\\resources\\invalid_input.csv";


    private List<String> read_input_from_csv(String filepath) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records.get(0);
    }

    @Test
    public void generating_1_numeric_string_should_see_1_numeric_string() {
        List<String> input = read_input_from_csv(valid_input_filepath);
        user.is_on_string_generaion_page();
        user.enters(input.get(0), input.get(1));
        user.generates_strings();
        user.should_see_content_of_length(10);
        user.should_see_numeric_content();
    }

    @Test
    public void using_invalid_input_should_see_error_message() {
        List<String> input = read_input_from_csv(invalid_input_filepath);
        user.is_on_string_generaion_page();
        user.enters(input.get(0), input.get(1));
        user.generates_strings();
        user.should_see_error_message("Error: The num value must be an integer in the [1,10000] interval");
    }

    @Test
    public void generating_1_uppercase_string_should_see_1_uppercase_string() {
        List<String>input = read_input_from_csv(valid_input_filepath);
        user.is_on_string_generaion_page();
        user.enters(input.get(0), input.get(1));
        user.disables_numeric_filter();
        user.enables_uppercase_filter();
        user.generates_strings();
        user.should_see_content_of_length(10);
        user.should_see_uppercase_content();
    }

    @Test
    public void generating_with_all_filters_disabled_should_see_error_message() {
        List<String> input = read_input_from_csv(valid_input_filepath);
        user.is_on_string_generaion_page();
        user.enters(input.get(0), input.get(1));
        user.disables_numeric_filter();
        user.generates_strings();
        user.should_see_error_message("Error: At least one of the digits or alphabet boxes must be ticked");
    }
}
