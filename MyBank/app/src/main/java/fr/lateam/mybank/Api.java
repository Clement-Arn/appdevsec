package fr.lateam.mybank;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static Retrofit retrofit = null;
    public static Holder getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://6007f1a4309f8b0017ee5022.mockapi.io/api/m1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        //Creating object for our interface
        Holder api = retrofit.create(Holder.class);
        return api;
    }
}

