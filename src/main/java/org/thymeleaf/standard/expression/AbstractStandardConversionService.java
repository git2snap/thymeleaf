/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2012, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.thymeleaf.standard.expression;


import org.thymeleaf.Configuration;
import org.thymeleaf.context.IProcessingContext;
import org.thymeleaf.util.Validate;

/**
 * 
 * @author Daniel Fern&aacute;ndez
 * 
 * @since 2.1.0
 *
 */
public abstract class AbstractStandardConversionService implements IStandardConversionService {



    protected AbstractStandardConversionService() {
        super();
    }




    public final <T> T convert(final Configuration configuration, final IProcessingContext processingContext,
                               final Object object, final Class<T> targetClass) {

        Validate.notNull(targetClass, "Target class cannot be null");

        /*
         * CONVERSIONS TO String (will be 99% of executions)
         */
        if (targetClass.equals(String.class)) {
            if (object == null || object instanceof String) {
                return (T) object;
            }
            return (T) convertToString(configuration, processingContext, object);
        }

        /*
         * OTHER CONVERSIONS
         */
        return convertOther(configuration, processingContext, object, targetClass);

    }



    protected String convertToString(
            final Configuration configuration, final IProcessingContext processingContext, final Object object) {
        if (object == null) {
            return null;
        }
        return object.toString();
    }


    protected <T> T convertOther(
            final Configuration configuration, final IProcessingContext processingContext, final Object object, final Class<T> targetClass) {
        throw new IllegalArgumentException("No available conversion for target class \"" + targetClass.getName() + "\"");
    }




}
