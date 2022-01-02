/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.felix.http.base.internal.jakartawrappers;

import org.jetbrains.annotations.NotNull;

import jakarta.servlet.ServletException;
import jakarta.servlet.UnavailableException;

/**
 * Helper class to wrap servlet exceptions
 */
public class ServletExceptionUtil {

    /**
     * Throw jakarta servlet exception
     * @param e javax exception
     * @return The exception
     */
    public static ServletException getServletException(@NotNull final javax.servlet.ServletException e) {
        if ( e instanceof javax.servlet.UnavailableException ) {
            final UnavailableException t = new UnavailableException(e.getMessage(), ((javax.servlet.UnavailableException)e).getUnavailableSeconds());
            if ( e.getCause() != null ) {
                t.initCause(e.getCause());
            }
            return t;
        }
        if ( e.getCause() instanceof ServletException ) {
            return (ServletException)e.getCause();
        }
        final ServletException ne = new ServletException(e.getMessage(), e.getCause());
        ne.setStackTrace(e.getStackTrace());
        return ne;
    }

}