package com.apecengo.ems;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toxicidad_card) CardView mToxicidadCardView;
    @Bind(R.id.texto_toxicidad) TextView mTextoToxicidad;
    @Bind(R.id.contador_toxicidad) TextView mToxicidadTextView;
    @Bind(R.id.timer_text_view) TextView mTimerTextView;

    @Bind(R.id.boton_morfina) Button mButtonMorfina;
    @Bind(R.id.boton_epidefrina) Button mButtonEpidefrina;
    @Bind(R.id.boton_tramadol) Button mButtonTramadol;
    @Bind(R.id.boton_sangre) Button mButtonSangre;

    private VisibilityManager mVisibilityManager = new VisibilityManager();

    private static final int TOXICIDAD_MAX = 140;

    private int mToxicidad = 0;
    private boolean mVibrationEnabled = true;
    CountDownTimer countDownTimer;


    @Override
    protected void onResume() {
        super.onResume();

        mVisibilityManager.setIsVisible(true);
    }

    @Override
    protected void onPause() {
        mVisibilityManager.setIsVisible(false);

        super.onPause();
    }

    public int getToxicidad() {
        return mToxicidad;
    }

    public void setToxicidad(int toxicidad) {
        if (toxicidad >= TOXICIDAD_MAX) {
            mToxicidad = TOXICIDAD_MAX;
        } else {
            mToxicidad = toxicidad;
        }
        mToxicidadTextView.setText("" + Integer.toString(mToxicidad));
        countDown();
        actualizarEstilos();
    }

    public void addToxicidad(int toxicidad) {
        if(!(getToxicidad() >= TOXICIDAD_MAX)) {
            setToxicidad(toxicidad + getToxicidad());
        }
    }

    public void quitarToxicidad(int toxicidad) {
        if ((getToxicidad() - toxicidad) <= 0) {
            setToxicidad(0);
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
        } else {
            setToxicidad(getToxicidad() - toxicidad);
            countDown();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


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

    public void setColors(String fondo, String textoToxicidad, String textos) {
        mToxicidadCardView.setCardBackgroundColor(Color.parseColor(fondo));
        mToxicidadTextView.setTextColor(Color.parseColor(textoToxicidad));
        mTextoToxicidad.setTextColor(Color.parseColor(textos));
        mTimerTextView.setTextColor(Color.parseColor(textos));
    }

    public void actualizarEstilos() {
        if (getToxicidad() >= 100) {
            setColors("#3e2723", "#e57373", "#ffffff");
        } else if (getToxicidad() >= 70) {
            setColors("#b71c1c", "#ffffff", "#ffffff");
        } else if (getToxicidad() >= 50) {
            setColors("#ef6c00", "#ffffff", "#ffffff");
        } else if (getToxicidad() >= 20) {
            setColors("#0277bd", "#ffffff", "#ffffff");
        } else {
            setColors("#ffffff", "#000000", "#000000");
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

        switch (item.getItemId()) {

            case R.id.action_settings:

                new MaterialDialog.Builder(this)
                        .title("Acerca de")
                        .content("Esta aplicación ha sido creada para el EMS de PoPlife por Manolo Pérez (Apecengo). Versión " + BuildConfig.VERSION_NAME)
                        .positiveText("Cerrar")
                        .show();

                return false;
            case R.id.action_settings_disclaimer:

                // Deshabilitado por el momento. TODO: Que sólo aparezca la primera vez.
                new MaterialDialog.Builder(this)
                        .title("Descargo de responsabilidad")
                        .content("Esta aplicación usa los conocimientos del EMS sobre la toxicidad. Sin embargo, todavía quedan muchos factores sobre el funcionamiento sin confirmar. Esta información debe ser considerada una aproximación.")
                        .positiveText("Acepto")
                        .show();

                return false;

            case R.id.action_settings_vibration:
                if (item.isChecked()) {
                    item.setChecked(false);
                    mVibrationEnabled = false;
                } else {
                    item.setChecked(true);
                    mVibrationEnabled = true;
                }

                return false;

            default:
                return false;

        }



    }

    public boolean shouldVibrate() {
        if (!mVibrationEnabled) {
            return false;
        }
        if (!mVisibilityManager.isIsVisible()) {
            return false;
        }

        return true;
    }

    public void vibrate(int time) {
        if(shouldVibrate()) {
            // Get instance of Vibrator from current Context https://stackoverflow.com/questions/13950338/how-to-make-an-android-device-vibrate
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            // Vibrate for 100 milliseconds
            v.vibrate(time);
        }
    }

    public void countDown() {
            if (countDownTimer == null) {
                countDownTimer = new CountDownTimer(26000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        if (getToxicidad() <= 0) {
                            this.cancel();
                            countDownTimer = null;
                            if(shouldVibrate()) {
                                vibrate(150);
                            }
                        } else {
                            mTimerTextView.setText("" + millisUntilFinished / 1000);
                        }
                    }

                    public void onFinish() {
                        mTimerTextView.setText("0");
                        quitarToxicidad(10);

                        vibrate(50);
                        this.start();
                    }
                }.start();
            }
    }


}
