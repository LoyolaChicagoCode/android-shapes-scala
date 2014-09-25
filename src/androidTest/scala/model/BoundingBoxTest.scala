package edu.luc.etl.cs313.scala.shapes.model

import org.scalatest.{FlatSpec, ShouldMatchers}

class BoundingBoxTest extends FlatSpec with ShouldMatchers {

  import fixtures._

  "The bounding box behavior" should "work for a simple circle" in {
    boundingBox(simpleCircle) should equal (Location(-50, -50, Rectangle(100, 100)))
  }
  it should "work for a simple rectangle" in {
    boundingBox(simpleRectangle) should equal (Location(0, 0, Rectangle(80, 120)))
  }
  it should "work for a simple polygon" in {
    boundingBox(simplePolygon) should equal (Location(50, 50, Rectangle(70, 60)))
  }
  it should "work for a simple location" in {
    boundingBox(simpleLocation) should equal (Location(70, 30, Rectangle(80, 120)))
  }
  it should "work for a simple fill" in {
    boundingBox(simpleFill) should equal (Location(0, 0, Rectangle(80, 120)))
  }
  it should "work for a simple outline" in {
    boundingBox(simpleOutline) should equal (Location(0, 0, Rectangle(80, 120)))
  }
  it should "work for a simple stroke" in {
    boundingBox(simpleStroke) should equal (Location(0, 0, Rectangle(80, 120)))
  }
  it should "work for a simple group" in {
    boundingBox(simpleGroup) should equal (Location(150, 50, Rectangle(350, 300)))
  }
  it should "work for a complex group" in {
    boundingBox(complexGroup) should equal (Location(30, 80, Rectangle(470, 320)))
  }
}
