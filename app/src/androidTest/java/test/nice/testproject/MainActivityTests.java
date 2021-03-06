package test.nice.testproject;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import test.nice.testproject.activities.MainActivity;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openContextualActionModeOverflowMenu;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.isFocusable;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityTests  {

	/** Launches {@link MainActivity} for every test */
	@Rule
	public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * Click a button and change the text of a TextView.
     */
    @Test
    public void testSwapText() {
        onView(withId(R.id.exampleButton)).perform(click());
        onView(allOf(withId(R.id.exampleText), withText(R.string.example_text_after))).check(matches(isDisplayed()));
    }

    /**
     * Test a button is enabled.
     */
    @Test
    public void testIsEnabled() {
        onView(withId(R.id.exampleButton)).check(matches(isEnabled()));
    }

    /**
     * Test checking a checkbox
     */
    @Test
    public void testCheckingACheckBox() {
        onView(withId(R.id.enabled_checkbox)).check(matches(isNotChecked())).perform(click()).check(matches(isChecked()));
    }

    /**
     * Test checking a checkbox
     * Test if you can press go, or next within an EditText
     */
    @Test
    public void testHasIME() {
        //hasImeAction(R.id.contentDescriptionText);
    }

    /**
     * Test checking a checkbox
     */
    @Test
    public void testADisabledCheckbox() {
        onView(withId(R.id.disabled_checkbox)).check(matches(not(isEnabled())));
    }

    /**
     * Test a button is clickable.
     */
    @Test
    public void testIsClickable() {
        onView(withId(R.id.exampleButton)).check(matches(isClickable()));
    }

    /**
     * Click on a contextual menu item from the Overflow menu.
     */
    @Test
    public void testActionMenuItemClick() {
        openContextualActionModeOverflowMenu();
        onView(withText(R.string.action_settings)).perform(click());
    }

    /**
     * Test if an EditText is focusable.
     */
    @Test
    public void testEditTextIsFocusable() {
        onView(withId(R.id.exampleEditText)).check(matches(isFocusable()));
    }


    /**
     * Type text and confirm that text has been typed by searching for the text
     */
    @Test
    public void testTypeText() {
        String exampleText = "Here is a long piece of text to type out.";
        onView(withId(R.id.exampleEditText)).perform(typeText(exampleText));
        // Example confirming this text has been succesfully typed with just the text.
        onView(withText(exampleText)).check(matches(isDisplayed()));
    }

    /**
     * Type text and confirm that text has been typed by searching for the text
     */
    @Test
    public void testTypeTextThenClear() {
        String exampleText = "Here is a long piece of text to type out.";
        onView(withId(R.id.exampleEditText)).perform(typeText(exampleText));
        // Example confirming this text has been succesfully typed with just the text.
        onView(withText(exampleText)).check(matches(isDisplayed()));
        onView(withId(R.id.exampleEditText)).perform(clearText());
        // Check it is empty
        onView(withId(R.id.exampleEditText)).check(matches(withText("")));
    }
    /**
     * Type text and confirm that text has been typed by searching for the text
     */
    @Test
    public void testTypeTextThenReplace() {
        String exampleText = "Here is a long piece of text to type out.";
        String exampleReplaceText = "Here is a long piece of text to replace.";
        onView(withId(R.id.exampleEditText)).perform(typeText(exampleText));
        // Example confirming this text has been succesfully typed with just the text.
        onView(withText(exampleText)).check(matches(isDisplayed()));
        onView(withId(R.id.exampleEditText)).perform(replaceText(exampleReplaceText));
        // Check it is empty
        onView(allOf(withId(R.id.exampleEditText), withText(exampleReplaceText))).check(matches(isDisplayed()));
    }
    /**
     * Type text and confirm that text has been typed by searching for the text and the ID of the textbox.
     */
    @Test
    public void testTypeTextWithTextAndId() {
        String exampleText = "Here is a long piece of text to type out.";
        onView(withId(R.id.exampleEditText)).perform(typeText(exampleText));
        // Example confirming this text has been succesfully typed with just the text.
        onView(allOf(withText(exampleText), withId(R.id.exampleEditText))).check(matches(isDisplayed()));
    }

    /**
     * Focus on an EditText and then close the Soft Keyboard that is displayed.
     */
    @Test
    public void testTypeTextCloseSoftKeyboard() {
        onView(withId(R.id.exampleEditText)).perform(click());
        closeSoftKeyboard();
    }


    /**
     * Test the Content Description of a TextView
     */
    @Test
    public void testContentDescription() {
        String exampleContentDescription = activityRule.getActivity().getString(R.string.example_content_description);
        onView(withId(R.id.contentDescriptionText)).check(matches(hasContentDescription()));
        onView(allOf(withId(R.id.contentDescriptionText), withContentDescription(exampleContentDescription))).check(matches(isDisplayed()));
    }

    /**
     * Test textView startsWith
     */
    @Test
    public void testStartsWith() {
        String textStartsWith = activityRule.getActivity().getString(R.string.example_content_description).substring(0, 5);
        onView(allOf(withId(R.id.contentDescriptionText), withText(startsWith(textStartsWith)))).check(matches(isDisplayed
				()));
    }

    /**
     * Test textView endsWith
     */
    @Test
    public void testEndsWith() {
        String textEndsWith =  activityRule.getActivity().getString(R.string.example_content_description);
        textEndsWith = textEndsWith.substring(textEndsWith.length() - 4);
        onView(allOf(withId(R.id.contentDescriptionText), withText(endsWith(textEndsWith)))).check(matches(isDisplayed()));
    }


    /**
     * Test textView endsWith
     */
    @Test
    public void testScrollToButton() {
        onView(withId(R.id.offscreen_button)).check(matches(not(isDisplayed()))).perform(scrollTo()).check(matches(isDisplayed()));
    }

    /**
     * Test swipe down.
     */
    @Test
    public void testScrollDown() {
        onView(withId(R.id.scroll_view)).perform(swipeUp()/* Got to swipe up to scroll down. */);
    }

    /**
     * Test swipe down.
     */
    @Test
    public void testScrollUp() {
        onView(withId(R.id.scroll_view)).perform(swipeDown()/* Got to swipe down to scroll up. */);
    }

    /**
     * Test swipe down.
     */
    @Test
    public void testSelectWithHint() {
        onView(withHint(R.string.example_text_hint)).check(matches(isDisplayed()));
    }
}