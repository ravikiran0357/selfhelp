/*
 * Copyright 2010-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.zyppysselfhelp.exception;

import java.util.Arrays;

public class MissingParameterException extends ApplicationException {

    private static final long serialVersionUID = 1L;
    private static final int errorCategoryCode = 101;

    public MissingParameterException(String parameter) {
    	super("The parameter [" + parameter + "] is required.",errorCategoryCode);
    }
    
    public MissingParameterException(String[] parameters) {
    	super("The parameters " + parameters + " are required.",errorCategoryCode);
    }
    
    public MissingParameterException(int errorCode, String parameter) {
        super(errorCode,"The parameter [" + parameter + "] is required.",errorCategoryCode);
    }
    
    public MissingParameterException(int errorCode, String[] parameter) {
        super(errorCode,"The parameters " + Arrays.toString(parameter) + " are required.",errorCategoryCode);
    }
}
