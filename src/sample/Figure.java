package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

import static sample.Main.WIDTH;
import static sample.Main.HEIGHT;
import static sample.Main.SIZE;

public class Figure extends Pane {

    private int points;

    public int getPoints() {
        return points;
    }

    public ArrayList<Rectangle> rectangles;

    public Figure(int type, double initialLayoutX, double initialLayoutY) {
        super();
        this.setLayoutY(initialLayoutY);
        this.setLayoutX(initialLayoutX);
        rectangles = new ArrayList<>();
        switch (type) {
            case 1: createType1(); break;
            case 2: createType2(); break;
            case 3: createType3(); break;
            case 4: createType4(); break;
            case 5: createType5(); break;
            case 6: createType6(); break;
            case 7: createType7(); break;
            case 8: createType8(); break;
            case 9: createType9(); break;
            case 10: createType10(); break;
            case 11: createType11(); break;
            case 12: createType12(); break;
            case 13: createType13(); break;
            case 14: createType14(); break;
            case 15: createType15(); break;
            case 16: createType16(); break;
            case 17: createType17(); break;
            case 18: createType18(); break;
            case 19: createType19(); break;
        }
        points = rectangles.size();
        this.getChildren().addAll(rectangles);
        this.setOnMouseDragged(mouseEvent -> {
            this.setLayoutX(mouseEvent.getX() + this.getLayoutX());
            this.setLayoutY(mouseEvent.getY() + this.getLayoutY());
        });
        this.setOnMouseReleased(mouseEvent -> {
            if (!Main.isPossibleToPlace(this)) {
                this.setLayoutX(initialLayoutX);
                this.setLayoutY(initialLayoutY);
            }
            else {
                Main.checkForNewFigure();
                Main.checkForSequence();
            }
        });
    }

    private Rectangle createRectangle() {
        Rectangle rectangle = new Rectangle(SIZE, SIZE);
        rectangle.setArcHeight(HEIGHT * 1.25);
        rectangle.setArcWidth(WIDTH * 1.25);
        rectangle.setStroke(Color.BLACK);
        return rectangle;
    }

