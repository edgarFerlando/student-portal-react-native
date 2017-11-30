package id.refactory.app.refactoryapps;

import android.app.Application;
import android.content.Context;

import id.refactory.app.refactoryapps.dagger.ApplicationComponent;
import id.refactory.app.refactoryapps.dagger.ApplicationModule;
import id.refactory.app.refactoryapps.dagger.DaggerApplicationComponent;

/**
 * Created by dhihan on 28/11/17.
 */

public class RefactoryApplication extends Application {
    private static Application instance;
    private ApplicationComponent applicationComponent;

    public static RefactoryApplication get(Context context){
        return (RefactoryApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (instance == null)
            instance = this;
        this.initAppComponent();
    }

    private void initAppComponent() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(instance))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

}
