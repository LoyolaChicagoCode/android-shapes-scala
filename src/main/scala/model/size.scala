package edu.luc.etl.cs313.scala.shapes.model

import scala.language.postfixOps

/**
 * A visitor to compute the number of basic shapes in a (possibly complex) shape.
 */
object size {

  // TODO entirely your job

  def apply(s: Shape): Int = s match {
    case Circle(_) => 1
    case Rectangle(_, _) => 1
    case Polygon(pointList) => 1
    case Location(_, _, _) => 1
    case Fill(_) => 1
    case Outline(_) => 1
    case Stroke(_, _) => 1
    case Group(shapesList) => shapesList.seq.length
    case _ => -1
  }
}