    private void createType1() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Rectangle rectangle = createRectangle();
                rectangle.setFill(Color.GREEN);
                rectangle.setX(j * SIZE + (j + 1) * 2);
                rectangle.setY(i * SIZE + (i + 1) * 2);
                rectangles.add(rectangle);
            }
        }
    }

    private void createType2() {
        for (int i = 0; i < 3; i++) {
            Rectangle rectangle = createRectangle();
            rectangle.setFill(Color.BLUE);
            rectangle.setX(i * SIZE + (i + 1) * 2);
            rectangle.setY(2);
            rectangles.add(rectangle);
        }
    }

    private void createType3() {
        for (int i = 0; i < 3; i++) {
            Rectangle rectangle = createRectangle();
            rectangle.setFill(Color.BLUE);
            rectangle.setY(i * SIZE + (i + 1) * 2);
            rectangle.setX(2);
            rectangles.add(rectangle);
        }
    }

    private void createType4() {
        for (int i = 0; i < 3; i++) {
            Rectangle rectangle = createRectangle();
            rectangle.setFill(Color.YELLOW);
            rectangle.setY(i * SIZE + (i + 1) * 2);
            rectangle.setX(2);
            rectangles.add(rectangle);
        }
        int y = 2 * SIZE + 3 * 2;
        for (int i = 1; i <= 2; i++) {
            Rectangle rectangle = createRectangle();
            rectangle.setFill(Color.YELLOW);
            rectangle.setY(y);
            rectangle.setX(i * SIZE + (i + 1) * 2);
            rectangles.add(rectangle);
        }
    }

    private void createType5() {
        int y = 2 * SIZE + 3 * 2;
        for (int i = 0; i < 3; i++) {
            Rectangle rectangle = createRectangle();
            rectangle.setFill(Color.YELLOW);
            rectangle.setY(y);
            rectangle.setX(i * SIZE + (i + 1) * 2);
            rectangles.add(rectangle);
        }
        int x = y;
        for (int i = 0; i < 2; i++) {
            Rectangle rectangle = createRectangle();
            rectangle.setFill(Color.YELLOW);
            rectangle.setX(x);
            rectangle.setY(i * SIZE + (i + 1) * 2);
            rectangles.add(rectangle);
        }
    }

    private void createType6() {
        for (int i = 0; i < 3; i++) {
            Rectangle rectangle = createRectangle();
            rectangle.setFill(Color.YELLOW);
            rectangle.setX(i * SIZE + (i + 1) * 2);
            rectangle.setY(2);
            rectangles.add(rectangle);
        }
        for (int i = 1; i <= 2; i++) {
            Rectangle rectangle = createRectangle();
            rectangle.setFill(Color.YELLOW);
            rectangle.setY(i * SIZE + (i + 1) * 2);
            rectangle.setX(2);
            rectangles.add(rectangle);
        }
    }

    private void createType7() {
        for (int i = 0; i < 3; i++) {
            Rectangle rectangle = createRectangle();
            rectangle.setFill(Color.YELLOW);
            rectangle.setX(i * SIZE + (i + 1) * 2);
            rectangle.setY(2);
            rectangles.add(rectangle);
        }
        for (int i = 1; i <= 2; i++) {
            Rectangle rectangle = createRectangle();
            rectangle.setFill(Color.YELLOW);
            rectangle.setY(i * SIZE + (i + 1) * 2);
            rectangle.setX(2 * SIZE + 3 * 2);
            rectangles.add(rectangle);
        }
    }

    private void createType8() {
        for (int i = 0; i < 4; i++) {
            Rectangle rectangle = createRectangle();
            rectangle.setY(2);
            rectangle.setX(i * SIZE + (i + 1) * 2);
            rectangle.setFill(Color.ORANGE);
            rectangles.add(rectangle);
        }
    }

    private void createType9() {
        for (int i = 0; i < 4; i++) {
            Rectangle rectangle = createRectangle();
            rectangle.setY(i * SIZE + (i + 1) * 2);
            rectangle.setX(2);
            rectangle.setFill(Color.ORANGE);
            rectangles.add(rectangle);
        }
    }

    private void createType10() {
        for (int i = 0; i < 5; i++) {
            Rectangle rectangle = createRectangle();
            rectangle.setY(2);
            rectangle.setX(i * SIZE + (i + 1) * 2);
            rectangle.setFill(Color.RED);
            rectangles.add(rectangle);
        }
    }

    private void createType11() {
        for (int i = 0; i < 5; i++) {
            Rectangle rectangle = createRectangle();
            rectangle.setY(i * SIZE + (i + 1) * 2);
            rectangle.setX(2);
            rectangle.setFill(Color.RED);
            rectangles.add(rectangle);
        }
    }

    private void createType12() {
        for (int i = 0; i < 2; i++) {
            Rectangle rectangle = createRectangle();
            rectangle.setX(2);
            rectangle.setY(i * SIZE + (i + 1) * 2);
            rectangle.setFill(Color.PURPLE);
            rectangles.add(rectangle);
        }
        Rectangle rectangle = createRectangle();
        rectangle.setX(SIZE + 2 * 2);
        rectangle.setY(SIZE + 2 * 2);
        rectangle.setFill(Color.PURPLE);
        rectangles.add(rectangle);
    }

    private void createType13() {
        for (int i = 0; i < 2; i++) {
            Rectangle rectangle = createRectangle();
            rectangle.setX(SIZE + 2 * 2);
            rectangle.setY(i * SIZE + (i + 1) * 2);
            rectangle.setFill(Color.PURPLE);
            rectangles.add(rectangle);
        }
        Rectangle rectangle = createRectangle();
        rectangle.setX(2);
        rectangle.setY(SIZE + 2 * 2);
        rectangle.setFill(Color.PURPLE);
        rectangles.add(rectangle);
    }

    private void createType14() {
        for (int i = 0; i < 2; i++) {
            Rectangle rectangle = createRectangle();
            rectangle.setX(2);
            rectangle.setY(i * SIZE + (i + 1) * 2);
            rectangle.setFill(Color.PURPLE);
            rectangles.add(rectangle);
        }
        Rectangle rectangle = createRectangle();
        rectangle.setX(SIZE + 2 * 2);
        rectangle.setY(2);
        rectangle.setFill(Color.PURPLE);
        rectangles.add(rectangle);
    }

    private void createType15() {
        for (int i = 0; i < 2; i++) {
            Rectangle rectangle = createRectangle();
            rectangle.setX(SIZE + 2 * 2);
            rectangle.setY(i * SIZE + (i + 1) * 2);
            rectangle.setFill(Color.PURPLE);
            rectangles.add(rectangle);
        }
        Rectangle rectangle = createRectangle();
        rectangle.setX(2);
        rectangle.setY(2);
        rectangle.setFill(Color.PURPLE);
        rectangles.add(rectangle);
    }

    private void createType16() {
        Rectangle rectangle = createRectangle();
        rectangle.setY(2);
        rectangle.setX(2);
        rectangle.setFill(Color.BLACK);
        rectangles.add(rectangle);
    }

    private void createType17() {
        for (int i = 0; i < 2; i++) {
            Rectangle rectangle = createRectangle();
            rectangle.setX(i * SIZE + (i + 1) * 2);
            rectangle.setY(2);
            rectangle.setFill(Color.PINK);
            rectangles.add(rectangle);
        }
    }

    private void createType18() {
        for (int i = 0; i < 2; i++) {
            Rectangle rectangle = createRectangle();
            rectangle.setX(2);
            rectangle.setY(i * SIZE + (i + 1) * 2);
            rectangle.setFill(Color.PINK);
            rectangles.add(rectangle);
        }
    }

    private void createType19() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Rectangle rectangle = createRectangle();
                rectangle.setFill(Color.LIMEGREEN);
                rectangle.setX(j * SIZE + (j + 1) * 2);
                rectangle.setY(i * SIZE + (i + 1) * 2);
                rectangles.add(rectangle);
            }
        }
    }
}
