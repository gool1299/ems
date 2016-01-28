package com.apecengo.ems;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.contador_toxicidad) TextView mToxicidadTextView;
    @Bind(R.id.timer_text_view) TextView mTimerTextView;
    @Bind(R.id.toxicidad_card) CardView mToxicidadCardView;
    @Bind(R.id.texto_toxicidad) TextView mTextoToxicidad;

    @Bind(R.id.boton_morfina) Button mButtonMorfina;
    @Bind(R.id.boton_epidefrina) Button mButtonEpidefrina;
    @Bind(R.id.boton_tramadol) Button mButtonTramadol;
    @Bind(R.id.boton_sangre) Button mButtonSangre;

    private int mToxicidad = 0;
    CountDownTimer countDownTimer;




    public int getToxicidad() {
        return mToxicidad;
    }

    public void setToxicidad(int toxicidad) {
        mToxicidad = toxicidad;
        mToxicidadTextView.setText("" + Integer.toString(mToxicidad));
        cuentaAtras();
        actualizarEstilos();
    }

    public void addToxicidad(int toxicidad) {
        setToxicidad(toxicidad + getToxicidad());
    }

    public void quitarToxicidad(int toxicidad) {
        if ((getToxicidad() - toxicidad) <= 0) {
            setToxicidad(0);
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
        } else {
            setToxicidad(getToxicidad() - toxicidad);
            cuentaAtras();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        new MaterialDialog.Builder(this)
                .title("Descargo de responsabilidad")
                .content("Esta aplicación usa los conocimientos del EMS sobre la toxicidad. Sin embargo, todavía quedan muchos factores sobre el funcionamiento sin confirmar. Esta información debe ser considerada una aproximación.")
                .positiveText("Acepto")
                .show();

    }


    @OnClick(R.id.boton_morfina)
    public void morfinaClicked(View view) {
        addToxicidad(45);
    }


    @OnClick(R.id.boton_tramadol)
    public void tramadolClicked(View view) {
        addToxicidad(25);
    }


    @OnClick(R.id.boton_epidefrina)
    public void epidefrinaClicked(View view) {
        addToxicidad(50);
    }


    @OnClick(R.id.boton_sangre)
    public void sangreClicked(View view) {
        addToxicidad(10);
    }

    @OnClick(R.id.reset_button)
    public void resetClicked(View view) {
        setToxicidad(0);
        mTimerTextView.setText("");
        countDownTimer.cancel();
        countDownTimer = null;
    }

    public void actualizarEstilos() {
        if (getToxicidad() >= 100) {
            mToxicidadCardView.setCardBackgroundColor(Color.parseColor("#3e2723"));
            mToxicidadTextView.setTextColor(Color.parseColor("#e57373"));
            mTextoToxicidad.setTextColor(Color.parseColor("#ffffff"));
            mTimerTextView.setTextColor(Color.parseColor("#ffffff"));
        } else if (getToxicidad() >= 70) {
            mToxicidadCardView.setCardBackgroundColor(Color.parseColor("#b71c1c"));
            mToxicidadTextView.setTextColor(Color.parseColor("#ffffff"));
            mTextoToxicidad.setTextColor(Color.parseColor("#ffffff"));
            mTimerTextView.setTextColor(Color.parseColor("#ffffff"));
        } else if (getToxicidad() >= 50) {
            mToxicidadCardView.setCardBackgroundColor(Color.parseColor("#ef6c00"));
            mToxicidadTextView.setTextColor(Color.parseColor("#ffffff"));
            mTextoToxicidad.setTextColor(Color.parseColor("#ffffff"));
            mTimerTextView.setTextColor(Color.parseColor("#ffffff"));
        } else if (getToxicidad() >= 20) {
            mToxicidadCardView.setCardBackgroundColor(Color.parseColor("#0277bd"));
            mToxicidadTextView.setTextColor(Color.parseColor("#ffffff"));
            mTextoToxicidad.setTextColor(Color.parseColor("#ffffff"));
            mTimerTextView.setTextColor(Color.parseColor("#ffffff"));
        } else {
            mToxicidadCardView.setCardBackgroundColor(Color.parseColor("#ffffff"));
            mToxicidadTextView.setTextColor(Color.parseColor("#000000"));
            mTextoToxicidad.setTextColor(Color.parseColor("#000000"));
            mTimerTextView.setTextColor(Color.parseColor("#000000"));
        }

        if (getToxicidad() >= 50) {
            mButtonMorfina.getBackground().setColorFilter(Color.parseColor("#e53935"), PorterDuff.Mode.MULTIPLY);
            mButtonMorfina.setTextColor(Color.parseColor("#ffffff"));
        } else {
            mButtonMorfina.getBackground().setColorFilter(null);
            mButtonMorfina.setTextColor(Color.parseColor("#000000"));
        }

        if (getToxicidad() >= 70) {
            mButtonTramadol.getBackground().setColorFilter(Color.parseColor("#e53935"), PorterDuff.Mode.MULTIPLY);
            mButtonTramadol.setTextColor(Color.parseColor("#ffffff"));
        } else {
            mButtonTramadol.getBackground().setColorFilter(null);
            mButtonTramadol.setTextColor(Color.parseColor("#000000"));
        }

        if (getToxicidad() >= 45) {
            mButtonEpidefrina.getBackground().setColorFilter(Color.parseColor("#e53935"), PorterDuff.Mode.MULTIPLY);
            mButtonEpidefrina.setTextColor(Color.parseColor("#ffffff"));
        } else {
            mButtonEpidefrina.getBackground().setColorFilter(null);
            mButtonEpidefrina.setTextColor(Color.parseColor("#000000"));
        }

        if (getToxicidad() >= 85) {
            mButtonSangre.getBackground().setColorFilter(Color.parseColor("#e53935"), PorterDuff.Mode.MULTIPLY);
            mButtonSangre.setTextColor(Color.parseColor("#ffffff"));
        } else {
            mButtonSangre.getBackground().setColorFilter(null);
            mButtonSangre.setTextColor(Color.parseColor("#000000"));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            new MaterialDialog.Builder(this)
                    .title("Acerca de")
                    .content("Esta aplicación ha sido creada para el EMS de PoPlife por Manolo Pérez (Apecengo). Versión " + BuildConfig.VERSION_NAME)
                    .positiveText("Cerrar")
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }



    public void cuentaAtras() {
            if (countDownTimer == null) {
                countDownTimer = new CountDownTimer(26000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        if (getToxicidad() <= 0) {
                            this.cancel();
                            countDownTimer = null;
                        } else {
                            mTimerTextView.setText("" + millisUntilFinished / 1000);
                        }
                    }

                    public void onFinish() {
                        mTimerTextView.setText("0");
                        quitarToxicidad(10);
                        this.start();
                    }
                }.start();
            }
    }


}
