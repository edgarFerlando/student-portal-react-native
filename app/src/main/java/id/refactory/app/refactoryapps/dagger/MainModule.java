package id.refactory.app.refactoryapps.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import id.refactory.app.refactoryapps.api.services.RappClient;
import id.refactory.app.refactoryapps.api.services.RegAPI;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by dhihan on 28/11/17.
 */

@Module(includes = ApplicationModule.class)
public class MainModule {
    public static final String BASE_URL = "https://api.refactory.id";
//    private static Retrofit retrofit = null;

    @Provides @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor(){
        return new HttpLoggingInterceptor();
    }

    @Provides @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor interceptor){
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Provides
    Gson provideGson(){
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides @Singleton
    Retrofit provideRetrofitConnection(OkHttpClient client,Gson gson) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        return builder.build();
    }

//    @Provides @Singleton
//    public Retrofit provideRetrofit(Gson gson) {
//
//        retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//        return retrofit;
//    }

    @Provides
    public RappClient provideRappClient(Retrofit retrofit) {
        RappClient apiService = retrofit.create(RappClient.class);
        return apiService;
    }

    @Provides
    public RegAPI provideRegApi(Retrofit retrofit) {
        RegAPI api = retrofit.create(RegAPI.class);
        return api;
    }
}
