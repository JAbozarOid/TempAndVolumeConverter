package com.example.temperatureconverter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;

import com.example.temperatureconverter.interfaces.VolumeListener;
import com.example.temperatureconverter.utils.CelsiusConverter;
import com.example.temperatureconverter.utils.FahrenheitConverter;
import com.example.temperatureconverter.utils.KelvinConverter;
import com.example.temperatureconverter.utils.PrimitiveConverter;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements VolumeListener {


    @BindView(R.id.edt_input_temp)
    AppCompatEditText edtInputTemp;
    @BindView(R.id.tv_c_to_f)
    AppCompatTextView tvCToF;
    @BindView(R.id.tv_c_to_k)
    AppCompatTextView tvCToK;
    @BindView(R.id.tv_f_to_c)
    AppCompatTextView tvFToC;
    @BindView(R.id.tv_f_to_k)
    AppCompatTextView tvFToK;
    @BindView(R.id.tv_k_to_c)
    AppCompatTextView tvKToC;
    @BindView(R.id.tv_k_to_f)
    AppCompatTextView tvKToF;
    @BindView(R.id.edt_input_volume)
    AppCompatEditText edtInputVolume;
    @BindView(R.id.tv_l_to_ml)
    AppCompatTextView tvLToMl;
    @BindView(R.id.tv_l_to_g)
    AppCompatTextView tvLToG;
    @BindView(R.id.tv_ml_to_l)
    AppCompatTextView tvMlToL;
    @BindView(R.id.tv_ml_to_g)
    AppCompatTextView tvMlToG;
    @BindView(R.id.tv_g_to_l)
    AppCompatTextView tvGToL;
    @BindView(R.id.tv_g_to_ml)
    AppCompatTextView tvGToMl;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private VolumeListener mListener;
    private double miliLitr,litr,gallon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mListener = this;

        compositeDisposable.add(
                RxTextView.textChangeEvents(edtInputTemp)
                        .skipInitialValue()
                        .debounce(300, TimeUnit.MILLISECONDS)
                        .distinctUntilChanged()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(tempConverter()));

        compositeDisposable.add(
                RxTextView.textChangeEvents(edtInputVolume)
                        .skipInitialValue()
                        .debounce(300, TimeUnit.MILLISECONDS)
                        .distinctUntilChanged()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(volumeConverter()));

    }

    private DisposableObserver<TextViewTextChangeEvent> tempConverter() {
        return new DisposableObserver<TextViewTextChangeEvent>() {
            @Override
            public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                Log.d("Temp", textViewTextChangeEvent.text().toString());
                if (!textViewTextChangeEvent.text().toString().isEmpty()) {
                    int temp = new PrimitiveConverter(textViewTextChangeEvent.text().toString()).convertStringToInt();
                    tvCToF.setText(new PrimitiveConverter().convertDoubleToString(new FahrenheitConverter(temp).convertCelsiusToFarenhet()));
                    tvCToK.setText(new PrimitiveConverter().convertDoubleToString(new KelvinConverter(temp).convertCelsiusToKelvin()));
                    tvFToC.setText(new PrimitiveConverter().convertDoubleToString(new CelsiusConverter(temp).convertFahrenheitToCelsius()));
                    tvFToK.setText(new PrimitiveConverter().convertDoubleToString(new KelvinConverter(temp).convertFarenheitToKelvin()));
                    tvKToC.setText(new PrimitiveConverter().convertDoubleToString(new CelsiusConverter(temp).convertKelvinToCelsius()));
                    tvKToF.setText(new PrimitiveConverter().convertDoubleToString(new FahrenheitConverter(temp).convertKelvinToFarenheit()));
                } else {
                    tvCToF.setText("");
                    tvCToK.setText("");
                    tvFToC.setText("");
                    tvFToK.setText("");
                    tvKToC.setText("");
                    tvKToF.setText("");
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.e("onError: ", e.getMessage());
            }

            @Override
            public void onComplete() {
            }
        };
    }

    private DisposableObserver<TextViewTextChangeEvent> volumeConverter() {
        return new DisposableObserver<TextViewTextChangeEvent>() {
            @Override
            public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                Log.d("Temp", textViewTextChangeEvent.text().toString());
                if (!textViewTextChangeEvent.text().toString().isEmpty()) {
                    int volume = new PrimitiveConverter(textViewTextChangeEvent.text().toString()).convertStringToInt();
                    tvLToMl.setText(mListener.onLitrToMiliLitrConvert(volume));
                    tvLToG.setText(mListener.onLitrToGallonConvert(volume));
                    tvMlToL.setText(mListener.onMiliLitrToLitrConvert(volume));
                    tvGToL.setText(mListener.onGallonToLitrConvert(volume));
                } else {
                    tvLToMl.setText("");
                    tvLToG.setText("");
                    tvMlToL.setText("");
                    tvGToL.setText("");
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.e("onError: ", e.getMessage());
            }

            @Override
            public void onComplete() {
            }
        };
    }

    @Override
    public String onLitrToMiliLitrConvert(int volume) {
        miliLitr = volume * 1000;
        return new PrimitiveConverter().convertDoubleToString(miliLitr);
    }

    @Override
    public String onLitrToGallonConvert(int volume) {
        gallon = volume * 0.264172052358148;
        return new PrimitiveConverter().convertDoubleToString(gallon);
    }

    @Override
    public String onMiliLitrToLitrConvert(int volume) {
        litr = volume / 1000;
        return new PrimitiveConverter().convertDoubleToString(litr);
    }

    @Override
    public String onMiliLitrToGallonConvert(int volume) {
        return "";
    }

    @Override
    public String onGallonToLitrConvert(int volume) {
        litr = volume * 3.7854;
        return new PrimitiveConverter().convertDoubleToString(litr);
    }

    @Override
    public String onGallonToMiliLitr(int volume) {
        return "";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
