package otabek.io.teztop;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "check";
    Random random = new Random();
    Button startGameButton;
    String operator;
    int answer;
    int time = 10100;
    int correctChoice;
    short score = 0;
    short numberOfQuestions = 0;
    int[] fourRandomNumbers = {0, 0, 0, 0};
    TextView timerView;
    TextView problem;
    TextView option1;
    TextView option2;
    TextView option3;
    TextView option4;
    TextView scoreView;
    CountDownTimer timer;
    TextView message;
    Button again;
    boolean finished = false;
    TableLayout mTableLayout;

    public void playAgain(View view) {
        time = 10000;
        score = 0;
        numberOfQuestions = 0;
        problemGenerator();
        option1.setClickable(true);
        option2.setClickable(true);
        option3.setClickable(true);
        option4.setClickable(true);
        again.setVisibility(View.INVISIBLE);
        message.setVisibility(View.INVISIBLE);
        finished = false;
        setTimer();


    }

    public void startGame(View view) {
        view.setVisibility(View.INVISIBLE);
        timerView.setVisibility(View.VISIBLE);
        scoreView.setVisibility(View.VISIBLE);
        problem.setVisibility(View.VISIBLE);
        mTableLayout.setVisibility(View.VISIBLE);

        setTimer();
        problemGenerator();
        optionSetter();

    }

    public void clicked(android.view.View view) {
        Log.i(TAG, "clicked: " + time);
        view = ((TextView) view);
        int userAnswer = Integer.parseInt(((TextView) view).getText().toString());
//        Log.i(TAG, "clicked: " + userAnswer);
        if (userAnswer == answer) {
            score++;
            timer.cancel();
            time += 3000;
            setTimer();
            numberOfQuestions++;

            problemGenerator();
            optionSetter();

            message.setText("To'g'ri");
            message.setTextColor(Color.parseColor("#000000"));

        } else {
            time -= 1000;
            timer.cancel();
            setTimer();
            if (finished) {
                setTimer();
            }
            numberOfQuestions++;
            scoreView.setText(String.valueOf(score) + "/" + String.valueOf(numberOfQuestions));
//          Log.i(TAG, "clicked: Wrong");
            message.setText("Xato");
            message.setTextColor(Color.parseColor("#FF0000"));
            problemGenerator();
            optionSetter();

        }
        message.setVisibility(View.VISIBLE);


    }


    public void setTimer() {
        timer = new CountDownTimer(time, 1000) {


            public void onTick(long millisUntilFinished) {
                timerView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }


            public void onFinish() {
                time = 10100;
                option1.setClickable(false);
                option2.setClickable(false);
                option3.setClickable(false);
                option4.setClickable(false);

                again.setVisibility(View.VISIBLE);
                finished = true;
                message.setText("Vaqt tugadi !!!");

                message.setVisibility(View.VISIBLE);


            }
        }.start();

    }


    private void optionSetter() {
        scoreView.setText(String.valueOf(score) + "/" + String.valueOf(numberOfQuestions));
        if (answer < 0) {

            fourRandomNumbers[0] = random.nextInt((200) - 100) - 100;
            fourRandomNumbers[1] = random.nextInt((200) - 100) - 150;
            fourRandomNumbers[2] = random.nextInt((200) - 150) - 150;
            fourRandomNumbers[3] = random.nextInt((199) - 150) - 150;

        } else {
            fourRandomNumbers[0] = random.nextInt((200) + 1) + 1;
            fourRandomNumbers[1] = random.nextInt((200) + 1) + 1;
            fourRandomNumbers[2] = random.nextInt((200) + 1) + 1;
            fourRandomNumbers[3] = random.nextInt((199) + 1) + 1;
        }
        correctChoice = random.nextInt(4);
//        Log.i(TAG, "optionSetter: called" + correctChoice);

        if (correctChoice == 0) {
//                Log.i(TAG, "case 0: ");
            option1.setText(Integer.toString(answer));
            option2.setText(Integer.toString(fourRandomNumbers[0]));
            option3.setText(Integer.toString(fourRandomNumbers[1]));
            option4.setText(Integer.toString(fourRandomNumbers[2]));
        } else if (correctChoice == 1) {
//                Log.i(TAG, "case 1");
            option1.setText(Integer.toString(fourRandomNumbers[0]));
            option2.setText(Integer.toString(answer));
            option3.setText(Integer.toString(fourRandomNumbers[1]));
            option4.setText(Integer.toString(fourRandomNumbers[2]));
        } else if (correctChoice == 2) {
//                Log.i(TAG, "case 2 ");
            option1.setText(Integer.toString(fourRandomNumbers[1]));
            option2.setText(Integer.toString(fourRandomNumbers[0]));
            option3.setText(Integer.toString(answer));
            option4.setText(Integer.toString(fourRandomNumbers[2]));
        } else if (correctChoice == 3) {
//                Log.i(TAG, "case 3");
            option1.setText(Integer.toString(fourRandomNumbers[2]));
            option2.setText(Integer.toString(fourRandomNumbers[0]));
            option3.setText(Integer.toString(fourRandomNumbers[1]));
            option4.setText(Integer.toString(answer));
        }


    }


    private void problemGenerator() {
        int[] twoRandomNumbers = {random.nextInt((99) + 1) + 1, random.nextInt((99) + 1) + 1};
        int operatorNum = random.nextInt(2);
        if (operatorNum == 0) {
            operator = "-";
            answer = twoRandomNumbers[0] - twoRandomNumbers[1];
        } else {
            operator = "+";
            answer = twoRandomNumbers[0] + twoRandomNumbers[1];
        }

        optionSetter();

        problem.setText(Integer.toString(twoRandomNumbers[0]) + operator + Integer.toString(twoRandomNumbers[1]));


//        Log.i(TAG, "problemGenerator: " + operatorNum + " " + operator);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        startGameButton = findViewById()
        mTableLayout = findViewById(R.id.tableLayout);
        again = findViewById(R.id.again);
        again.setVisibility(View.INVISIBLE);
        message = findViewById(R.id.Message);
        message.setVisibility(View.INVISIBLE);

        scoreView = findViewById(R.id.Score);


        option1 = findViewById(R.id.Option1);
        option2 = findViewById(R.id.Option2);
        option3 = findViewById(R.id.Option3);
        option4 = findViewById(R.id.Option4);


        problem = findViewById(R.id.Problem);
        timerView = findViewById(R.id.Timer);

        TextView score = findViewById(R.id.Score);


        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                problemGenerator();
            }
        });


    }
}
