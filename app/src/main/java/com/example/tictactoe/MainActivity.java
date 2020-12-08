package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ArrayList<String>> list = new ArrayList<>();
    private HashMap<Integer, Coordinate> coordinates = new HashMap<>();
    private Button button1, button2, button3, button4, button5, button6, button7,
            button8, button9;
    private TextView textResultX, textResultO;
    private int winnerCountX;
    private int winnerCountO;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SubmitOnClickListener submitOnClickListener = new SubmitOnClickListener();

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(submitOnClickListener);

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(submitOnClickListener);

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(submitOnClickListener);

        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(submitOnClickListener);

        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(submitOnClickListener);

        button6 = findViewById(R.id.button6);
        button6.setOnClickListener(submitOnClickListener);

        button7 = findViewById(R.id.button7);
        button7.setOnClickListener(submitOnClickListener);

        button8 = findViewById(R.id.button8);
        button8.setOnClickListener(submitOnClickListener);

        button9 = findViewById(R.id.button9);
        button9.setOnClickListener(submitOnClickListener);

        textResultX = findViewById(R.id.x_result);
        textResultX.setText(Integer.toString(winnerCountX));

        textResultO = findViewById(R.id.o_result);
        textResultO.setText(Integer.toString(winnerCountO));

        newGame();

        coordinates.put(button1.getId(), new Coordinate(0, 0));
        coordinates.put(button2.getId(), new Coordinate(0, 1));
        coordinates.put(button3.getId(), new Coordinate(0, 2));
        coordinates.put(button4.getId(), new Coordinate(1, 0));
        coordinates.put(button5.getId(), new Coordinate(1, 1));
        coordinates.put(button6.getId(), new Coordinate(1, 2));
        coordinates.put(button7.getId(), new Coordinate(2, 0));
        coordinates.put(button8.getId(), new Coordinate(2, 1));
        coordinates.put(button9.getId(), new Coordinate(2, 2));
    }

    public class SubmitOnClickListener implements View.OnClickListener {

        private String lastValue = null;

        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View v) {
            int id = v.getId();
            Coordinate coordinate = coordinates.get(id);
            int xCoordinate = coordinate.getX();
            int yCoordinate = coordinate.getY();

            if (!list.get(xCoordinate).get(yCoordinate).equals("-")) {
                return;
            }

            if (lastValue == null || lastValue.equals("O")) {
                lastValue = "X";
            } else {
                lastValue = "O";
            }

            list.get(xCoordinate).set(yCoordinate, lastValue);

            if (v instanceof Button) {
                Button clickedButton = (Button) v;
                clickedButton.setText(list.get(xCoordinate).get(yCoordinate));
            }

            if (validate(list, coordinate)) {
                Toast.makeText(getApplicationContext(), "Winner is " + lastValue, Toast.LENGTH_SHORT).show();
                if (lastValue.equals("X")) {
                    winnerCountX++;
                } else {
                    winnerCountO++;
                }
                lastValue = null;

                textResultX.setText(Integer.toString(winnerCountX));
                textResultO.setText(Integer.toString(winnerCountO));

                newGame();
                return;
            }

            if (isBoardFull(list)) {
                Toast.makeText(getApplicationContext(), "DRAW ", Toast.LENGTH_SHORT).show();
                lastValue = null;
                newGame();
            }
        }
    }

    private boolean validate(ArrayList<ArrayList<String>> list, Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        String value = list.get(x).get(y);
        if (value.equals(list.get(x).get(0)) && value.equals(list.get(x).get(1)) && value.equals(list.get(x).get(2))) {
            return true;
        }
        if (value.equals(list.get(0).get(y)) && value.equals(list.get(1).get(y)) && value.equals(list.get(2).get(y))) {
            return true;
        }
        // check diagonal
        if (value.equals(list.get(0).get(0)) && value.equals(list.get(1).get(1)) && value.equals(list.get(2).get(2))) {
            return true;
        }
        // check anti-diagonal
        if (value.equals(list.get(2).get(0)) && value.equals(list.get(1).get(1)) && value.equals(list.get(0).get(2))) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull(ArrayList<ArrayList<String>> list) {
        boolean isFull = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (list.get(i).get(j).contains("-")) {
                    isFull = false;
                }
            }
        }
        return isFull;
    }

    private void newGame() {
        ArrayList<ArrayList<String>> newList = new ArrayList<>();
        newList.add(new ArrayList<String>(Arrays.asList("-", "-", "-")));
        newList.add(new ArrayList<String>(Arrays.asList("-", "-", "-")));
        newList.add(new ArrayList<String>(Arrays.asList("-", "-", "-")));
        list = newList;
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");
    }
}



