package id.refactory.app.refactoryapps.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import id.refactory.app.refactoryapps.R;

/**
 * Created by massam on 07/12/17.
 */

public class UploadDocsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View uploadDocs = inflater.inflate(R.layout.fragment_upload_docs, container, false);

        Spinner spinner = (Spinner) uploadDocs.findViewById(R.id.docs_dropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.docs_array, R.layout.fragment_docs_dropdown_item);
        spinner.setAdapter(adapter);
        return uploadDocs;

    }
}
