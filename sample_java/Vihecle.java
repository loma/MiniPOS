class Vihecle {
  double speed;
  String name;
  String color;
  void accelerate() { speed = speed + 10;}
  void breaks() { speed = speed - 10;}
}
class Car extends Vihecle {
  Wheel[] wheels = new Wheel[4];
  void accelerate() { speed = speed + 100;}
  void breaks() { speed = speed - 100;}
}
class Bicycle extends Vihecle {
  Wheel[] wheels = new Wheel[2];
  void accelerate() { speed = speed + 1;}
  void breaks() { speed = speed - 1;}
}
class Wheel {
  double radius;
  String type;
}
