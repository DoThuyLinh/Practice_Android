package com.example.myself.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myself.karaoke.MainActivity;
import com.example.myself.karaoke.R;
import com.example.myself.model.Music;

import java.util.List;

public class MusicAdapter extends ArrayAdapter<Music> {

    Activity context;
    int resource;
    List<Music> objects;

    public MusicAdapter(@NonNull Activity context, int resource, @NonNull List<Music> objects) {
        super( context, resource, objects );
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate( this.resource,null );
        TextView txtId = row.findViewById( R.id.txtId );
        TextView txtSong = row.findViewById( R.id.txtSong );
        TextView txtSinger = row.findViewById( R.id.txtSinger );
        ImageButton btnLike = row.findViewById( R.id.btnLike );
        ImageButton btnDislike = row.findViewById( R.id.btnDislike );

        final Music music = this.objects.get( position );
        txtId.setText( music.getId() );
        txtSong.setText( music.getName() );
        txtSinger.setText( music.getSinger() );

        if(music.getLike()){
            btnLike.setVisibility( View.INVISIBLE );
            btnDislike.setVisibility( View.VISIBLE );
        }
        else{
            btnLike.setVisibility( View.VISIBLE );
            btnDislike.setVisibility( View.INVISIBLE );
        }

        btnLike.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyLike(music);
            }
        } );

        btnDislike.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyDislike(music);
            }
        } );

        return row;
    }

    private void xuLyDislike(Music music) {
        ContentValues row = new ContentValues(  );
        row.put( "likeSong",0 );
        MainActivity.database.update( "SongList",row,
                "idSong=?",new String[]{music.getId()});
    }

    private void xuLyLike(Music music) {
        ContentValues row = new ContentValues(  );
        row.put( "likeSong",1 );
        MainActivity.database.update( "SongList",row,
                "idSong=?",new String[]{music.getId()});

    }
}
