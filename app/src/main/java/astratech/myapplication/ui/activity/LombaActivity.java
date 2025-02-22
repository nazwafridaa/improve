package astratech.myapplication.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import astratech.myapplication.R;
import astratech.myapplication.model.Lomba;
import astratech.myapplication.ui.fragment.HomeFragment;

public class LombaActivity extends AppCompatActivity {
    private ViewPager2 mViewPager2;
    private ImageView mBackBtn;
    private RecyclerView mRVLomba, mRVLombaLain;
    private Handler slideHandler = new Handler();
    private List<Lomba> mLomba;
    private LombaAdapter mLombaAdapter;
    private LombaLainAdapter mLombaLainAdapter;
    private boolean showAllItems = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lomba);

        mViewPager2 = findViewById(R.id.slider);
        mViewPager2.setClipToPadding(false);
        mViewPager2.setClipChildren(false);
        mViewPager2.setOffscreenPageLimit(5);
        mViewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        mRVLomba = findViewById(R.id.rv_lomba_lomba);
        mRVLomba.setLayoutManager(new LinearLayoutManager(LombaActivity.this, LinearLayoutManager.HORIZONTAL, false));
        mRVLomba.setAdapter(mLombaAdapter);

        mRVLombaLain = findViewById(R.id.rv_lomba_lainnya);
        mRVLombaLain.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRVLombaLain.setAdapter(mLombaLainAdapter);
        mRVLombaLain.setItemAnimator(null);
        mRVLombaLain.setOverScrollMode(mRVLombaLain.OVER_SCROLL_NEVER);

        mBackBtn = findViewById(R.id.back_buttonn);

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(30));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r*0.15f);
            }
        });
        mViewPager2.setPageTransformer(compositePageTransformer);

        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                slideHandler.removeCallbacks(sliderRunnable);
                slideHandler.postDelayed(sliderRunnable, 2000);
            }
        });
        List<Lomba> lombas = dataLomba();
        updateSlider(lombas);
        updateLomba(lombas);
        updateLombaLain(lombas);

        ImageView textViewLihatLainnya = findViewById(R.id.textViewLihatLainnya);
        textViewLihatLainnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllItems = !showAllItems;
                mLombaLainAdapter.notifyDataSetChanged();
                if (showAllItems) {
                    textViewLihatLainnya.setVisibility(View.GONE);
                }
            }
        });
    }
    private void updateSlider(List<Lomba> lombaList){
        mLomba = lombaList;
        mViewPager2.setAdapter(new SliderAdapter(mLomba,mViewPager2));
    }
    private void updateLomba(List<Lomba> lombaList){
        mLomba = lombaList;
        mLombaAdapter = new LombaAdapter(mLomba);
        mRVLomba.setAdapter(mLombaAdapter);
    }
    private void updateLombaLain(List<Lomba> lombaList){
        mLomba = lombaList;
        mLombaLainAdapter = new LombaLainAdapter(mLomba);
        mRVLombaLain.setAdapter(mLombaLainAdapter);
    }
    public List<Lomba> dataLomba(){
        List<Lomba> lombaList = new ArrayList<>();
        for(int i  = 0; i < 5; i++){
            Lomba lomba = new Lomba();
            lomba.setIdLomba("" + i + 1);
            lomba.setNamaLomba("Lomba " + i);
            lombaList.add(lomba);
        }
        return lombaList;
    }
    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            mViewPager2.setCurrentItem(mViewPager2.getCurrentItem() + 1);
        }
    };
    private class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderHolder>{
        private List<Lomba> mLombas;
        private ViewPager2 mViewPager2;

        public SliderAdapter(List<Lomba> lombas, ViewPager2 viewPager2){
            mLombas = lombas;
            mViewPager2 = viewPager2;
        }

        @NonNull
        @Override
        public SliderAdapter.SliderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(LombaActivity.this);

            return new SliderAdapter.SliderHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull SliderAdapter.SliderHolder holder, int position) {
            Lomba lomba = mLombas.get(position);
            holder.onBindViewHolder(lomba);
        }

        @Override
        public int getItemCount() {
            return mLombas.size();
        }

        private class SliderHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            private TextView mNamaLomba, mKategori, mTanggal;
            private ImageView mPoster;
            private Lomba mLomba;

            public SliderHolder(LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.list_banner_card, parent, false));

                mPoster = itemView.findViewById(R.id.cover_book_list_view);
                mNamaLomba = itemView.findViewById(R.id.nama_lomba);
                mTanggal = itemView.findViewById(R.id.tgl_lomba);

                itemView.setOnClickListener(this);
            }
            public void onBindViewHolder(Lomba lomba){
                mLomba = lomba;
                mNamaLomba.setText(mLomba.getNamaLomba());
            }
            @Override
            public void onClick(View view){
                Animation scaleDown = AnimationUtils.loadAnimation(LombaActivity.this, R.anim.scale_down);
                itemView.startAnimation(scaleDown);
                Intent intent = new Intent(LombaActivity.this, DetailLombaActivity.class);
//                        intent.putExtra(KEY_EXTRA, mKoleksi.getIdKoleksi());
                startActivity(intent);            }
        }
    }
    private class LombaAdapter extends RecyclerView.Adapter<LombaAdapter.LombaHolder>{
        private List<Lomba> mLombas;

        public LombaAdapter(List<Lomba> lombas){
            mLombas = lombas;
        }

        @NonNull
        @Override
        public LombaAdapter.LombaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(LombaActivity.this);

            return new LombaAdapter.LombaHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(@NonNull LombaAdapter.LombaHolder holder, int position) {
            Lomba lomba = mLombas.get(position);
            holder.bind(lomba);
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.fade_in);
            holder.itemView.startAnimation(animation);
        }

        @Override
        public int getItemCount() {
            return mLombas.size();
        }

        private class LombaHolder extends RecyclerView.ViewHolder{
            private ImageView mPoster;
            private TextView mNamaLomba;
            private Lomba mLomba;

            public LombaHolder(LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.list_rekomendasi_home, parent, false));

                mPoster = itemView.findViewById(R.id.cover_image);
                mNamaLomba = itemView.findViewById(R.id.title);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Animation scaleDown = AnimationUtils.loadAnimation(LombaActivity.this, R.anim.scale_down);
                        itemView.startAnimation(scaleDown);
                        Intent intent = new Intent(LombaActivity.this, DetailLombaActivity.class);
