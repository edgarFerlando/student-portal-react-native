package id.refactory.app.refactoryapps.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.refactory.app.refactoryapps.R;

/**
 * Created by prana on 02/11/17.
 */

public class OverviewFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Set Title Toolbar
        getActivity().setTitle("Overview");
        //layout untuk fragment Overview
        return inflater.inflate(R.layout.fragment_overview, container, false);

    }

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        //menrubah judul pada toolbar
//        getActivity().setTitle("OVERVIEW");
//    }
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
