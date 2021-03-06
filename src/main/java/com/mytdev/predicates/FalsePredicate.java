/*
 * Copyright 2014 Yann D'Isanto.
 *
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
 */

package com.mytdev.predicates;

/**
 *
 * @author Yann D'Isanto
 * @param <T>
 */
public final class FalsePredicate<T> extends AbstractPredicate<T> {

    private static final FalsePredicate INSTANCE = new FalsePredicate();

    public boolean eval(T candidate) {
        return false;
    }
    
    public static <T> FalsePredicate<T> get() {
        return INSTANCE;
    }

}
