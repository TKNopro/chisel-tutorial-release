package axifabric

import chisel3._
import chisel3.util._

class axiF(mst_num: Int, prio_num: Int) extends Bundle {
  val slv = Vec(mst_num, new SlaveInterface)
  val mst = new MasterInterface
}