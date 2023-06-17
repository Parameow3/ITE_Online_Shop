package kh.edu.rupp.ite.onlineshop.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import kh.edu.rupp.ite.onlineshop.R;
import kh.edu.rupp.ite.onlineshop.databinding.ActivityLandingBinding;
import kh.edu.rupp.ite.onlineshop.ui.fragment.HomeFragment;
import kh.edu.rupp.ite.onlineshop.ui.fragment.MoreFragment;
import kh.edu.rupp.ite.onlineshop.ui.fragment.ProductFragment;
import kh.edu.rupp.ite.onlineshop.ui.fragment.ProfileFragment;

public class LandingActivity extends AppCompatActivity {

    private ActivityLandingBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showFragment(new HomeFragment());

        // setup listeners
        binding.bottomNav.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.menuHome){
                showFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.menuProduct){
                showFragment(new ProductFragment());
            } else if (item.getItemId() == R.id.menuProfile){
                showFragment(new ProfileFragment());
            } else {
                showFragment(new MoreFragment());
            }

            return true;
        });
    }


    private void showFragment(Fragment fragment){
        // FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace HomeFragment in lytFragment
        fragmentTransaction.replace(binding.lytFragment.getId(), fragment);

        // Commit transaction
        fragmentTransaction.commit();
    }
}
