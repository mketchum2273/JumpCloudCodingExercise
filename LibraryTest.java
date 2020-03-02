import com.company.Action;
import com.company.ActionDetails;
import com.company.Library;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class LibraryTest {

    private Library library;
    Gson gson;

    private String testJumpAction;
    private String testRunAction;
    private String testSwimAction;

    @Before
    public void setup() {
        gson = new Gson();
        String action1 = gson.toJson(new Action("jump", 100));
        String action2 = gson.toJson(new Action("run", 75));
        String action3 = gson.toJson(new Action("jump", 200));

        library = new Library();
        library.addAction(action1);
        library.addAction(action2);
        library.addAction(action3);

        testJumpAction = gson.toJson(new Action("jump", 400));
        testRunAction = gson.toJson(new Action("run", 26));
        testSwimAction = gson.toJson(new Action("swim", 500));
    }

    // First, the initial (post-setup() method) state of the actions map is confirmed.
    // Then additional actions are added to the actions map.
    // Lastly, the final state of the actions map is confirmed.
    // This test confirms that the addAction() method properly handles the
    // updating of the key/value pairs with the addition of the new actions.
    @Test
    public void addAction() {
        Map<String, ActionDetails> actions = library.getActions();
        assertEquals(actions.get("jump").getTime(), 300);
        assertEquals(actions.get("jump").getCount(), 2);
        assertEquals(actions.get("run").getTime(), 75);
        assertEquals(actions.get("run").getCount(), 1);
        // An action for "swim" has not been added yet
        assertFalse(actions.containsKey("swim"));

        library.addAction(testJumpAction);
        library.addAction(testRunAction);
        library.addAction(testSwimAction);

        assertEquals(actions.get("jump").getTime(), 700);
        assertEquals(actions.get("jump").getCount(), 3);
        assertEquals(actions.get("run").getTime(), 101);
        assertEquals(actions.get("run").getCount(), 2);
        // The "swim" action exists now and has the appropriate values
        assertEquals(actions.get("swim").getTime(), 500);
        assertEquals(actions.get("swim").getCount(), 1);
    }

    // First, the length of the array created by the getStats() method is confirmed.
    // Then all of the action strings are deserialized and evaluated to ensure that the values for each key are correct.
    // Lastly, the output string for the array of action strings is checked for correctness.
    // This test confirms that the getStats() method correctly handles the calculating of the average time for each
    // action and that the appropriate JSON serialized strings are being returned and output.
    @Test
    public void getStats() {
        // These three addAction() calls need to be re-executed since this is a separate test
        library.addAction(testJumpAction);
        library.addAction(testRunAction);
        library.addAction(testSwimAction);

        String[] actionStrings = library.getStats();
        //There should be 3 actions in the array - jump, run, and swim
        assertEquals(actionStrings.length, 3);

        gson = new Gson();
        for (String action : actionStrings) {
            Action deserializedAction = gson.fromJson(action, Action.class);
            String actionName = deserializedAction.getName();
            int actionTime = deserializedAction.getTime();
            // Confirming correct results/calculations for each of the three actions
            if (actionName.equals("jump")) {
                assertEquals(actionTime, 234);
            } else if (actionName.equals("run")) {
                assertEquals(actionTime, 51);
            } else if (actionName.equals("swim")) {
                assertEquals(actionTime, 500);
            }
        }

        // Confirming that the final output of the JSON serialized strings is as expected
        assertEquals(Arrays.toString(actionStrings), "[{\"name\":\"run\",\"time\":51}, {\"name\":\"swim\",\"time\":500}, {\"name\":\"jump\",\"time\":234}]");
    }
}
