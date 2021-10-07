package com.crisdev.shaiofonodx;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

//https://www.youtube.com/watch?v=pwcG6npiXyo&t=27s
public class Preview_Logica extends AppCompatActivity {

    private ViewPager screenPager;
    Preview_Adapter introViewPagerAdapter ;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0 ;
    Button btnGetStarted;
    Animation btnAnim ;
    TextView tvSkip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // make the activity on full screen

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // when this activity is about to be launch we need to check if its openened before or not

        if (CreacionSharedPref())
        {
            Intent mainActivity = new Intent(getApplicationContext(), menu_principal.class );

            startActivity(mainActivity);
            finish();
        }

        setContentView(R.layout.previewapp);

        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);
        tvSkip = findViewById(R.id.tv_skip);

        // P A N T A L L A S

        final List<Preview_PreviewScreenItem> mList = new ArrayList<>();
        mList.add(new Preview_PreviewScreenItem("Info 1.\nProyecto Fono Dx","Sistema tecnológico para la prevención, diagnóstico y monitoreo de insuficiencia cardiaca secundaria a enfermedad valvular.",R.drawable.logoinsitucional));
        mList.add(new Preview_PreviewScreenItem("Info 2.\nProyecto Fono Dx","Información adicional del proyecto.",R.drawable.logoinsitucional));
        mList.add(new Preview_PreviewScreenItem("Info 3.\nProyecto Fono Dx","Beneficios que genera el proyecto a la comunidad.",R.drawable.logoinsitucional));



        // setup viewpager
        screenPager =findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new Preview_Adapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);

        // setup tablayout with viewpager

        tabIndicator.setupWithViewPager(screenPager);

        // next button click Listner

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                if (position < mList.size()) {

                    position++;
                    screenPager.setCurrentItem(position);
                }

                if (position == mList.size()-1) { // when we rech to the last screen

                    // TODO : show the GETSTARTED Button and hide the indicator and the next button
                    loaddLastScreen();
                }

            }
        });


        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
                {
                    if (tab.getPosition() == mList.size()-1)
                        {
                            loaddLastScreen();
                        }
                }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        // Get Started button click listener

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainActivity = new Intent(getApplicationContext(), menu_principal.class);
                startActivity(mainActivity);

                EditarSharedPRef();
                finish();

            }
        });

        // skip button click listener

        tvSkip.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                screenPager.setCurrentItem(mList.size());
            }
        });

    }

    private boolean CreacionSharedPref()
        {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("myPreferencia",MODE_PRIVATE);
            Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpenend",false);
            return  isIntroActivityOpnendBefore;
        }

    private void EditarSharedPRef()
        {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("myPreferencia",MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isIntroOpenend",true);
            editor.commit();
        }

    // show the GETSTARTED Button and hide the indicator and the next button
    private void loaddLastScreen() {

        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        // TODO : ADD an animation the getstarted button

        btnGetStarted.setAnimation(btnAnim);

    }
}
//https://www.youtube.com/watch?v=pwcG6npiXyo&t=27s


