package com.example.myself.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myself.animation.R;

import java.util.List;

public class MyAdapter extends ArrayAdapter<String> {

    Activity context;
    int resource;
    List<String> objects;

    public MyAdapter(Activity context, int resource, List<String> objects) {
        super( context, resource, objects );
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View row = layoutInflater.inflate( R.layout.item_animation,parent,false);
        TextView txtData = row.findViewById( R.id.tvData );
        txtData.setText( this.objects.get( position ) );

        Animation animation = AnimationUtils.loadAnimation( this.context,R.anim.hieuunglistview );
        animation.setDuration(200);
        row.startAnimation( animation );
        return row;
    }
}
