package com.cedisropa.pruebaarquitectura.Pruebas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.cedisropa.pruebaarquitectura.R;
import com.cedisropa.pruebaarquitectura.utils.ResourceProvider;
import com.cedisropa.sdk.DatosGenerales;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.KeyEvent.KEYCODE_ENTER;
import static android.view.KeyEvent.keyCodeFromString;

public class PruebasActivity extends AppCompatActivity implements PruebasView {

    @BindView(R.id.etnumeroSurtidor)
    EditText numeroSurtido;

    @BindView(R.id.numsegundocuadro)
    TextView nomSurtidor;

    private PruebasViewPresenter viewPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ResourceProvider resourceProvider = new ResourceProvider(this.getResources());
        this.viewPresenter = new PruebasPresenter(this, resourceProvider);

        DatosGenerales datosGenerales = new DatosGenerales();
        datosGenerales.setIpBodega("10.28.114.110");
        datosGenerales.setNumTerminal(1286);
        datosGenerales.setNumArea(1);

        viewPresenter.recibirDatosGenerales(datosGenerales);


        numeroSurtido.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {

                if (keyCode == KeyEvent.KEYCODE_ENTER && numeroSurtido.length() > 0) {

                    int numSurtidor = Integer.valueOf(numeroSurtido.getText().toString());
                    viewPresenter.obtenerNombreSurtidor(numSurtidor);

                }


                return false;
            }
        });
    }


    @Override
    public void mostrarNomSurtidor(String pNomSurtidor) {
        nomSurtidor.setText(pNomSurtidor);
    }
}