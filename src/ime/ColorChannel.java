package ime;

/**
 * This interface represents a 2D matrix of values which supports certain operations with other
 * matrices.
 */
public interface ColorChannel {

  /**
   * Set the specific cell to the specific value. Cell rows and columns begin with 0.
   *
   * @param i     the row of the cell
   * @param j     the column of the cell
   * @param value the floating point value
   * @throws IllegalArgumentException if the given cell does not exist within the bounds of this
   *                                  matrix
   */
  void set(int i, int j, float value) throws IllegalArgumentException;

  /**
   * Retrieves and returns the value at the specified cell in this color channel.
   *
   * @param i the row of the cell
   * @param j the column of the cell
   * @return the value at the given row and column
   * @throws IllegalArgumentException if the row or column is not within the bounds of this color channel
   */
  float get(int i, int j) throws IllegalArgumentException;

//  /**
//   * Add this color channel with the other color channel and return the resulting color channel.
//   * Neither this nor the other color channel should be changed as a result of this operation.
//   *
//   * @param other the other matrix to be added to this
//   * @return the sum of this and other matrix
//   * @throws IllegalArgumentException if the number of rows and number of columns of the two
//   *                                  matrices do not match respectively
//   */
//  ColorChannel add(ColorChannel other) throws IllegalArgumentException;

  ColorChannel brighten(float constant);

  ColorChannel reverseHorizontally();

  ColorChannel reverseVertically();

  float convolve(ColorChannel other);

  /**
   * Get the width of this color channel (number of columns).
   *
   * @return the width of this color channel
   */
  int getWidth();

  /**
   * Get the height of this color channel (number of rows).
   *
   * @return the height of this color channel
   */
  int getHeight();

}
