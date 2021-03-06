/**
 * AbstractNumberBuilder.java
 * @author David Ladapo (davidl@zphinx.com)
 * @version  1.0
 * 
 * Copyright &copy;Zphinx Software Solutions
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  License for more details.
 *
 * THERE IS NO WARRANTY FOR THIS SOFTWARE, TO THE EXTENT PERMITTED BY
 * APPLICABLE LAW.  EXCEPT WHEN OTHERWISE STATED IN WRITING BY ZPHINX SOFTWARE SOLUTIONS 
 * AND/OR OTHER PARTIES WHO PROVIDE THIS SOFTWARE "AS IS" WITHOUT WARRANTY
 * OF ANY KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE.  THE ENTIRE RISK AS TO THE QUALITY AND PERFORMANCE OF THE PROGRAM
 * IS WITH YOU.  SHOULD THE PROGRAM PROVE DEFECTIVE, YOU ASSUME THE COST OF
 * ALL NECESSARY SERVICING, REPAIR OR CORRECTION.
 *
 * IN NO EVENT UNLESS REQUIRED BY APPLICABLE LAW OR AGREED TO IN WRITING
 * WILL ANY COPYRIGHT HOLDER, OR ANY OTHER PARTY WHO MODIFIES AND/OR CONVEYS
 * THE PROGRAM AS PERMITTED ABOVE, BE LIABLE TO YOU FOR DAMAGES, INCLUDING ANY
 * GENERAL, SPECIAL, INCIDENTAL OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE
 * USE OR INABILITY TO USE THE PROGRAM (INCLUDING BUT NOT LIMITED TO LOSS OF
 * DATA OR DATA BEING RENDERED INACCURATE OR LOSSES SUSTAINED BY YOU OR THIRD
 * PARTIES OR A FAILURE OF THE PROGRAM TO OPERATE WITH ANY OTHER PROGRAMS),
 * EVEN IF SUCH HOLDER OR OTHER PARTY HAS BEEN ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGES.
 *
 * For further information, please go to http://www.zphinx.co.uk/
 *
 **/
package com.zphinx.reader.core;

import com.zphinx.reader.english.enums.GazillionNumbers;
import com.zphinx.reader.english.translator.TranslatorConstants;
import com.zphinx.reader.exception.NumberReaderException;
import com.zphinx.reader.messages.Messages;

/**
 * <p>
 * Base implementation of the builder which is used by the translator associated
 * with this process.
 * </p>
 * <p>
 * It provides methods which are used to validate strings, convert them from
 * strings to ints and also provides utility methods used to append translated
 * strings during processing of the translation of numbers.
 * </p>
 * 
 * @author David Ladapo
 * @version $Id: AbstractNumberBuilder.java 220 2012-07-14 23:47:59Z rogue $
 * 
 *          <p>
 *          Created: Jul 10, 2012 06:41:17 PM<br>
 *          Copyright &copy;Zphinx Software Solutions
 *          </p>
 * 
 */

public abstract class AbstractNumberBuilder implements NumberBuilder {

	/**
	 * Checks the last number in the given number sequence
	 * 
	 * @param num
	 *            The additional string to append.
	 * @param sBuilder
	 *            The StringBuilder which contains extra information.
	 */
	protected void checkLastNumbers(final String num, final StringBuilder sBuilder) {
		final String seStr = num.substring(TranslatorConstants.CONSTANT_1, TranslatorConstants.CONSTANT_2);
		final String thStr = num.substring(TranslatorConstants.CONSTANT_2);
		final int secondNum = Integer.parseInt(seStr);
		final int thirdNum = Integer.parseInt(thStr);
		final boolean notZero1 = (secondNum != TranslatorConstants.CONSTANT_0);
		final boolean notZero2 = (thirdNum != TranslatorConstants.CONSTANT_0);
		if (notZero1 || notZero2) {
			sBuilder.append(TranslatorConstants.STRING_AND);
		}
	}

	/**
	 * Validates the length of the number been inputted
	 * 
	 * @param numLen
	 *            The length of the said number
	 * @param numVal
	 *            The value to which this length must conform
	 * @param message
	 *            The string to be used to create an exception
	 * @throws NumberReaderException
	 *             Throws a NumberReaderException if an error occurs
	 */
	protected final void validateNumberLength(final int numLen, final int numVal, final String message) throws NumberReaderException {

		if (numLen < numVal || numLen > numVal) {
			final String mesg = Messages.getString(message);
			throw new NumberReaderException(mesg);
		}
	}

	/**
	 * Gets the int value of a specified number
	 * 
	 * @param num
	 *            The number whose intvalue is expected
	 * @return An int representing the string passed to this method
	 */
	protected final int getNumberInt(final String num) {
		final String str = num.substring(TranslatorConstants.CONSTANT_0, TranslatorConstants.CONSTANT_1);
		return Integer.parseInt(str);
	}

	/**
	 * Gets a trailing word to append to a set of numbers eg
	 * <code>Million</code>.
	 * 
	 * @param index
	 *            The index of the array which is passed to this method
	 * @return A word used as the leading word for a group of numbers
	 */
	public String trailingWord(final int index) {
		final StringBuilder sBuilder = new StringBuilder();
		final String str = GazillionNumbers.findNumber(index);
		sBuilder.append(" ");
		appendWord(sBuilder, str);
		return sBuilder.toString();
	}

	/**
	 * Appends the string represented by num and adds more formating
	 * information.
	 * 
	 * @param sBuilder
	 *            The StringBuilder which contains extra information.
	 * @param num
	 *            The additional string to append.
	 */
	private void appendWord(final StringBuilder sBuilder, final String num) {
		final String str = GazillionNumbers.HUNDRED.getNumber();
		sBuilder.append(num);
		if (!num.equals(str)) {
			sBuilder.append(", ");
		}
	}

}