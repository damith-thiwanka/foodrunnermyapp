package lk.electfast.thiwanka.foodrunner_myapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import butterknife.BindView;
import lk.electfast.thiwanka.foodrunner_myapp.R;
import lk.electfast.thiwanka.foodrunner_myapp.models.CardItem;


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

        return inflate;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        new SimpleTask().execute();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }

    private class SimpleTask extends AsyncTask<Void, Void, String> {

        private static final String URL = "https://picsum.photos/list";
        public static final String SERVER_URL = "https://api.unsplash.com/photos/random?count=10&client_id=6aca39670d4ad87beacfc189ed8255fa89bf2b21ced820f6aa57b317f3fc905f";

        @Override
        protected String doInBackground(Void... voids) {
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

//                Gson gson=new Gson();
//                CardItem cardItem =gson.fromJson(JsonString,CardItem.class);
//                System.out.println("GSON :- "+cardItem.getDownloads().toString());


               // System.out.println(convertJsonToObject(bufferedReader));

               // urlConnection.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


    }


}