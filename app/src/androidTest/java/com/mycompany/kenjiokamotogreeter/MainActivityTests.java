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

        final EditText nameEditText =
                (EditText) activity.findViewById(R.id.greet_edit_text);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                nameEditText.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("Jake");

        Button greetButton =
                (Button) activity.findViewById(R.id.greet_button);

        TouchUtils.clickView(this, greetButton);

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

    private void clickGreetButton() {
        MainActivity activity = getActivity();
        Button greetButton =
                (Button) activity.findViewById(R.id.greet_button);

        TouchUtils.clickView(this, greetButton);
    }

}
