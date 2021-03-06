/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package no.ntnu.fp.model;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.fp.utils.LoadCallback;
import no.ntnu.fp.utils.Loader;
import no.ntnu.fp.utils.ServiceLoaders;


/**
 *
 * @author alxandr
 */
public class ServiceWrapper {

    public static interface EventsRunner
    {
        void run(List<Event> events);
    }

    public static interface LoginRunner
    {

        void run(boolean success);
    }

    public void getEvents(final EventsRunner runner) {
        getEvents(Communication.LoggedInUserEmail, runner);
    }

    public void getEvents(final String email, final EventsRunner runner)
    {
        Loader.loadAndRun(ServiceLoaders.getEvents(email), new LoadCallback() {

            public void run(Object object)
            {
                runner.run((List<Event>)object);
            }
        });
        //runner.run(new List<Event>());
    }

    public void login(final String username, final String password, final LoginRunner runner)
    {
        Loader.loadAndRun(ServiceLoaders.login(username, password), new LoadCallback() {

            public void run(Object object)
            {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Login complete<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                runner.run(((Boolean)object).booleanValue());
            }
        });
    }
}