//                        intent.putExtra(KEY_EXTRA, mKoleksi.getIdKoleksi());
                        startActivity(intent);
                    }
                });
            }
            public void bind(Lomba lomba){
                mLomba = lomba;
                mNamaLomba.setText(lomba.getNamaLomba());
            }
        }
    }

    private class LombaLainAdapter extends RecyclerView.Adapter<LombaLainAdapter.LombaHolder>{
        private List<Lomba> mLombas;

        public LombaLainAdapter(List<Lomba> lombas){
            mLombas = lombas;
        }

        @NonNull
        @Override
        public LombaLainAdapter.LombaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(LombaActivity.this);

            return new LombaLainAdapter.LombaHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(@NonNull LombaLainAdapter.LombaHolder holder, int position) {
/*            Lomba lomba = mLombas.get(position);
            holder.bind(lomba);
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.fade_in);
            holder.itemView.startAnimation(animation);*/

            if (!showAllItems && position >= 3) {
                holder.itemView.setVisibility(View.GONE);
                holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            } else {
                Lomba lomba = mLombas.get(position);
                holder.bind(lomba);
                Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.fade_in);
                holder.itemView.startAnimation(animation);
                holder.itemView.setVisibility(View.VISIBLE);
                holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
            }
        }

        @Override
        public int getItemCount() {
            return mLombas.size();
        }

        private class LombaHolder extends RecyclerView.ViewHolder{
            private ImageView mPoster;
            private TextView mNamaLomba;
            private Lomba mLomba;

            public LombaHolder(LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.list_card_event, parent, false));

                mPoster = itemView.findViewById(R.id.cover_book_list_view);
                mNamaLomba = itemView.findViewById(R.id.nama_lomba);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Animation scaleDown = AnimationUtils.loadAnimation(LombaActivity.this, R.anim.scale_down);
                        itemView.startAnimation(scaleDown);
                        Intent intent = new Intent(LombaActivity.this, DetailLombaActivity.class);
//                        intent.putExtra(KEY_EXTRA, mKoleksi.getIdKoleksi());
                        startActivity(intent);
                    }
                });
            }
            public void bind(Lomba lomba){
                mLomba = lomba;
                mNamaLomba.setText(lomba.getNamaLomba());
            }
        }
    }


}