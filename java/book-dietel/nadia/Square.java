public class Square extends Quadrilateral{
    public Square(Points point) {
        super(point);
    }

    public double areaOfShapes(Points point) {
        double side = point.getX();
        double area = side * side;
        return area;
    }
}