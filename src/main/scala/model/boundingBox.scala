package edu.luc.etl.cs313.scala.shapes.model

/**
 * A shape visitor for calculating the bounding box, that is, the smallest
 * rectangle containing the shape. The resulting bounding box is returned as a
 * rectangle at a specific location.
 */
object boundingBox {

  // TODO entirely your job (except Circle) -- DONE!

  def apply(s: Shape): Location = s match {
    case Circle(radius) =>
      Location(-radius, -radius, Rectangle(2 * radius, 2 * radius))
    case Rectangle(width, height) =>
      Location(0, 0, Rectangle(width, height))
    case Polygon(points @ _*) =>
      var xmin = Int.MaxValue
      var ymin = Int.MaxValue
      var xmax = Int.MinValue
      var ymax = Int.MinValue
      def pointBounds(point: Point) {

        xmin = Math.min(xmin, point.x)
        ymin = Math.min(ymin, point.y)
        xmax = Math.max(xmax, point.x)
        ymax = Math.max(ymax, point.y)

      }
      points.map(point => pointBounds(point))
      xmax -= xmin
      ymax -= ymin
      Location(xmin, ymin, Rectangle(xmax, ymax))

    case Location(width, height, shape) =>
        var loc:Location = this.apply(shape)
        val w = width + loc.x
        val h = height + loc.y
        Location(w, h,loc.child)

    case Fill(shape) =>
      this.apply(shape)

    case Outline(shape) =>
      this.apply(shape)

    case Stroke(color, shape) =>
      this.apply(shape)

    case Group(shapeList) =>
      var xmin = Int.MaxValue
      var ymin = Int.MaxValue
      var xmax = Int.MinValue
      var ymax = Int.MinValue

      def findBounds(shape: Shape) {
        val loc = this.apply(shape)
        xmin = Math.min(xmin, loc.x)
        ymin = Math.min(ymin, loc.y)
        var shRect = loc.child.asInstanceOf[Rectangle]
        xmax = Math.max(xmax, shRect.width + loc.x)
        ymax = Math.max(ymax, shRect.height + loc.y)
      }

      shapeList.map(shape =>findBounds(shape))
      xmax -= xmin
      ymax -= ymin
      Location(xmin, ymin, Rectangle(xmax, ymax))

    case _ =>
      Location(0, 0, Rectangle(0, 0))

  }

}