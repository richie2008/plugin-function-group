package com.fr.solution.plugin.function.string;

import com.fr.general.GeneralUtils;
import com.fr.script.AbstractFunction;
import com.fr.solution.plugin.function.AbstractSolutionFunction;
import com.fr.stable.ArrayUtils;
import com.fr.stable.Primitive;

/**
 * 反转字符串
 */
public class StringReverse extends AbstractSolutionFunction {

    @Override
    public Object solve(Object[] args) {
        if (ArrayUtils.isEmpty(args)) {
            return Primitive.ERROR_VALUE;
        }
        String str = GeneralUtils.objectToString(args[0]);
        StringBuilder sb = new StringBuilder(str);

        return sb.reverse().toString();
    }
}
