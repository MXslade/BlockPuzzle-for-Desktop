package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main extends Application {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    public static final int SIZE = 25;

    private static Rectangle board[][];

    private static int score = 0;
    private static int bestScore;
    private static Label scoreLabel;
    private static Label bestLabel;

    private static Figure figure1;
    private static Figure figure2;
    private static Figure figure3;

    private static Pane root;

    private void createBoard() {
        board = new Rectangle[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Rectangle rectangle = new Rectangle(SIZE, SIZE);
                rectangle.setX(j * SIZE + (j + 1) * 2);
                rectangle.setY(i * SIZE + (i + 1) * 2);
                rectangle.setArcHeight(HEIGHT * 1.25);
                rectangle.setArcWidth(WIDTH * 1.25);
                rectangle.setFill(Color.WHITE);
                rectangle.setStroke(Color.BLACK);
                board[i][j] = rectangle;
                root.getChildren().add(rectangle);
            }
        }
    }

    private void createTopElements() throws IOException {
        Scanner scanner = new Scanner(new File("best.txt"));
        bestScore = scanner.nextInt();
        scanner.close();
        scoreLabel = new Label("Score label");
        bestLabel = new Label(Integer.toString(bestScore));
        ImageView crown = new ImageView();
        crown.setImage(new Image("crown.png"));
        crown.setFitWidth(85);
        crown.setPreserveRatio(true);
        HBox hbox = new HBox();
        hbox.getChildren().addAll(scoreLabel, crown, bestLabel);
        hbox.setAlignment(Pos.BASELINE_CENTER);
        hbox.setSpacing(10);
        hbox.setLayoutY(-100);
        hbox.setLayoutX(22);
        root.getChildren().add(hbox);
    }

    private static void createBottomElements() {
        Random random = new Random();
        double layoutY = HEIGHT * (SIZE + 2) + 50;
        figure1 = new Figure(random.nextInt(19) + 1, 0, layoutY);
        figure2 = new Figure(random.nextInt(19) + 1, root.getPrefWidth() / 3, layoutY);
        figure3 = new Figure(random.nextInt(19) + 1, root.getPrefWidth() * 2 / 3, layoutY);
        figure1.setDisable(false);
        figure2.setDisable(false);
        figure3.setDisable(false);
        root.getChildren().addAll(figure1, figure2, figure3);
    }

    public static boolean isPossibleToPlace(Figure figure) {
        ArrayList<Integer> xs = new ArrayList<>();
        ArrayList<Integer> ys = new ArrayList<>();
        for (Rectangle rectangle : figure.rectangles) {
            double x = figure.getLayoutX() + rectangle.getX();
            double y = figure.getLayoutY() + rectangle.getY();
            double j = (x - 2) / (SIZE + 2);
            double i = (y - 2) / (SIZE + 2);
            int approxX = (int)j, approxY = (int)i;
            if (approxX + 0.5 <= j) {
                approxX++;
            }
            if (approxY + 0.5 <= i) {
                approxY++;
            }
            xs.add(approxX);
            ys.add(approxY);
        }
        for (int i = 0; i < xs.size(); i++) {
            if (ys.get(i) < 0 || ys.get(i) >= HEIGHT || xs.get(i) < 0 || xs.get(i) >= WIDTH || board[ys.get(i)][xs.get(i)].getFill() != Color.WHITE) {
                return false;
            }
        }
        for (int i = 0; i < xs.size(); i++) {
            board[ys.get(i)][xs.get(i)].setFill(figure.rectangles.get(0).getFill());
        }
        root.getChildren().remove(figure);
        figure.setDisable(true);
        score += figure.getPoints();
        bestScore = score > bestScore ? score : bestScore;
        scoreLabel.setText(Integer.toString(score));
        bestLabel.setText(Integer.toString(bestScore));
        return true;
    }

    public static void checkForNewFigure() {
        if (figure1.isDisable() && figure2.isDisable() && figure3.isDisable()) {
            createBottomElements();
        }
    }

    public static void checkForSequence() {
        ArrayList<Integer> xs = new ArrayList<>();
        ArrayList<Integer> ys = new ArrayList<>();
        for (int i = 0; i < HEIGHT; i++) {
            int counterRow = 0;
            int counterColumn = 0;
            for (int j = 0; j < WIDTH; j++) {
                if (board[i][j].getFill() != Color.WHITE) {
                    counterRow++;
                }
                if (board[j][i].getFill() != Color.WHITE) {
                    counterColumn++;
                }
            }
            if (counterRow == WIDTH) {
                xs.add(i);
            }
            if (counterColumn == HEIGHT) {
                ys.add(i);
            }
        }
        for (int i = 0; i < xs.size(); i++) {
            for (int j = 0; j < WIDTH; j++) {
                board[xs.get(i)][j].setFill(Color.WHITE);
            }
        }
        for (int i = 0; i < ys.size(); i++) {
            for (int j = 0; j < HEIGHT; j++) {
                board[j][ys.get(i)].setFill(Color.WHITE);
            }
        }
        score += 10 * (xs.size() + ys.size());
        bestScore = score > bestScore ? score : bestScore;
        scoreLabel.setText(Integer.toString(score));
        bestLabel.setText(Integer.toString(bestScore));
    }

    private Parent createContent() throws IOException {
        root = new Pane();
        root.setPrefSize(WIDTH * (SIZE + 2) + 2, HEIGHT * (SIZE + 2) + 200);
        root.setTranslateY(100);
        createBoard();
        createTopElements();
        createBottomElements();
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.setTitle("BlockPuzzle");
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(new File("best.txt"));
                printWriter.println(bestScore);
                printWriter.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
