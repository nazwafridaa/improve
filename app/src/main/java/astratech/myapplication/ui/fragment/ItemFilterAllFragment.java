package astratech.myapplication.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import astratech.myapplication.R;
import astratech.myapplication.model.Lomba;
import astratech.myapplication.model.Orang;
import astratech.myapplication.model.Seminar;
import astratech.myapplication.ui.activity.DetailLombaActivity;
import astratech.myapplication.ui.activity.LombaActivity;
import astratech.myapplication.ui.activity.ProfileOrangActivity;

/**
 * A fragment representing a list of Items.
 */
public class ItemFilterAllFragment extends Fragment {

    private static final String KEY_SEARCH = "search-value";
    private String data = "";
    private RecyclerView mRvFilterLomba, mRvFilterSeminar, mRvFilterOrang, mRvFilterLainnya;
    private LombaAdapter mLombaAdapter;
    private SeminarAdapter mSeminarAdapter;
    private OrangAdapter mOrangAdapter;
    private LainnyaAdapter mLainnyaAdapter;
    private View mEmptyData;
    private List<Lomba> mLombaList;
    private List<Seminar> mSeminarList;
    private List<Orang> mOrangList;
    public ItemFilterAllFragment() {
    }

    @SuppressWarnings("unused")
    public static ItemFilterAllFragment newInstance(String data) {
        ItemFilterAllFragment fragment = new ItemFilterAllFragment();
        Bundle args = new Bundle();
        args.putString(KEY_SEARCH, data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            data = getArguments().getString(KEY_SEARCH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_filter_all, container, false);

        mRvFilterLomba = view.findViewById(R.id.rv_filter_all_lomba);
        mRvFilterLomba.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRvFilterLomba.setAdapter(mLombaAdapter);

        mRvFilterSeminar = view.findViewById(R.id.rv_filter_all_seminar);
        mRvFilterSeminar.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRvFilterSeminar.setAdapter(mSeminarAdapter);

        mRvFilterOrang = view.findViewById(R.id.rv_filter_all_orang);
        mRvFilterOrang.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRvFilterOrang.setAdapter(mOrangAdapter);

        mRvFilterLainnya = view.findViewById(R.id.rv_filter_all_lainnya_lainnya);
        mRvFilterLainnya.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRvFilterLainnya.setAdapter(mLainnyaAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLombaList = dataLomba();
        mSeminarList = dataSeminar();
        mOrangList = dataOrang();
        updateUI(mLombaList);
        updateUI2(mSeminarList);
        updateUI3(mOrangList);
        updateUI4(mLombaList);
    }
    public List<Lomba> dataLomba(){
        List<Lomba> lombaList = new ArrayList<>();

        Lomba lomba = new Lomba();
        lomba.setIdLomba("1");
        lomba.setNamaLomba("Amikom Video Competition");
        lomba.setGambarPoster("lomba1");
        lombaList.add(lomba);

        Lomba lomba1 = new Lomba();
        lomba1.setIdLomba("2");
        lomba1.setNamaLomba("Teknologi Zaman Now");
        lomba1.setGambarPoster("lomba2");
        lombaList.add(lomba1);

        Lomba lomba2 = new Lomba();
        lomba2.setIdLomba("3");
        lomba2.setNamaLomba("Robonec");
        lomba2.setGambarPoster("lomba3");
        lombaList.add(lomba2);

        Lomba lomba3 = new Lomba();
        lomba3.setIdLomba("4");
        lomba3.setNamaLomba("E-Time");
        lomba3.setGambarPoster("lomba4");
        lombaList.add(lomba3);

        Lomba lomba4 = new Lomba();
        lomba4.setIdLomba("5");
        lomba4.setNamaLomba("Today Competition");
        lomba4.setGambarPoster("lomba5");
        lombaList.add(lomba4);

        return lombaList;
    }
    public List<Seminar> dataSeminar(){
        List<Seminar> seminarList = new ArrayList<>();

        Seminar seminar1 = new Seminar();
        seminar1.setIdSeminar("1");
        seminar1.setNamaSeminar("First Step");
        seminarList.add(seminar1);

        Seminar seminar2 = new Seminar();
        seminar2.setIdSeminar("2");
        seminar2.setNamaSeminar("Start Up");
        seminarList.add(seminar2);

        Seminar seminar3 = new Seminar();
        seminar3.setIdSeminar("3");
        seminar3.setNamaSeminar("Let Secure Your Code");
        seminarList.add(seminar3);

        Seminar seminar4 = new Seminar();
        seminar4.setIdSeminar("4");
        seminar4.setNamaSeminar("First Step");
        seminarList.add(seminar4);

        Seminar seminar5 = new Seminar();
        seminar5.setIdSeminar("5");
        seminar5.setNamaSeminar("Let Secure Your Code");
        seminarList.add(seminar3);

        return seminarList;
    }
    public List<Orang> dataOrang(){
        List<Orang> orangs = new ArrayList<>();
        for(int i  = 0; i < 5; i++){
            Orang orang = new Orang();
            orang.setIdOrang("" + i + 1);
            orang.setNamaOrang("Orang " + i);
            orangs.add(orang);
        }
        return orangs;
    }
    private void updateUI(List<Lomba> lombaList){
        mLombaAdapter = new LombaAdapter(mLombaList);
        mRvFilterLomba.setAdapter(mLombaAdapter);
    }
    private void updateUI2(List<Seminar> seminarList){
        mSeminarAdapter = new SeminarAdapter(seminarList);
        mRvFilterSeminar.setAdapter(mLombaAdapter);
    }
    private void updateUI3(List<Orang> orangList){
        mOrangAdapter = new OrangAdapter(orangList);
        mRvFilterOrang.setAdapter(mOrangAdapter);
    }
    private void updateUI4(List<Lomba> lombaList){
        mLombaAdapter = new LombaAdapter(mLombaList);
        mRvFilterLainnya.setAdapter(mLombaAdapter);
    }
    private class LombaAdapter extends RecyclerView.Adapter<LombaAdapter.LombaHolder>{
        private List<Lomba> mLombas;

        public LombaAdapter(List<Lomba> lombas){
            mLombas = lombas;
        }

        @NonNull
        @Override
        public LombaAdapter.LombaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

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
                super(inflater.inflate(R.layout.list_card_event, parent, false));

                mPoster = itemView.findViewById(R.id.cover_book_list_view);
                mNamaLomba = itemView.findViewById(R.id.nama_lomba);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Animation scaleDown = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_down);
                        itemView.startAnimation(scaleDown);
                        Intent intent = new Intent(getContext(), DetailLombaActivity.class);
//                        intent.putExtra(KEY_EXTRA, mKoleksi.getIdKoleksi());
                        startActivity(intent);
                    }
                });
            }
            public void bind(Lomba lomba){
                mLomba = lomba;
                mNamaLomba.setText(lomba.getNamaLomba());

                if(lomba.getIdLomba().equals("1")){
                    mPoster.setImageResource(R.drawable.lomba1);
                } else if (lomba.getIdLomba().equals("2")) {
                    mPoster.setImageResource(R.drawable.lomba2);
                } else if (lomba.getIdLomba().equals("3")) {
                    mPoster.setImageResource(R.drawable.lomba3);
                } else if (lomba.getIdLomba().equals("4")) {
                    mPoster.setImageResource(R.drawable.lomba4);
                } else if (lomba.getIdLomba().equals("5")) {
                    mPoster.setImageResource(R.drawable.lomba5);
                }
            }
        }
    }
    private class SeminarAdapter extends RecyclerView.Adapter<SeminarAdapter.SeminarHolder>{
        private List<Seminar> mSeminars;

        public SeminarAdapter(List<Seminar> seminars){
            mSeminars = seminars;
        }

        @NonNull
        @Override
        public SeminarAdapter.SeminarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new SeminarAdapter.SeminarHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(@NonNull SeminarAdapter.SeminarHolder holder, int position) {
            Seminar seminar = mSeminars.get(position);
            holder.bind(seminar);
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.fade_in);
            holder.itemView.startAnimation(animation);
        }

        @Override
        public int getItemCount() {
            return mSeminars.size();
        }

        private class SeminarHolder extends RecyclerView.ViewHolder{
            private ImageView mPoster;
            private TextView mNamaLomba;
            private Seminar mSeminar;

            public SeminarHolder(LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.list_card_event, parent, false));

                mPoster = itemView.findViewById(R.id.cover_book_list_view);
                mNamaLomba = itemView.findViewById(R.id.nama_lomba);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Animation scaleDown = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_down);
                        itemView.startAnimation(scaleDown);
                        Intent intent = new Intent(getContext(), DetailLombaActivity.class);
