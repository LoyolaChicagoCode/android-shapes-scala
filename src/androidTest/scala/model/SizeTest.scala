package edu.luc.etl.cs313.scala.shapes.model

import org.scalatest.{ShouldMatchers, FlatSpec}

class SizeTest extends FlatSpec with ShouldMatchers {

  import fixtures._
  import edu.luc.etl.cs313.scala.shapes.model.{size => ssize} // to avoid weird naming conflict

  "The size behavior" should "work for a simple circle" in { ssize(simpleCircle) should equal (1) }
  it should "work for a simple rectangle" in { ssize(simpleRectangle) should equal (1) }
  it should "work for a simple polygon" in { ssize(simplePolygon) should equal (1) }
  it should "work for a simple location" in { ssize(simpleLocation) should equal (1) }
  it should "work for a simple fill" in { ssize(simpleFill) should equal (1) }
  it should "work for a simple outline" in { ssize(simpleOutline) should equal (1) }
  it should "work for a simple stroke" in { ssize(simpleStroke) should equal (1) }
  it should "work for a simple group" in { ssize(simpleGroup) should equal (2) }
  it should "work for a complex group" in { ssize(complexGroup) should equal (6) }
}
