package axifabric

import chisel3._
import chisel3.util._
import axifabric.utils._

class axiFabric(mst_num: Int, prio_num: Int) extends Module with AxiConfig {
  val io = new axiF(mst_num, prio_num)

  /*
  generate the wreq_gen signal
  */
  val wreq = Wire(UInt(mst_num.W))
  for(i <- 0 until mst_num) {
    wreq(i) := io.slv(i).aw.valid
  }
  val wreq_gen = 
  //generate the qos signal to rr
  //what is the contains of the qos
  val qos = Wire(Vec(mst_num, UInt(qosWidth.W)))
  for(i <- 0 until mst_num) {
    qos(i) := io.slv(i).aw.bits.qos
  }

  val arbiterQ = Module(new arbiterBB(mst_num, prio_num))
  
  for(i <- 0 until mst_num) {
    arbiterQ.io.prio(i) := qos(i)
  }
  
  //select the awchannel and wchannel
  //val muxWChannel := MuxCase(new )
}