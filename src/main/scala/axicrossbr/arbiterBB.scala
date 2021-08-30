package axifabric

import chisel3._
import chisel3.util._

class arbiterBB(val mst_num: Int, val prio_num: Int) extends BlackBox(Map("MST_NUM" -> mst_num, "PRIO_NUM" -> prio_num)) with HasBlackBoxResource {
  val io = IO(new Bundle{
    val i_clk   = Input(Clock())
    val i_rst   = Input(Bool())
    val i_req   = Input(UInt(mst_num.W))
    val prio    = Input(UInt(prio_num.W))
    val rls     = Input(UInt(mst_num.W))
    val i_gnt   = Input(UInt(1.W))
    val o_req   = Output(UInt(prio_num.W))
    val o_gnt   = Output(UInt(mst_num.W))
  })

  setResource("./enflame_arb_prio.sv")
}