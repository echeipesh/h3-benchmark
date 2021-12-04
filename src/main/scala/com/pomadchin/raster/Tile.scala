package com.pomadchin.raster

trait Grid[@specialized(Int, Long) N] {
  def cols: N
  def rows: N
}

trait MutableArrayTile extends Grid[Int] {
  // comment out these functions to see the regression (boxing) in all set / update function calls
  def cols: Int
  def rows: Int

  def update(i: Int, z: Int): Unit

  def set(col: Int, row: Int, value: Int): Unit = update(row * cols + col, value)
}

/** Inheriting this trait causes boxing */
trait MutableArrayTileBoxed extends Grid[Int] {
  def update(i: Int, z: Int): Unit

  def set(col: Int, row: Int, value: Int): Unit = update(row * cols + col, value)
}

class IntArrayTile(arr: Array[Int], val cols: Int, val rows: Int) extends MutableArrayTile {
  def update(i: Int, z: Int): Unit = arr(i) = z
}

class IntArrayTileBoxed(arr: Array[Int], val cols: Int, val rows: Int) extends MutableArrayTileBoxed {
  def update(i: Int, z: Int): Unit = arr(i) = z
}
