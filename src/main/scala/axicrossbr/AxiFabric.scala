package axifabric

import chisel3._
import chisel3.util._
import chisel3.iotesters._

import axicrossbr.utils._

/*
[1]--parameters is the config of axi bus;
[2]--mst_num is the number of axi master interface;
[3]--prio_num is the number of axi slave interface.
*/
//class AxiFabric(mst_num: Int, prio_num: Int) extends Module with AxiConfig {
//  val io = new axiF(mst_num, prio_num)
//    
//  //***************************************************
//  //Write Channel of the Axi fabric
//  //***************************************************
//  /*
//  defination of intra logic, generate the wreq 
//  enable signal of roundrobin.
//  */
//  //val mstWireq   = Wire(UInt(mst_num.W))
//  val wReqGen    = Wire(UInt(mst_num.W))
//  val wReqEn     = WireDefault(false.B)
//  for(i <- 0 until mst_num) {
//    wReqGen(i)  := (io.mst(i).axislavewriteaddress.valid > 0.U) && (io.mst(i).axislavewritedata.valid > 0.U)
//    when ((wReqGen(i).andR).asBool) {
//      wReqEn := true.B
//    }
//  }
//  
//  
//  val muxWcmdChannel = new axiAddrChannel
//  muxWcmdChannel := MuxCase(new axiAddrChannel, Array(
//    (io.rrP.i_gnt === 0.U) -> io.mst(0).axislavewriteaddress,
//    (io.rrP.i_gnt === 1.U) -> io.mst(1).axislavewriteaddress
//  ))
//  val muxWdataChannel= new axiDataChannel
//  muxWdataChannel := MuxCase(new axiDataChannel, Array(
//    (io.rrP.i_gnt === 0.U) -> io.mst(0).axislavewritedata,
//    (io.rrP.i_gnt === 1.U) -> io.mst(1).axislavewritedata
//  )) 
//
//  /*
//  write channel fifo contains write address channel and 
//  write data channel
//  */
//  val wCmdFIFO  = new syncFIFO(new axiAddrChannel, 2)
//  val wDataFIFO = new syncFIFO(new axiDataChannel, 2)
//  val wRespFIFO = new syncFIFO(new axiRespChannel, 2)
//  val wrespDemux = new demux(new axiDataChannel, Flipped(new axiDataChannel), mst_num)
//  //write cmd channel
//  wCmdFIFO.io.enq.valid := (wReqEn > 0.U) && (wCmdFIFO.io.deq.ready > 0.U)
//  wCmdFIFO.io.enq.bits  <> muxWcmdChannel
//  wCmdFIFO.io.deq.ready := io.slv.aximasterreadaddress.ready || io.slv.aximasterwriteaddress.ready
//  io.slv.aximasterwriteaddress <> wCmdFIFO.io.deq.bits
//  //write data channel
//  wDataFIFO.io.enq.valid:= (wReqEn > 0.U) && (wDataFIFO.io.deq.ready > 0.U)
//  wDataFIFO.io.enq.bits <> muxWdataChannel
//  wDataFIFO.io.deq.ready:= io.slv.aximasterreadaddress.ready || io.slv.aximasterwriteaddress.ready
//  io.slv.aximasterwritedata <> wDataFIFO.io.deq.bits
//  //write resp channel
//  for(j <- 0 until mst_num) {
//    io.mst(j).axislavewriteresp <> wrespDemux.io.muxOut(j)
//  }
//  wrespDemux.io.muxIn <> wRespFIFO.io.deq.bits
//
//  //****************************************************
//  //Read Channel of the Axi fabric
//  //****************************************************
//  val mstRireq    = Wire(UInt(mst_num.W))
//  val rReqGen     = Wire(UInt(mst_num.W))
//  val rReqEn      = Wire(UInt(1.W))
//  for(k <- 0 until mst_num) {
//    rReqGen(k) :=  io.mst(k).axislavereadaddress.valid
//    rReqEn  :=  rReqGen(k).andR
//  }
//  val muxRcmdChannel = new axiAddrChannel
//  muxRcmdChannel := MuxCase(new axiAddrChannel, Array(
//    (io.rrP.i_gnt === 0.U) -> io.mst(0).axislavereadaddress,
//    (io.rrP.i_gnt === 1.U) -> io.mst(1).axislavereadaddress
//  ))
//
//  /*
//  defination of intra logic, generate the rreq
//  enable signal of roundrobin
//  */
//  val rCmdFIFO  = new syncFIFO(new axiAddrChannel, 2)
//  val rDataFIFO = new syncFIFO((Flipped(new axiDataChannel)) ,2)
//  val rdataDemux = new demux(new axiDataChannel, Flipped(new axiDataChannel), mst_num)
//  //read cmd channel
//  rCmdFIFO.io.enq.valid := (rReqEn > 0.U) && (rCmdFIFO.io.deq.ready > 0.U)
//  rCmdFIFO.io.enq.bits <> muxRcmdChannel
//  rCmdFIFO.io.deq.ready := io.slv.aximasterreadaddress.ready || io.slv.aximasterwriteaddress.ready
//  io.slv.aximasterreadaddress <> rCmdFIFO.io.deq.bits
//  //read data channel
//  rDataFIFO.io.enq.valid := (rReqEn > 0.U) && (rDataFIFO.io.deq.ready > 0.U)
//  rDataFIFO.io.enq.bits <> io.slv.aximasterreaddata
//  for(l <- 0 until mst_num) {
//    rDataFIFO.io.deq.ready := (io.mst(l).axislavereaddata.ready).orR
//  }
//  rdataDemux.io.muxIn <> rDataFIFO.io.deq.bits
//  for(m <- 0 until mst_num) {
//    io.mst(m).axislavereaddata <> rdataDemux.io.muxOut(m)
//  }
//  
//  //val rCmdInfo      = Wire(Cat(io.))
//  //select the read control signal
//
//}