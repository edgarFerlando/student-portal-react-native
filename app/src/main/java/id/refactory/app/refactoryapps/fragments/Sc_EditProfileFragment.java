package id.refactory.app.refactoryapps.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import id.refactory.app.refactoryapps.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sc_EditProfileFragment extends Fragment {


    public Sc_EditProfileFragment() {
        // Required empty public constructor
    }

    public View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Student Card");


        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_sc__edit_profile, container, false);
        view = inflater.inflate(R.layout.fragment_sc__edit_profile, container, false);

//        TextView t_myprofile = (TextView) view.findViewById(R.id.tv_sc_myprofile);
//        final LinearLayout Detail_MyProfile = (LinearLayout) view.findViewById(R.id.sc_detail_myprofile);

        view_Myprofile_Detail();
        view_Myprotofolio_Detail();
        view_Mycode_Detail();

        return view;
    }

    public void view_Myprofile_Detail() {

        TextView t_myprofile = (TextView) view.findViewById(R.id.tv_sc_myprofile);
        final LinearLayout Detail_MyProfile = (LinearLayout) view.findViewById(R.id.sc_detail_myprofile);

        t_myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Detail_MyProfile.getVisibility() == View.GONE) {
                    //Buat Animasi Fade By Prana
                    Animation in = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
                    Detail_MyProfile.startAnimation(in);
                    Detail_MyProfile.setVisibility(View.VISIBLE);
                } else {
                    Detail_MyProfile.setVisibility(View.GONE);
                }
            }
        });

    }

    public void view_Myprotofolio_Detail() {

        TextView t_myPorto = (TextView) view.findViewById(R.id.tv_sc_Myporto);
        final LinearLayout Detail_Sof = (LinearLayout) view.findViewById(R.id.sc_detail_sof);

        t_myPorto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (Detail_Sof.getVisibility() == View.GONE) {
                    //Buat Animasi Fade By Prana
                    Animation in = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
                    Detail_Sof.startAnimation(in);
                    Detail_Sof.setVisibility(View.VISIBLE);
                } else {
                    Detail_Sof.setVisibility(View.GONE);
                }
            }
        });

    }

    public void view_Mycode_Detail() {

        TextView t_myCode = (TextView) view.findViewById(R.id.tv_sc_MyCodes);
        final LinearLayout Detail_myCode = (LinearLayout) view.findViewById(R.id.sc_detail_gitMycode);

        t_myCode.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (Detail_myCode.getVisibility() == View.GONE) {
                    //Buat Animasi Fade By Prana
                    Animation in = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
                    Detail_myCode.startAnimation(in);
                    Detail_myCode.setVisibility(View.VISIBLE);
                } else {
                    Detail_myCode.setVisibility(View.GONE);
                }
            }
        });

    }
}


