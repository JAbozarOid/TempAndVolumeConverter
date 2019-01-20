package com.example.temperatureconverter.interfaces;

public interface VolumeListener {

    String onLitrToMiliLitrConvert(int volume);
    String onLitrToGallonConvert(int volume);
    String onMiliLitrToLitrConvert(int volume);
    String onMiliLitrToGallonConvert(int volume);
    String onGallonToLitrConvert(int volume);
    String onGallonToMiliLitr(int volume);
}
