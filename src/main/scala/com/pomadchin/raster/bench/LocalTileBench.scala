package com.pomadchin.raster.bench

import com.pomadchin.raster._

import org.openjdk.jmh.annotations._

import java.util.concurrent.TimeUnit
import scala.util.Random

@BenchmarkMode(Array(Mode.AverageTime))
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
class LocalTileBench {
  val size = 1024
  var tile: MutableArrayTile = _

  @Setup(Level.Trial)
  def setup(): Unit = tile = new IntArrayTile(1 to size * size toArray, size, size)

  var row: Int   = _
  var col: Int   = _
  var value: Int = _
  @Setup(Level.Invocation)
  def selectCell(): Unit = {
    row = Random.nextInt(size)
    col = Random.nextInt(size)
    value = Random.nextInt()
  }

  @Benchmark
  def setCell(): MutableArrayTile = {
    tile.set(row, col, value)
    tile
  }
}
