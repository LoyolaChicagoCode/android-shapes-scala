package edu.luc.etl.cs313.scala.shapes.model

/**
 * A shape visitor for calculating the bounding box, that is, the smallest
 * rectangle containing the shape. The resulting bounding box is returned as a
 * rectangle at a specific location.
 */
object boundingBox {

  // TODO entirely your job (except Circle)

  def apply(s: Shape): Location = s match {
    case Circle(radius) =>
      Location(-radius, -radius, Rectangle(2 * radius, 2 * radius))
    case Rectangle(width, height) =>
      Location(0, 0, Rectangle(width, height))
    case Polygon(point) =>
      Location(point.x, point.y, point.child)
    case Location(width, height, shape) =>
      Location(width, height, shape)
    case Fill(shape) =>
      Location(0, 0, shape)
    case Outline(shape) =>
      Location(0, 0, shape)
    case Stroke(color, shape) =>
      Location(0, 0, shape)
    case Group(shapeList) =>
      for ( shape <- shapeList.seq ){
        Location(0, 0, shape)
      }
      Location(0, 0, Rectangle(0, 0))
    case _ =>
      Location(0, 0, Rectangle(0, 0))
  }
}