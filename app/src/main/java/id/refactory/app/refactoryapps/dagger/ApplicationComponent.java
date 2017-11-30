package id.refactory.app.refactoryapps.dagger;

import javax.inject.Singleton;

import dagger.Component;
import id.refactory.app.refactoryapps.Assignments;
import id.refactory.app.refactoryapps.DetailAssignments;
import id.refactory.app.refactoryapps.GitLogin;
import id.refactory.app.refactoryapps.fragments.HRFragment;
import id.refactory.app.refactoryapps.fragments.HomeFragment;
import id.refactory.app.refactoryapps.fragments.OSFragment;
import id.refactory.app.refactoryapps.fragments.SOFFragment;
import id.refactory.app.refactoryapps.fragments.WPMFragment;

/**
 * Created by dhihan on 28/11/17.
 */

@Singleton
@Component(modules = {MainModule.class, ApplicationModule.class})
public interface ApplicationComponent {
    void inject(Assignments assignments);
    void inject(DetailAssignments detailAssignments);
    void inject(GitLogin gitLogin);
    void inject(HomeFragment homeFragment);
    void inject(HRFragment hrFragment);
    void inject(OSFragment osFragment);
    void inject(SOFFragment sofFragment);
    void inject(WPMFragment wpmFragment);
}
