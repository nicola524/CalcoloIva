package it.nicolab.calcoloiva.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;


public class MainActivity extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener  {

    SeekBar IVABar;
    EditText IVAImpostata, TotalePagato;
    TextView ImportoNettoIva, ImportoNetto, NettoDecemiali;

    NumberFormat formattatore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        formattatore = NumberFormat.getNumberInstance();
        formattatore.setMaximumFractionDigits(2);

        ImportoNettoIva = (TextView) findViewById(R.id.textViewImportoIva);
        ImportoNetto = (TextView) findViewById(R.id.textViewNetto);
        NettoDecemiali = (TextView) findViewById(R.id.textViewNettoDecemiali);
        IVAImpostata = (EditText) findViewById(R.id.editTextIVAImpostata);
        TotalePagato = (EditText) findViewById(R.id.TotalePagato);
        IVABar = (SeekBar) findViewById(R.id.seekBar);
        IVABar.setOnSeekBarChangeListener(this);

        TotalePagato.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                calcolo();

            }
        });


        IVAImpostata.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                calcolo();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        IVAImpostata.setText(String.valueOf(progress));

        calcolo();

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    /**
     *
     */
    private void calcolo() {

        double netto, iva;
        double nettoDecimali;

        try {
            if(Double.parseDouble(IVAImpostata.getText().toString()) > 0l) {
                netto = Double.parseDouble(TotalePagato.getText().toString()) / (((Double.parseDouble(IVAImpostata.getText().toString()) * 0.01) + 1));
                iva = Double.parseDouble(TotalePagato.getText().toString()) - netto;
                nettoDecimali = netto % (int) netto;
            }
            else {
                netto = 0;
                iva = 0;
                nettoDecimali = 0;
            }
        }
        catch (Exception e) {
            netto = 0;
            iva = 0;
            nettoDecimali = 0;
        }

        ImportoNettoIva.setText(String.valueOf(formattatore.format(iva)) + "€");
        ImportoNetto.setText(String.valueOf(formattatore.format(netto)) + "€");
        //NettoDecemiali.setText(String.valueOf(nettoDecimali));

    }

}
