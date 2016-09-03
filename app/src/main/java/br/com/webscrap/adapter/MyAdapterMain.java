package br.com.webscrap.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import br.com.webscrap.InfoActivity;
import br.com.webscrap.R;
import br.com.webscrap.model.Evento;

/**
 * Created by Gustavo Terras on 16/06/2016.
 */
public class MyAdapterMain extends RecyclerView.Adapter<MyAdapterMain.ViewHolder> {

    private static final String TAG = MyAdapterMain.class.getSimpleName();
    private List<Evento> eventos;
    private Context context;

    public MyAdapterMain(Context context, List<Evento> eventos) {
        this.context = context;
        this.eventos = eventos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_main, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Evento casa = eventos.get(position);

        Glide.with(context).load(casa.getCapa())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(holder.picture);
    }

    private void setAnimation(View viewToAnimate, int anim) {
        viewToAnimate.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(context, anim);
        viewToAnimate.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnTouchListener{
        private ImageView picture;

        ViewHolder(View view) {
            super(view);
            this.picture = (ImageView) view.findViewById(R.id.img_content);

            view.setOnClickListener(this);
            view.setOnTouchListener(this);
        }

        @Override
        public void onClick(View view) {
            context.startActivity(new Intent(context, InfoActivity.class).putExtra("extra", eventos.get(getPosition())));
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction()==MotionEvent.ACTION_DOWN)
                setAnimation(v, android.R.anim.fade_out);
            if(event.getAction() == MotionEvent.ACTION_UP)
                setAnimation(v, android.R.anim.fade_in);
            return false;
        }
    }
}