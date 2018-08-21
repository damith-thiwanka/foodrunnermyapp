package lk.electfast.thiwanka.foodrunner_myapp.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import lk.electfast.thiwanka.foodrunner_myapp.R;
import lk.electfast.thiwanka.foodrunner_myapp.adapters.CustomItemClickListener;
import lk.electfast.thiwanka.foodrunner_myapp.adapters.GallaryItemAdapter;
import lk.electfast.thiwanka.foodrunner_myapp.models.CardItem;
import lk.electfast.thiwanka.foodrunner_myapp.models.CardModel;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GalleryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GalleryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GalleryFragment extends Fragment {
    private String TAG = GalleryFragment.class.getSimpleName();


    @BindView(R.id.galleryRecycleView)
    RecyclerView gellaryView;
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.RatingBar)
    RatingBar ratingBar;
    @BindView(R.id.txt_rating)
    TextView rating;
    @BindView(R.id.btn_filter)
    TextView btnFilter;
    @BindView(R.id.btn_cancel)
    TextView CancelFilters;
    @BindView(R.id.FilterLayout)
    LinearLayout FilterLayout;


    private GallaryItemAdapter gallaryItemAdapter;
    private List<CardItem> cardItems;


    private OnFragmentInteractionListener mListener;

    public GalleryFragment() {
    }

    public static GalleryFragment newInstance() {
        GalleryFragment fragment = new GalleryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "content view is set in onCreate method of activity");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_gallery, container, false);
        ButterKnife.bind(this, inflate);

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilterLayout.setVisibility(View.VISIBLE);
            }
        });
        CancelFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilterLayout.setVisibility(View.INVISIBLE);
            }
        });
        FilterLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        refreshLayout.setRefreshing(true);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                load_data();

            }
        });
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                System.out.println(ratingBar.getRating());
                if ((int) ratingBar.getRating() > 4) {
                    rating.setText("Excellent!!");
                } else if ((int) ratingBar.getRating() >= 2) {
                    rating.setText("Good!");
                } else {
                    rating.setText("Bad");
                }
            }
        });

        refreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        cardItems = new ArrayList<CardItem>();

        gallaryItemAdapter = new GallaryItemAdapter(getContext(), cardItems, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, String LargeURL) {
                mListener.onFragmentInteraction(LargeURL);
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        gellaryView.setLayoutManager(mLayoutManager);
        gellaryView.setAdapter(gallaryItemAdapter);

        load_data();
        return inflate;
    }

    private void initRecyclerView() {

        gallaryItemAdapter = new GallaryItemAdapter(getContext(), cardItems, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, String LargeURL) {
                mListener.onFragmentInteraction(LargeURL);
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        gellaryView.setLayoutManager(mLayoutManager);
        gellaryView.setAdapter(gallaryItemAdapter);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String url_m);
    }

    public void load_data() {
        final String SERVER_URL = "https://api.unsplash.com/photos/random?count=10&client_id=6aca39670d4ad87beacfc189ed8255fa89bf2b21ced820f6aa57b317f3fc905f";

        @SuppressLint("StaticFieldLeak")
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                gallaryItemAdapter.clear();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                gallaryItemAdapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {

                    URL url = new URL(SERVER_URL);
                    HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

                    InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder builder = new StringBuilder();
                    String inputString;
                    while ((inputString = bufferedReader.readLine()) != null) {
                        builder.append(inputString);
                    }

                    String JsonString = builder.toString();

                    System.out.println(JsonString);

                    Gson gson = new Gson();
                    CardItem[] Card = gson.fromJson(JsonString, CardItem[].class);
                    System.out.println(gson.toJson(Card));

                    cardItems.addAll(Arrays.asList(Card));

                    urlConnection.disconnect();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        task.execute();
    }

}