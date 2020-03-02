package com.company;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Library {
    private Map<String, ActionDetails> actions;
    private Gson gson;

    public Library() {
        this.actions = new HashMap<>();
    }

    // Update the actions map with the contents of the JSON serialized string being passed in
    public void addAction(String actionString) {
        synchronized(this) {
            gson = new Gson();
            Action newAction = gson.fromJson(actionString, Action.class);
            String actionName = newAction.getName();
            int actionTime = newAction.getTime();

            if (actions.containsKey(actionName)) {
                // The actionName already exists as a key, so update the ActionDetails value for the pair
                ActionDetails previousActionDetails = actions.get(actionName);
                int updatedTime = previousActionDetails.getTime() + actionTime;
                int updatedCount = previousActionDetails.getCount() + 1;
                ActionDetails updatedActionDetails = new ActionDetails(updatedTime, updatedCount);
                actions.put(actionName, updatedActionDetails);
            } else {
                // Add a new action name/details pair to the actions map
                ActionDetails newActionDetails = new ActionDetails(actionTime, 1);
                actions.put(actionName, newActionDetails);
            }
        }
    }

    // Return the contents of the actions map as an array of JSON serialized strings
    public String[] getStats() {
        synchronized(this) {
            String[] actionStats = new String[actions.size()];
            int index = 0;
            Iterator it = actions.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                String name = (String) pair.getKey();
                ActionDetails actionDetails = (ActionDetails) pair.getValue();
                int detailsTime = actionDetails.getTime();
                int detailsCount = actionDetails.getCount();
                int time = (detailsTime + detailsCount - 1) / detailsCount;
                Action action = new Action(name, time);

                gson = new Gson();
                String actionString = gson.toJson(action);
                actionStats[index] = actionString;
                index++;
            }

            return actionStats;
        }
    }

    // This getter exists solely for use in a unit test
    public Map<String, ActionDetails> getActions() {
        return actions;
    }
}
