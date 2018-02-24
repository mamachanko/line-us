package lineus

sealed trait GCode

case object Home extends GCode

case object ArmDown extends GCode

case object ArmUp extends GCode

case class MoveTo(x: Int, y: Int) extends GCode

case class MoveToX(x: Int) extends GCode

case class MoveToY(y: Int) extends GCode

object GCode {
  def draw(moves: List[GCode]): List[GCode] = List(Home, moves.head, ArmDown) ++ moves.tail ++ List(ArmUp, Home)


  def rect(x: Int, y: Int, w: Int, h: Int): List[GCode] = List(
    MoveTo(x, y),
    MoveToY(y - h),
    MoveToX(x + w),
    MoveToY(y),
    MoveToX(x)
  )

  def polygon(vertices: (Int, Int)*): List[GCode] = {
    (vertices :+ vertices.head) map { case (x, y) => MoveTo(x, y) } toList
  }

  def render(gcodes: GCode*): List[String] = {
    gcodes map {
      case Home => "G28"
      case ArmDown => "G01 Z0"
      case ArmUp => "G01 Z1000"
      case MoveTo(x, y) => s"G01 X$x Y$y"
      case MoveToX(x) => s"G01 X$x"
      case MoveToY(y) => s"G01 Y$y"
    } toList
  }
}
