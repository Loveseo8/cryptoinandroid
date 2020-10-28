package com.add.caia.ui.caesar_cipher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.add.caia.R;

public class CaesarFragment extends Fragment {

    EditText text_caesar;
    EditText text_result;
    Spinner spinner;
    TextView text_view;
    RadioGroup radio_group;
    RadioButton decrypt;
    RadioButton encrypt;
    Button button;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_caesar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        text_caesar = (EditText) view.findViewById(R.id.text_caesar);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        text_result = (EditText) view.findViewById(R.id.text_result);
        radio_group = (RadioGroup) view.findViewById(R.id.radio_group);
        decrypt = (RadioButton) view.findViewById(R.id.decrypt);
        encrypt = (RadioButton) view.findViewById(R.id.encrypt);
        button = (Button) view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (radio_group.getCheckedRadioButtonId()){

                    case R.id.encrypt:
                        text_result.setText(ceasarChipher(text_caesar.getText().toString(), Integer.parseInt(spinner.getSelectedItem().toString())));
                        break;
                    case R.id.decrypt:
                        text_result.setText(caesarDecipher(text_caesar.getText().toString(), Integer.parseInt(spinner.getSelectedItem().toString())));
                        break;

                }

            }
        });

    }

    public String ceasarChipher(String message, int offset) {

        StringBuilder result = new StringBuilder();

        for (char character : message.toCharArray()) {

            if (character != ' ') {

                int oroginalAlphabetPosition = character - 'a';

                int newAlphabetPosition = (oroginalAlphabetPosition + offset) % 26;

                char newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);

            } else {

                result.append(character);

            }

        }

        return result.toString();

    }

    public String caesarDecipher(String message, int offset) {

        return ceasarChipher(message, 26 - (offset % 26));

    }

}