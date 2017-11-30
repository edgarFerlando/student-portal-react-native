package id.refactory.app.refactoryapps.dagger;

import javax.inject.Singleton;

import dagger.Component;
import id.refactory.app.refactoryapps.Assignments;
import id.refactory.app.refactoryapps.DetailAssignments;
import id.refactory.app.refactoryapps.sessions.SessionManager;

/**
 * Created by dhihan on 28/11/17.
 */

@Singleton
@Component(modules = {MainModule.class,ApplicationModule.class})

public interface ApplicationComponent {
    void inject (Assignments assignments);
    void inject (DetailAssignments detailAssignments);

}