//                        intent.putExtra(KEY_EXTRA, mKoleksi.getIdKoleksi());
                        startActivity(intent);
                    }
                });
            }
            public void bind(Seminar seminar){
                mSeminar = seminar;
                mNamaLomba.setText(seminar.getNamaSeminar());

                if(seminar.getIdSeminar().equals("1")){
                    mPoster.setImageResource(R.drawable.seminar1);
                } else if (seminar.getIdSeminar().equals("2")) {
                    mPoster.setImageResource(R.drawable.seminar4);
                } else if (seminar.getIdSeminar().equals("3")) {
                    mPoster.setImageResource(R.drawable.seminar5);
                } else if (seminar.getIdSeminar().equals("4")) {
                    mPoster.setImageResource(R.drawable.seminar1);
                } else if (seminar.getIdSeminar().equals("5")) {
                    mPoster.setImageResource(R.drawable.seminar4);
                }
            }
        }
    }
    private class OrangAdapter extends RecyclerView.Adapter<OrangAdapter.OrangHolder>{
        private List<Orang> mOrangs;

        public OrangAdapter(List<Orang> orangs){
            mOrangs = orangs;
        }

        @NonNull
        @Override
        public OrangAdapter.OrangHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new OrangAdapter.OrangHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(@NonNull OrangAdapter.OrangHolder holder, int position) {
            Orang orang = mOrangs.get(position);
            holder.bind(orang);
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.fade_in);
            holder.itemView.startAnimation(animation);
        }

        @Override
        public int getItemCount() {
            return mOrangs.size();
        }

        private class OrangHolder extends RecyclerView.ViewHolder{
            private ImageView mPoster;
            private TextView mNamaOrang;
            private Orang mOrang;

            public OrangHolder(LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.list_card_orang, parent, false));

                mNamaOrang = itemView.findViewById(R.id.nama_lomba);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Animation scaleDown = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_down);
                        itemView.startAnimation(scaleDown);
                        Intent intent = new Intent(getContext(), ProfileOrangActivity.class);
//                        intent.putExtra(KEY_EXTRA, mKoleksi.getIdKoleksi());
                        startActivity(intent);
                    }
                });
            }
            public void bind(Orang orang){
                mOrang = orang;
                mNamaOrang.setText(orang.getNamaOrang());
            }
        }
    }
    private class LainnyaAdapter extends RecyclerView.Adapter<LainnyaAdapter.LombaHolder>{
        private List<Lomba> mLombas;

        public LainnyaAdapter(List<Lomba> lombas){
            mLombas = lombas;
        }

        @NonNull
        @Override
        public LainnyaAdapter.LombaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new LainnyaAdapter.LombaHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(@NonNull LainnyaAdapter.LombaHolder holder, int position) {
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
                super(inflater.inflate(R.layout.list_card_event, parent, false));

                mPoster = itemView.findViewById(R.id.cover_book_list_view);
                mNamaLomba = itemView.findViewById(R.id.nama_lomba);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Animation scaleDown = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_down);
                        itemView.startAnimation(scaleDown);
                        Intent intent = new Intent(getContext(), DetailLombaActivity.class);
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