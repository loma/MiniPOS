import java.util.*;

public class Drawing {

  public static void main(String[] args) {
    Drawing drawing = new Drawing();
    drawing.add(new Rectangle(3, 5));
    Shape circle = new Circle(10);
    drawing.add(circle);

    System.out.println(drawing.getAllArea());
    System.out.println(drawing.getAreaBy(circle));
  }

  String name;
  Shape[] shapes = new Shape[10];

  int index = 0;
  public void add(Shape s) {
    shapes[index++] = s;
  }

  public void draw() {
  }

  public double getAreaBy(Shape shape) {
    for (Shape s : shapes)
      if (s != null && s == shape) return s.area();
    return 0;
  }

  public double getAllArea() {
    double allArea = 0;
    for (Shape s : shapes)
      if (s != null) allArea = allArea + s.area();
    return allArea;
  }
}
