package com.interaccion.parcial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import com.interaccion.parcial.fragments.ConceptFragment;
import com.interaccion.parcial.fragments.DefinitionFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ConceptFragment conceptFragment;
    DefinitionFragment definitionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            getSupportFragmentManager().beginTransaction().add(R.id.flFragment, new ConceptFragment(), "CONCEPTFRAG").commit();
        } else {
            getSupportFragmentManager().beginTransaction().add(R.id.landConceptFragment, new ConceptFragment(), "CONCEPTFRAG").commit();
            getSupportFragmentManager().beginTransaction().add(R.id.landDefinitionFragment, new DefinitionFragment(), "DEFINITIONFRAG").commit();
        }

    }

    @Override
    public void onBackPressed()
    {
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            ConceptFragment myFragment = (ConceptFragment) getSupportFragmentManager().findFragmentByTag("CONCEPTFRAG");
            if (myFragment.isVisible() && myFragment != null) {
                finish();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new ConceptFragment(), "CONCEPTFRAG").commit();
            }
        }
    }
}