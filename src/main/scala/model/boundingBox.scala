package edu.luc.etl.cs313.scala.shapes.model

import scala.collection.mutable

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
      val bounds = mutable.Map("xmin"-> Int.MaxValue ,"ymin" -> Int.MaxValue,"xmax" -> Int.MinValue,"ymax" -> Int.MinValue)
      points.foldLeft(bounds)((bound,point) => findBounds(bound, point))
      bounds("xmax") -= bounds("xmin")
      bounds("ymax") -= bounds("ymin")
      Location(bounds("xmin"), bounds("ymin"), Rectangle(bounds("xmax"), bounds("ymax")))

    case Location(width, height, shape) =>
        val loc:Location = this.apply(shape)
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
      val bounds = mutable.Map("xmin"-> Int.MaxValue ,"ymin" -> Int.MaxValue,"xmax" -> Int.MinValue,"ymax" -> Int.MinValue)
      shapeList.foldLeft(bounds)((bound,shape) => findBounds(bound, shape))
      bounds("xmax") -= bounds("xmin")
      bounds("ymax") -= bounds("ymin")
      Location(bounds("xmin"), bounds("ymin"), Rectangle(bounds("xmax"), bounds("ymax")))
    case _ =>
      Location(0, 0, Rectangle(0, 0))

  }

  def findBounds(bound: mutable.Map[String, Int], shape: Shape): mutable.Map[String, Int] = {
    if(shape.isInstanceOf[Point]) {
      val point = shape.asInstanceOf[Point]
      bound("xmin") = Math.min(bound("xmin"), point.x)
      bound("ymin") = Math.min(bound("ymin"), point.y)
      bound("xmax") = Math.max(bound("xmax"), point.x)
      bound("ymax") = Math.max(bound("ymax"), point.y)
    }else{
      val loc = this.apply(shape.asInstanceOf[Shape])
      bound("xmin") = Math.min(bound("xmin"), loc.x)
      bound("ymin") = Math.min(bound("ymin"), loc.y)
      val shRect = loc.child.asInstanceOf[Rectangle] // This is always a Rectangle
      bound("xmax") = Math.max(bound("xmax"), shRect.width + loc.x)
      bound("ymax") = Math.max(bound("ymax"), shRect.height + loc.y)
    }
    bound

  }
}