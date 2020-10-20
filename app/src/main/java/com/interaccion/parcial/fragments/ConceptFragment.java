package com.interaccion.parcial.fragments;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.interaccion.parcial.R;


public class ConceptFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView listView;

    public ConceptFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_concept, container, false);
        listView = v.findViewById(R.id.lv_concept_fragment);

        final String[] terms = {
                "SDK",
                "NDK",
                "Actividad",
                "Permisos",
                "Fragmento",
                "Ciclo de vida",
                "API Level",
                "Intent"
        };
        final String[] definitions = {
                "Un software development kit, abreviado SDK, es un paquete de herramientas y datos que facilita e incluso permite a los programadores desarrollar programas en un lenguaje concreto o para una plataforma o aplicación específica.",
                "El NDK permite instalar bibliotecas escritas en C, C++ y otros lenguajes, una vez compiladas para ARM, MIPS o código nativo x86.",
                "La clase Activity es un componente crucial de una aplicación de Android, y la forma en que se inician y combinan las actividades es una parte fundamental del modelo de aplicación de la plataforma.",
                "Una aplicación básica de Android no tiene permisos asociados de manera predeterminada. Esto significa que no puede hacer nada que afecte negativamente la experiencia del usuario o los datos en el dispositivo",
                "Un Fragment representa un comportamiento o una parte de la interfaz de usuario en una FragmentActivity. Puedes combinar varios fragmentos en una sola actividad para crear una IU multipanel y volver a usar un fragmento en diferentes actividades.",
                "A lo largo de su vida, una actividad pasa por varios estados. En general una actividad puede contenerse en 4 estados: inexistente, detenida, pausada o en ejecución.",
                "Esto se refiere a la minima version del sistema operativo donde se soporta una caracteristica o una aplicación.",
                "Una Intent es un objeto de mensajería que puedes usar para solicitar una acción de otro componente de una app."
        };

        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), R.layout.fragment_concept_list_view, R.id.tv_example, terms);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object object = listView.getItemAtPosition(position);

                Bundle bundle = new Bundle();
                bundle.putString("Term", object.toString());
                bundle.putString("Definition", definitions[position]);

                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    DefinitionFragment definitionFragment = new DefinitionFragment();
                    definitionFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.flFragment, definitionFragment, "DEFINITIONFRAG").commit();
                } else {
                    getFragmentManager().findFragmentByTag("DEFINITIONFRAG").setArguments(bundle);
                    Fragment frg = getFragmentManager().findFragmentByTag("DEFINITIONFRAG");
                    final FragmentTransaction ft = getFragmentManager().beginTransaction();

                    ft.detach(frg);
                    ft.attach(frg);
                    ft.commit();
                }
            }
        });

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {}
}
