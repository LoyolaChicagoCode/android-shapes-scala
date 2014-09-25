package edu.luc.etl.cs313.scala.shapes.model

sealed trait Shape

/** A circle with a specified radius. */
case class Circle(radius: Int) extends Shape

/** A rectangle with specified width and height. */
case class Rectangle(width: Int, height: Int) extends Shape

/**
 * A decorator for specifying a shape's location.
 * This cannot be a case class because we want to inherit from it.
 */
class Location(val x: Int, val y: Int, val child: Shape) extends Shape {
  require(child != null, "null child in " + getClass.getSimpleName)
  override def equals(o: Any) = o match {
    case that: Location => this.x == that.x && this.y == that.y && this.child == that.child
    case _ => false
  }
}
object Location {
  def apply(x: Int, y: Int, child: Shape) = new Location(x, y, child)
  def unapply(l: Location) = Some((l.x, l.y, l.child))
}

/**
 * A composite for grouping edu.luc.etl.cs313.android.shapes.model.
 * This cannot be a case class because we want to inherit from it.
 */
class Group(val children: Shape*) extends Shape {
  require(children != null, "null children in " + getClass.getSimpleName)
  require(! children.contains(null), "null child in " + getClass.getSimpleName)
}
object Group {
  def apply(children: Shape*) = new Group(children: _*)
  def unapply(g: Group) = Some(g.children)
}

/**
 * A decorator indicating that a shape should be drawn as a filled shape instead
 * of an outlined one.
 */
case class Fill(child: Shape) extends Shape {
  require(child != null, "null child in " + getClass.getSimpleName)
}

/**
 * A decorator indicating that a shape should be drawn as an outlined shape
 * instead of a filled one.
 */
case class Outline(child: Shape) extends Shape {
  require(child != null, "null child in " + getClass.getSimpleName)
}

/**
 * A decorator for specifying the stroke (foreground) color for drawing the
 * shape.
 */
case class Stroke(color: Int, child: Shape) extends Shape {
  require(child != null, "null child in " + getClass.getSimpleName)
}

/** A point, implemented as a location without a shape. */
case class Point(override val x: Int, override val y: Int) extends Location(x, y, Circle(0))

/** A special case of a group consisting only of Points. */
case class Polygon(override val children: Point*) extends Group(children: _*)