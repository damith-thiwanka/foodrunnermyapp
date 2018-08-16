package lk.electfast.thiwanka.foodrunner_myapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import lk.electfast.thiwanka.foodrunner_myapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserRegistrationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserRegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserRegistrationFragment extends Fragment {
//    @BindView(R.id.url)
//    TextView textView;
    @BindView(R.id.img_l)
    ImageView imageView;

    private OnFragmentInteractionListener mListener;

    public UserRegistrationFragment() {
    }

    public static UserRegistrationFragment newInstance() {
        UserRegistrationFragment fragment = new UserRegistrationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_reg, container, false);
        ButterKnife.bind(this,view);
        Bundle bundle=getArguments();
        if(bundle!=null){
            Glide.with(this.getContext())
                    .load(bundle.getString("largeUrl"))
                    .into(imageView);
        }
        //textView.setText(bundle.getString("largeUrl"));


        return view;
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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
