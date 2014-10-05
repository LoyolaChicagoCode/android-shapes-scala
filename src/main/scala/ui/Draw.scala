package edu.luc.etl.cs313.scala.shapes
package ui

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.Style
import android.graphics.Color


import model._

/**
 * A Visitor for drawing a shape to an Android canvas.
 */
class Draw(canvas: Canvas, paint: Paint) {

  paint.setStyle(Style.STROKE)

  // TODO entirely your job (except Circle)

  def apply(s: Shape): Unit = s match {
    case Circle(radius) => canvas.drawCircle(0, 0, radius, paint)
    case Rectangle(width, height) => canvas.drawRect(0, 0, width, height, paint)
    case Polygon(p1, p2, p3, p4) => canvas.drawLines(Array[Float](p1.x, p1.y, p2.x, p2.y, p2.x, p2.y, p3.x, p3.y, p3.x, p3.y, p4.x, p4.y, p1.x, p1.y, p4.x, p4.y), paint)
    case Fill(shape) => paint.setStyle(Style.FILL)
              apply(shape)
    case Location(width, height, shape) =>
              canvas.translate(width, height)
              apply(shape)
              canvas.translate(-width, -height)
    case Outline(shape) => paint.setStyle(Style.STROKE)
              apply(shape)
    case Stroke(color, shape) =>
              paint.setColor(color)
              paint.setStyle(Style.STROKE)
              apply(shape)
    case Group(shapeList) =>
              for (shape:Shape <- shapeList) {
                apply(shape)
              }

    case _ =>
  }
}
