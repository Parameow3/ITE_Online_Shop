package kh.edu.rupp.ite.onlineshop.api.service;

import java.util.List;

import kh.edu.rupp.ite.onlineshop.api.model.Product;
import kh.edu.rupp.ite.onlineshop.api.model.Profile;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("products.json")
    Call<List<Product>> loadProductList();

    @GET("profile.json")
    Call<Profile> loadProfile();
}
