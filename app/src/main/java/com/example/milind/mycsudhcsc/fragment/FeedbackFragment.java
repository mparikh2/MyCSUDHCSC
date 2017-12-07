package com.example.milind.mycsudhcsc.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milind.mycsudhcsc.R;
import com.example.milind.mycsudhcsc.activity.MainActivity;

public class FeedbackFragment extends Fragment {

    String Email = "milu2409@yahoo.com";
    private Spinner mSubjectSpinner;
    private Spinner mChannelSpinner;
    private TextView mChannelSpinnerText;
    private EditText mMessageEditText;
    private EditText mEmailEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings,
                container, false);

        mMessageEditText = (EditText) view.findViewById(R.id.messageEditText);

        mEmailEditText = (EditText) view.findViewById(R.id.emailEditText);

        Button mSendButton = (Button) view.findViewById(R.id.sendbutton);

        mChannelSpinnerText = (TextView) view.findViewById(R.id.channel_spinner_text);

        mSubjectSpinner = (Spinner) view.findViewById(R.id.subjectSpinner);

        mChannelSpinner = (Spinner) view.findViewById(R.id.channelSpinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> unitTypeSpinnerAdapter1 = ArrayAdapter.createFromResource(getActivity().getBaseContext(),

                R.array.feedback_subjects, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        unitTypeSpinnerAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mSubjectSpinner.setAdapter(unitTypeSpinnerAdapter1);

        mSubjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the item selected in the Spinner
                //String itemSelectedInSpinner = parent.getItemAtPosition(position).toString();
                if (parent.getId() == R.id.subjectSpinner) {
                    String selection = (String) parent.getItemAtPosition(position);

                    // Channel feedback allows user to select a specific channel
                    if (selection.equals(getString(R.string.feedback_channel_feedback))) {
                        if (mChannelSpinner != null) mChannelSpinner.setVisibility(View.VISIBLE);
                        if (mChannelSpinnerText != null)
                            mChannelSpinnerText.setVisibility(View.VISIBLE);
                    } else {
                        if (mChannelSpinner != null) mChannelSpinner.setVisibility(View.GONE);
                        if (mChannelSpinnerText != null)
                            mChannelSpinnerText.setVisibility(View.GONE);
                    }
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO maybe add something here later
            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> unitTypeSpinnerAdapter2 = ArrayAdapter.createFromResource(getActivity().getBaseContext(),

                R.array.feedback_channels, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        unitTypeSpinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mChannelSpinner.setAdapter(unitTypeSpinnerAdapter2);

        mChannelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the item selected in the Spinner
                String itemSelectedInSpinner = parent.getItemAtPosition(position).toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO maybe add something here later
            }
        });

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailEditText.getText().toString();
                String subject = (String)(mSubjectSpinner.getSelectedItem());
                String channel = (String)(mChannelSpinner.getSelectedItem());
                String message = mMessageEditText.getText().toString();
                Intent mailIntent = new Intent(Intent.ACTION_SEND);
                //mailIntent.setData(Uri.parse("email:" + Email));
                mailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{Email});
                mailIntent.putExtra(Intent.EXTRA_CC, new String[]{email});
                mailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                mailIntent.putExtra(Intent.EXTRA_TEXT, channel);
                mailIntent.putExtra(Intent.EXTRA_TEXT, message);
                mailIntent.setType("message/rfc822");
                if(mailIntent.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivity(Intent.createChooser(mailIntent, "Send email via:"));
                    resetForm();
                }
                else
                {
                    Toast.makeText(getActivity(),"There is no app that support this action",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

        // Reset the feedback forms.
    private void resetForm() {
        if (mSubjectSpinner != null) mSubjectSpinner.setSelection(0);
        if (mChannelSpinner != null) mChannelSpinner.setSelection(0);
        if (mEmailEditText != null) mEmailEditText.setText("");
        if (mMessageEditText != null) mMessageEditText.setText("");
    }
}

