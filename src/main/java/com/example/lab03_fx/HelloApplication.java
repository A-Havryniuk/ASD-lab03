package com.example.lab03_fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class HelloApplication extends Application {
    private final Pane PANE = new Pane();
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = createScene();
        stage.setTitle("Shell sort");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public Scene createScene() {
        TextField sizeOfArray_TextField = new TextField("Enter a size of Array");
        sizeOfArray_TextField.relocate(10, 20);
        sizeOfArray_TextField.setPrefSize(200, 30);

        Button createButton = new Button("Create and sort");
        createButton.resize(40, 40);
        createButton.relocate(sizeOfArray_TextField.getLayoutX()+220,sizeOfArray_TextField.getLayoutY());
        createButton.setOnAction(actionEvent -> {
            int size = Integer.parseInt(sizeOfArray_TextField.getText());
            createAndSort(size);
        });

        TextField sizeOfArrPT = new TextField("Enter a size of array");
        sizeOfArrPT.setPrefSize(150, 30);
        sizeOfArrPT.relocate(createButton.getLayoutX()+150, createButton.getLayoutY());

        Button personalTask_button = new Button("i'm here");
        personalTask_button.setPrefSize(100, 20);
        personalTask_button.relocate(sizeOfArrPT.getLayoutX()+150, sizeOfArrPT.getLayoutY());
        personalTask_button.setOnAction(actionEvent -> {
            personalTask(Integer.parseInt(sizeOfArrPT.getText()));
        });


        PANE.getChildren().addAll(sizeOfArray_TextField, createButton, personalTask_button, sizeOfArrPT);
        return new Scene(PANE, 800, 600);
    }

    public static void main(String[] args) {
        launch();
    }
    public void createAndSort(int size) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < array.length; ++i) {
            array[i] = (int) (Math.random() * 200 - 100);
        }
        long i1 = System.currentTimeMillis();
        TextArea textArea = new TextArea();
        textArea.setPrefSize(300, 200);
        textArea.setText(Arrays.toString(array));
        textArea.appendText("\n");
//---------------------------------------------------------------------
        for(int gap = array.length/2; gap>0; gap /= 2) {
            for(int i = gap; i < array.length; i += 1) {
                int temp = array[i];
                int j;
                for(j = i; j >= gap && array[j-gap] > temp; j -= gap)
                    array[j] = array[j-gap];
                array[j] = temp;
            }
            textArea.appendText(Arrays.toString(array)+"\n");
        }
//---------------------------------------------------------------------
        long i2 = System.currentTimeMillis();
        textArea.appendText("Result array:\n");
        textArea.appendText(Arrays.toString(array));
        textArea.appendText("\n");
        textArea.appendText("Time(ms): ");
        textArea.appendText((i2 - i1) + "");
        textArea.relocate(20, 60);
        PANE.getChildren().add(textArea);
    }

    public void personalTask(int size) {
        Double[] array = new Double[size];
        Random random = new Random();
        for (int i = 0; i < array.length; ++i) {
            array[i] = random.nextInt(10000) / 100.0 - 50.0;
        }
        TextArea textArea = new TextArea();
        textArea.setPrefSize(400, 200);
        for (Double aDouble : array) {
            textArea.appendText(String.format("%, .3f", aDouble));
            textArea.appendText(" | ");
        }
        textArea.appendText("\n");

        String string = Arrays.toString(array);
        int firstIndx = string.indexOf('-');
        int lastIndx = string.lastIndexOf('-');
        if (firstIndx == lastIndx)
            textArea.appendText("Oops. Sorry, we don't have required number. Try again ;)");
        textArea.relocate(350, 60);

        List<Double> negativeNum = Arrays.stream(array).filter(a -> a < 0).toList();
        List<Double> listOfArray = Arrays.stream(array).toList();
        firstIndx =  listOfArray.indexOf(negativeNum.get(0));
        lastIndx = listOfArray.lastIndexOf(negativeNum.get(negativeNum.size()-1));

        Double[] arrayBetweenNegative = new Double[lastIndx - firstIndx - 1];
        for(int i = 0; i < arrayBetweenNegative.length; ++i)
        {
            arrayBetweenNegative[i] = array[firstIndx+i+1];
        }
//----------------------------------------------------------------------------------------------------------------------

        for(int gap = arrayBetweenNegative.length/2; gap>0; gap /= 2) {
            for(int i = gap; i < arrayBetweenNegative.length; i += 1) {
                Double temp = arrayBetweenNegative[i];
                int j;
                for(j = i; j >= gap && arrayBetweenNegative[j-gap] > temp; j -= gap)
                    arrayBetweenNegative[j] = arrayBetweenNegative[j-gap];
                arrayBetweenNegative[j] = temp;
            }
            for(int k = 0; k <= firstIndx; ++k)
            {
                textArea.appendText(String.format("%, 6.3f",array[k])+" | ");
            }
            for(int k = 0; k < arrayBetweenNegative.length; ++k)
            {
                textArea.appendText(String.format("%, 6.3f",arrayBetweenNegative[k])+" | ");
            }
            for(int k = lastIndx; k < array.length; ++k)
            {
                textArea.appendText(String.format("%, 6.3f",array[k])+" | ");
            }
            textArea.appendText("\n");
        }
//----------------------------------------------------------------------------------------------------------------------

        PANE.getChildren().addAll(textArea);
    }
}