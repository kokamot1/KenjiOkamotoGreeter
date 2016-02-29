package com.mycompany.kenjiokamotogreeter;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {
    public MainActivityTests() {
        super(MainActivity.class);
    }

    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }

    public void testGreet() {
        MainActivity activity = getActivity();

        fillInNameEditText("Jake");
        clickGreetButton();

        TextView greetMessage =
                (TextView) activity.findViewById(R.id.message_text_view);

        String actualText = greetMessage.getText().toString();
        assertEquals("Hello, Jake!", actualText);
    }

    public void testReverseButtonIsInitiallyDisabled() {
        MainActivity activity = getActivity();

        Button reverseButton =
                (Button) activity.findViewById(R.id.reverse_button);
        assertEquals(false, reverseButton.isEnabled());

    }

    public void testReverseButtonIsEnabledAfterGreetButtonClick() {
        MainActivity activity = getActivity();
        clickGreetButton();
        Button reverseButton =
                (Button) activity.findViewById(R.id.reverse_button);
        assertEquals(true, reverseButton.isEnabled());

    }

    public void testTextIsReversedAfterReverseButtonClick() {
        MainActivity activity = getActivity();
        fillInNameEditText("Jake");
        clickGreetButton();
        clickReverseButton();
        TextView reversedMessage =
                (TextView) activity.findViewById(R.id.reverse_text_view);

        String actualText = reversedMessage.getText().toString();
        assertEquals("!ekaJ ,olleH", actualText);
    }

    private void clickGreetButton() {
        MainActivity activity = getActivity();
        Button greetButton =
                (Button) activity.findViewById(R.id.greet_button);

        TouchUtils.clickView(this, greetButton);
    }

    private void clickReverseButton() {
        MainActivity activity = getActivity();
        Button reverseButton =
                (Button) activity.findViewById(R.id.reverse_button);

        TouchUtils.clickView(this, reverseButton);
    }

    private void fillInNameEditText(String textToAdd) {
        MainActivity activity = getActivity();
        final EditText nameEditText =
                (EditText) activity.findViewById(R.id.greet_edit_text);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                nameEditText.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync(textToAdd);
    }

}
