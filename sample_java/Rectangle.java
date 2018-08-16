public class Rectangle extends Shape {
  public Rectangle(double w, double h) {
    width = w;
    height = h;
  }
  public double area() {
    return width * height;
  }
}
