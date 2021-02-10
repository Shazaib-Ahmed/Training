package com.example.sampleproject_1.WaterReminder.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sampleproject_1.R;
import com.example.sampleproject_1.WaterReminder.Fragment.FragmentWaterReminderHome;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.w3c.dom.Text;

public class BottomSheetDialog extends BottomSheetDialogFragment {

    private BottomSheetListener bottomSheetListener;
    TextView textView100ml,textView200ml,textView300ml,textView400ml,textView500ml,textView800ml;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        textView100ml =v.findViewById(R.id.textView_100ml);
        textView200ml =v.findViewById(R.id.textView_200ml);
        textView300ml =v.findViewById(R.id.textView_300ml);
        textView400ml =v.findViewById(R.id.textView_400ml);
        textView500ml =v.findViewById(R.id.textView_500ml);
        textView800ml =v.findViewById(R.id.textView_800ml);

        textView100ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext().getApplicationContext(), "100 ml touch", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        textView200ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext().getApplicationContext(), "200 ml touch", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        textView300ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext().getApplicationContext(), "300 ml touch", Toast.LENGTH_SHORT).show();

                dismiss();
            }
        });

        textView400ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext().getApplicationContext(), "400 ml touch", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        textView500ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext().getApplicationContext(), "500 ml touch", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        textView800ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext().getApplicationContext(), "800 ml touch", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });



        return v;
    }

    public  interface BottomSheetListener{
     void onButtonClicked(String text);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            bottomSheetListener = (BottomSheetListener) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + " must implement BottomSheetListener");
        }
    }
}
