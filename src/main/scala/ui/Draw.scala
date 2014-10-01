package edu.luc.etl.cs313.scala.shapes
package ui

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.Style

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
    case _ =>
  }
}
