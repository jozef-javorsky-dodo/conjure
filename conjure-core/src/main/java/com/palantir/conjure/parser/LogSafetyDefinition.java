/*
 * (c) Copyright 2022 Palantir Technologies Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.palantir.conjure.parser;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.palantir.conjure.exceptions.ConjureIllegalArgumentException;
import com.palantir.logsafe.Preconditions;

public enum LogSafetyDefinition {
    SAFE("safe"),
    UNSAFE("unsafe"),
    DO_NOT_LOG("do-not-log");

    private final String name;

    LogSafetyDefinition(String name) {
        this.name = name;
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static LogSafetyDefinition parse(String value) {
        switch (Preconditions.checkNotNull(value, "String value is required")) {
            case "safe" -> {
                return SAFE;
            }
            case "unsafe" -> {
                return UNSAFE;
            }
            case "do-not-log" -> {
                return DO_NOT_LOG;
            }
        }
        throw new ConjureIllegalArgumentException("Unknown value: " + value);
    }

    @Override
    public String toString() {
        return "LogSafetyDefinition{" + name + '}';
    }
}
