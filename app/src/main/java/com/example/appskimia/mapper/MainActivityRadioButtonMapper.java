package com.example.appskimia.mapper;

import android.content.Context;

import com.example.appskimia.R;

public class MainActivityRadioButtonMapper {

    private Context context;

    public MainActivityRadioButtonMapper(Context context) {
        this.context = context;
    }

    public String mapToStrigFrom(int viewId) {
        String tag;

        switch (viewId) {
            case R.id.rb_jawaban_a:
                tag = getStringResource(R.string.mapper_first_question_first_answer);
                break;
            case R.id.rb_jawaban_b:
                tag = getStringResource(R.string.mapper_first_question_second_answer);
                break;
            case R.id.rb_jawaban_c:
                tag = getStringResource(R.string.mapper_first_question_third_answer);
                break;
            case R.id.rb_jawaban_d:
                tag = getStringResource(R.string.mapper_first_question_fourth_answer);
                break;
            /*
            case R.id.second_question_first_answer:
                tag = getStringResource(R.string.mapper_second_question_first_answer);
                break;
            case R.id.second_question_second_answer:
                tag = getStringResource(R.string.mapper_second_question_second_answer);
                break;
            */
            default:
                tag = getStringResource(R.string.mapper_default_value);
                break;
        }

        return tag;
    }

    private String getStringResource(int stringId) {
        return context.getResources().getString(stringId);
    }
}
