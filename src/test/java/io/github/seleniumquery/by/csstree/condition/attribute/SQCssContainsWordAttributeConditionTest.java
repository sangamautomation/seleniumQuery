/*
 * Copyright (c) 2015 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.seleniumquery.by.csstree.condition.attribute;

import io.github.seleniumquery.by2.finder.ElementFinder;
import io.github.seleniumquery.by2.finder.ElementFinderUtilsTest;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

public class SQCssContainsWordAttributeConditionTest {

    @Test
    public void toElementFinder() {
        // given
        SQCssContainsWordAttributeCondition containsWordAttributeCondition = new SQCssContainsWordAttributeCondition("values", "10");
        ElementFinder previous = ElementFinderUtilsTest.UNIVERSAL_SELECTOR_FINDER;
        // when
        ElementFinder elementFinder = containsWordAttributeCondition.toElementFinder(previous);
        // then
        assertThat(elementFinder.getCssFinder().toString(), is("[values~='10']"));
        assertThat(elementFinder.canFetchThroughCssAlone(), is(true));

        String attrName = "@*[translate(name(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = 'values']";
        assertThat(elementFinder.getXPathExpression(), is(".//*[contains(concat(' ', normalize-space("+attrName+"), ' '), ' 10 ')]"));

        assertThat(elementFinder.getElementFilterList().getElementFilters(), empty());
    }

}