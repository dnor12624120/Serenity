package ssvv.features.search;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import ssvv.steps.serenity.EndUserSteps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SerenityRunner.class)
public class SearchByKeywordStory {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public EndUserSteps anna;

    @Issue("#WIKI-1")
    @Test
    public void searching_by_keyword_apple_should_display_the_corresponding_article() {
        anna.is_the_home_page();
        anna.looks_for("apple");
        anna.should_see_definition("A common, round fruit produced by the tree Malus domestica, cultivated in temperate climates.");

    }

    @Test
    public void searching_by_keyword_banana_should_display_the_corresponding_article() {
        anna.is_the_home_page();
        anna.looks_for("pear");
        anna.should_see_definition("An edible fruit produced by the pear tree, similar to an apple but elongated towards the stem.");
    }

    @Test
    public void searching_by_keyword_orange_should_display_the_corresponding_article() {
        anna.is_the_home_page();
        anna.looks_for("orange");
        anna.should_see_definition("An evergreen tree of the genus Citrus such as Citrus sinensis.");
    }

    @Test
    public void searching_by_keyword_tomato_should_display_the_corresponding_article() {
        anna.is_the_home_page();
        anna.looks_for("tomato");
        anna.should_see_definition("A widely cultivated plant, Solanum lycopersicum, having edible fruit.");
    }
} 