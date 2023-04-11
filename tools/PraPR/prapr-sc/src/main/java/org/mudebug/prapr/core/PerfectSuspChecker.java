package org.mudebug.prapr.core;

/*
 * #%L
 * prapr-plugin
 * %%
 * Copyright (C) 2018 - 2022 University of Texas at Dallas
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.Collection;

import org.pitest.mutationtest.engine.MutationDetails;

public class PerfectSuspChecker implements SuspChecker {
    private static final long serialVersionUID = 1L;

    private final Collection<String> failingTests;

    private final String oracleFaultyClass;

    private final int oracleFaultyLine;
    
    public PerfectSuspChecker(final Collection<String> failingTests,
        String oracleFaultyClass, int oracleFaultyLine) {
        this.failingTests = failingTests;
        this.oracleFaultyClass = oracleFaultyClass;
        this.oracleFaultyLine = oracleFaultyLine;
    }

    @Override
    public boolean isHit(String className) {
        return className.equals(oracleFaultyClass);
    }

    @Override
    public boolean isHit(String className, String methodSig) {
        return className.equals(oracleFaultyClass);
    }

    @Override
    public boolean isHit(MutationDetails details) {
        return details.getClassName().asJavaName().equals(oracleFaultyClass) &&
            details.getLineNumber() == oracleFaultyLine;
    }

    @Override
    public Collection<String> getAllFailingTests() {
        return this.failingTests;
    }
}
