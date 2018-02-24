package lineus

sealed trait GCode

case object ArmDown extends GCode

case object ArmUp extends GCode

case class Move(x: Int, y: Int) extends GCode

case class MoveX(x: Int) extends GCode

case class MoveY(y: Int) extends GCode

object GCode {
  def draw(moves: List[Move]): List[GCode] = (ArmDown :: moves) :+ ArmUp

  def rect(x: Int, y: Int, w: Int, h: Int): List[GCode] = List(
    Move(x, y),
    MoveX(w),
    MoveY(h),
    MoveX(-w),
    MoveY(-h)
  )

  def polygon(vertices: (Int, Int)*): List[GCode] = {
    (vertices :+ vertices.head) map { case (x, y) => Move(x, y) } toList
  }

  def render(gcodes: GCode*): List[String] = {
    gcodes map {
      case ArmDown => "G01 Z1000"
      case ArmUp => "G01 Z0"
      case Move(x, y) => s"G01 X$x Y$y"
      case MoveX(x) => s"G01 X$x"
      case MoveY(y) => s"G01 Y$y"
    } toList
  }
}
