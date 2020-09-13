package com.example.quizapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView ques,stats;
    Button btntrue;
    Button btnfalse;
    ProgressBar mprogress;
    int quesindex,quizques;
    int user_score;
    final String SCORE_KEY="SCORE";
    final String INDEX_KEY="INDEX";
    private QuizModel[] questioncollection=new QuizModel[]{
            new QuizModel(R.string.q1,true),
            new QuizModel(R.string.q2,false),
            new QuizModel(R.string.q3,false),
            new QuizModel(R.string.q4,true),
            new QuizModel(R.string.q5,true),
            new QuizModel(R.string.q6,true),
            new QuizModel(R.string.q7,true),
            new QuizModel(R.string.q8,true),
            new QuizModel(R.string.q9,false),
            new QuizModel(R.string.q10,true)
    };
    final int CONST=100/questioncollection.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState!=null)
        {
            quesindex=savedInstanceState.getInt(INDEX_KEY);
            user_score=savedInstanceState.getInt(SCORE_KEY);
        }
        else
        {
            user_score=0;
            quesindex=0;
        }

        Toast.makeText(this, "On create method is called", Toast.LENGTH_SHORT).show();

        btnfalse=findViewById(R.id.btnFalse);
        ques=findViewById(R.id.txtQues);
        stats=findViewById(R.id.txtStats);
        mprogress=findViewById(R.id.progressBar);
        btntrue=findViewById(R.id.btnTrue);
//        QuizModel q1=questioncollection[quesindex];
//        quizques=q1.getQuestion();
        ques.setText(questioncollection[quesindex].getQuestion());

        btntrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkanswer(true);
                changeques();
                mprogress.incrementProgressBy(10);
                stats.setText("Your score : "+user_score+"/"+questioncollection.length);
            }
        });
        btnfalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkanswer(false);
                changeques();
                mprogress.incrementProgressBy(CONST);
                stats.setText("Your score : "+user_score+"/"+questioncollection.length);
            }
        });

    }
    public void changeques(){
        quesindex=(quesindex+1)%10;
        if (quesindex==0)
        {
            AlertDialog.Builder alert=new AlertDialog.Builder(this);
            alert.setTitle("Quiz is finished");
            alert.setMessage("Your score is :"+user_score);
            alert.setCancelable(false);
            alert.setPositiveButton("Finish", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }
       quizques=questioncollection[quesindex].getQuestion();
        ques.setText(quizques);
    }
    public void checkanswer(Boolean userguess)
    {
        Boolean correctans=questioncollection[quesindex].getmAnswers();
        if(correctans==userguess)
        {
            Toast.makeText(getApplicationContext(),R.string.correct_toast_msg,Toast.LENGTH_SHORT).show();
            user_score=user_score+1;
        }
        else
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast_msg,Toast.LENGTH_SHORT).show();;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "On start method is called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "On resume method is called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "On pause method is called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "On stop method is called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "On destroy method is called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("SCORE_KEY",user_score);
        outState.putInt("INDEX_KEY",quesindex);
    }
}