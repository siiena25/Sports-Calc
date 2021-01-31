package com.example.sportscalc.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.session.MediaSessionManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.sportscalc.R;
import com.example.sportscalc.fragments.CalcFragment;
import com.example.sportscalc.fragments.DiaryFragment;
import com.example.sportscalc.fragments.SettingsFragment;
import com.example.sportscalc.fragments.StartFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private String currentFragment = null;

    private boolean isFirstEnter = false;

    private TextView toolbarTitleView;

    private Toolbar toolbar;
    private BottomNavigationView bottomNavigation;

    private static final String CURRENT_FRAGMENT = "Current fragment";

    private static final int TOOLBAR_DIARY_TEXT = R.string.toolbar_diary_text;
    private static final int TOOLBAR_CALC_TEXT = R.string.toolbar_calc_text;
    private static final int TOOLBAR_SETTINGS_TEXT = R.string.toolbar_settings_text;

    private static final int FRAGMENT_SETTINGS_NAME = R.string.fragment_settings_name;
    private static final int FRAGMENT_DIARY_NAME = R.string.fragment_diary_name;
    private static final int FRAGMENT_CALC_NAME = R.string.fragment_calc_name;
    private static final int FRAGMENT_START_NAME = R.string.fragment_start_name;

    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayShowTitleEnabled(false);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationListener);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        boolean getSharedIsFirstEnter = sharedPreferences.getBoolean("isFirstEnter", false);

        if (!getSharedIsFirstEnter) {
            firstEnter();
            editor.putBoolean("isFirstEnter", true);
        }

        if (currentFragment == null) {
            bottomNavigation.setSelectedItemId(R.id.nav_calc);
        }

        if (savedInstanceState != null) {
            currentFragment = savedInstanceState.getString(CURRENT_FRAGMENT);
        }
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public Context getThis() {
        return this;
    }

    public String getCurrentFragment() {
        return currentFragment;
    }

    public BottomNavigationView getBottomNavigation() {
        return bottomNavigation;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    private void firstEnter() {
        toolbar.setVisibility(View.GONE);
        bottomNavigation.setVisibility(View.GONE);
        StartFragment startFragment = new StartFragment();
        currentFragment = getString(FRAGMENT_START_NAME);
        loadFragment(startFragment, currentFragment);
    }

    public void setCurrentFragment(String currentFragment) {
        this.currentFragment = currentFragment;
    }

    public void setToolbar(String nameSelectedFragment) {
        toolbarTitleView = findViewById(R.id.toolbar_title);

        switch (nameSelectedFragment) {
            case "diary":
                toolbarTitleView.setText(TOOLBAR_DIARY_TEXT);
                break;
            case "calc":
                toolbarTitleView.setText(TOOLBAR_CALC_TEXT);
                break;
            case "settings":
                toolbarTitleView.setText(TOOLBAR_SETTINGS_TEXT);
                break;
        }
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navigationListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @SuppressLint("NonConstantResourceId")
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    String nameSelectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_calc:
                            selectedFragment = new CalcFragment();
                            nameSelectedFragment = getResources().getString(FRAGMENT_CALC_NAME);
                            break;
                        case R.id.nav_diary:
                            selectedFragment = new DiaryFragment();
                            nameSelectedFragment = getResources().getString(FRAGMENT_DIARY_NAME);
                            break;
                        case R.id.nav_settings:
                            selectedFragment = new SettingsFragment();
                            nameSelectedFragment = getResources().getString(FRAGMENT_SETTINGS_NAME);
                            break;
                    }

                    if (currentFragment == null || !currentFragment.equals(nameSelectedFragment)) {
                        assert selectedFragment != null;
                        loadFragment(selectedFragment, currentFragment);
                        currentFragment = nameSelectedFragment;
                    }
                    return true;
                }
            };

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            undoFragment();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CURRENT_FRAGMENT, currentFragment);
    }

    public void loadFragment(Fragment fragment, String currentFragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(currentFragment)
                .commit();
    }

    public void undoFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        }
    }

    public void clearBackStack() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}
