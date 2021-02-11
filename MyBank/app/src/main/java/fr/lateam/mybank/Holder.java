package fr.lateam.mybank;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Holder {

    @GET("accounts/{id}")
    Call<ArrayList<Base>> getAccounts(@Path("id") int id);
}
