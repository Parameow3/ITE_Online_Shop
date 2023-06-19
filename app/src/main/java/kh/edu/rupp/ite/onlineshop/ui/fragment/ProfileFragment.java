package kh.edu.rupp.ite.onlineshop.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import kh.edu.rupp.ite.onlineshop.api.model.Profile;
import kh.edu.rupp.ite.onlineshop.api.service.ApiService;
import kh.edu.rupp.ite.onlineshop.databinding.FragmentProfileBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // load profile from server (api)
        loadProfileFromServer();
    }

    private void loadProfileFromServer(){

        // create retrofit client
        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://ferupp.s3.ap-southeast-1.amazonaws.com/Midterm/Profile/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // create service object
        ApiService apiService = httpClient.create(ApiService.class);

        // load profile from server
        Call<Profile> task = apiService.loadProfile();

        task.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getContext(), "Load Profile Successful!", Toast.LENGTH_LONG).show();
                    showProfile(response.body());

                }else {
                    Toast.makeText(getContext(), "Load Profile failed!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Toast.makeText(getContext(), "Load Profile failed!", Toast.LENGTH_LONG).show();
                Log.e("[ProfileFragment]", "Load Profile failed: " + t.getMessage());
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void showProfile(Profile profile){
        // set image to view
        Picasso.get().load(profile.getImgUrl()).into(binding.imgProfile);

        // set full name to Imageview
        binding.txtFullName.setText(profile.getFirst_name() +" "+ profile.getLast_name());

        // set email to Textview
        binding.txtEmail.setText(profile.getEmail());

        // set email to EditText
        binding.txtEditEmail.setText(profile.getEmail());

        // set phone number to edittext
        DecimalFormat df = new DecimalFormat("##,###,###");
        binding.txtEditPhoneNum.setText("0" + df.format(Integer.valueOf(profile.getPhoneNum())).replaceAll(",", " "));

        // set gender to edittext
        binding.txtEditGender.setText(profile.getGender());

        // set birthday to edittext
        binding.txtEditBoD.setText(profile.getBirthday());

        // set address to edittext
        binding.txtEditAddress.setText(profile.getAddress());
    }
}
