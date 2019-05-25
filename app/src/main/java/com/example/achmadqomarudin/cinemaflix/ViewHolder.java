package com.example.achmadqomarudin.cinemaflix;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Achmad Qomarudin on 04/12/2017.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_nama;
    public ImageView iv_foto;
    public LinearLayout container;

    public ViewHolder(View rootView) {
        super(rootView);

        tv_nama = rootView.findViewById(R.id.tv_nama);
        iv_foto = rootView.findViewById(R.id.iv_foto);
        container = rootView.findViewById(R.id.container);
    }
}
