package com.example.android.miwok;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by marci on 21.11.2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int color;
    MediaPlayer mp;
   private AudioManager audioManager  ;

    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if(i==AudioManager.AUDIOFOCUS_GAIN){
                mp.start();
            }else if(i==AudioManager.AUDIOFOCUS_LOSS){
                stopMusic();

            }else if(i==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || i==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mp.pause();
                //mp.seekTo(0);

            }

        }
    };





    public WordAdapter(@NonNull Context context,  @NonNull List<Word> objects, int color) {
        super(context, 0, objects);
        this.color = color;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Word word = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }

        TextView englishTrans = (TextView) convertView.findViewById(R.id.text1huj);
        TextView miwokTrans = (TextView) convertView.findViewById(R.id.text2huj);
        ImageView resourceImageId = (ImageView) convertView.findViewById(R.id.imageView);


        englishTrans.setText("  " + word.getDefaultTranslation());
        miwokTrans.setText("  " +word.getMiwokTranslation());

        //Obrazki xd
        if(word.isHasImage())
        {
            resourceImageId.setImageResource(word.getResourceImageid());
        } else {
            resourceImageId.setVisibility(View.INVISIBLE);
        }

        //Kolorki xd
        //View linearLayoutColor  = convertView.findViewById(R.id.linearLayoutColor);
        int cc = ContextCompat.getColor(getContext(),color);
        //linearLayoutColor.setBackgroundColor(cc);
        englishTrans.setBackgroundColor(cc);
        miwokTrans.setBackgroundColor(cc);

        //AudioManager

        audioManager = (AudioManager)getContext().getSystemService(Context.AUDIO_SERVICE) ;



        //Muzyczka
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int result = audioManager.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    stopMusic();
                    mp = MediaPlayer.create(getContext(), word.getResourceAudioId());
                    mp.start();

                }


               // Log.v(getContext().getClass().getName(), "Current word: " + word);

            }
        };

        englishTrans.setOnClickListener(onClickListener);
        miwokTrans.setOnClickListener(onClickListener);








        return convertView;
    }








    public void stopMusic(){
       // mp.stop();
        if(mp != null){
            mp.release();
            mp = null;
            audioManager.abandonAudioFocus(audioFocusChangeListener);
        }

    }
}
